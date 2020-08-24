package org.mirrentools.orion.entity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 用于复制Request的流使其可以被重复使用,该类目前只作用于代理请求
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class MyServletRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body;

	public MyServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		body = toByteArray(super.getInputStream());
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new RequestBodyCachingInputStream(body);
	}

	private class RequestBodyCachingInputStream extends ServletInputStream {
		private byte[] body;
		private int lastIndexRetrieved = -1;
		private ReadListener listener;

		public RequestBodyCachingInputStream(byte[] body) {
			this.body = body;
		}

		@Override
		public int read() throws IOException {
			if (isFinished()) {
				return -1;
			}
			int i = body[lastIndexRetrieved + 1];
			lastIndexRetrieved++;
			if (isFinished() && listener != null) {
				try {
					listener.onAllDataRead();
				} catch (IOException e) {
					listener.onError(e);
					throw e;
				}
			}
			return i;
		}

		@Override
		public boolean isFinished() {
			return lastIndexRetrieved == body.length - 1;
		}

		@Override
		public boolean isReady() {
			return isFinished();
		}

		@Override
		public void setReadListener(ReadListener listener) {
			if (listener == null) {
				throw new IllegalArgumentException("listener cann not be null");
			}
			if (this.listener != null) {
				throw new IllegalArgumentException("listener has been set");
			}
			this.listener = listener;
			if (!isFinished()) {
				try {
					listener.onAllDataRead();
				} catch (IOException e) {
					listener.onError(e);
				}
			} else {
				try {
					listener.onAllDataRead();
				} catch (IOException e) {
					listener.onError(e);
				}
			}
		}

		@Override
		public int available() throws IOException {
			return body.length - lastIndexRetrieved - 1;
		}

		@Override
		public void close() throws IOException {
			lastIndexRetrieved = body.length - 1;
			body = null;
		}
	}
	public byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}