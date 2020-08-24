package org.mirrentools.orion.entity;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
/**
 * HttpClient的请求类
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class OrionHttpRequest extends HttpEntityEnclosingRequestBase {
	/**
	 * 请求的方法的类型
	 */
	private String requestMethod = "GET";

	public OrionHttpRequest() {
		super();
	}

	public OrionHttpRequest(final URI uri) {
		super();
		setURI(uri);
	}

	public OrionHttpRequest(final String uri) {
		super();
		setURI(URI.create(uri));
	}
	public OrionHttpRequest(final String method, final String uri) {
		super();
		this.requestMethod = method;
		setURI(URI.create(uri));
	}

	@Override
	public String getMethod() {
		if (requestMethod == null) {
			requestMethod = "GET";
		}
		return requestMethod.toUpperCase();
	}

}
