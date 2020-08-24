package org.mirrentools.orion.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.entity.OrionHttpRequest;
import org.mirrentools.orion.entity.RequestData;
import org.mirrentools.orion.service.HttpApiProxy;
import org.springframework.stereotype.Service;
/**
 * 代理的默认实现
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@Service
public class HttpApiProxyImpl implements HttpApiProxy {

	@Override
	public Map<String, Object> getProxy(String url) {
		try {
			if (url == null) {
				return ResultUtil.failed("project url is null");
			}
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() != 200) {
				return ResultUtil.failed(response.getStatusLine());
			} else {
				String result = EntityUtils.toString(response.getEntity());
				return ResultUtil.succeed(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> executeProxy(RequestData data) {
		try {
			OrionHttpRequest request = new OrionHttpRequest(data.getType(), data.getUrl() + data.getQueryParams());
			System.out.println(data);
			HttpClient client = HttpClientBuilder.create().build();
			if (data.getHeaders() != null) {
				JSONObject object = new JSONObject(data.getHeaders());
				Iterator<?> keys = object.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					if (object.has(key)) {
						request.setHeader(key, object.getString(key));
					}
				}
			}
			if (data.getContentType() != null) {
				request.setHeader("Content-Type", data.getContentType());
			}
			if (data.getData() != null) {
				request.setEntity(new ByteArrayEntity(new JSONObject(data.getData()).toString().getBytes()));
			}
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200) {
				return ResultUtil.failed(response.getStatusLine());
			} else {
				String result = EntityUtils.toString(response.getEntity());
				return ResultUtil.succeed(result);
			}
		} catch (Exception e) {
			if (e instanceof URISyntaxException || e instanceof IllegalArgumentException) {
				return ResultUtil.failed("\n无效的URL路径,如果有Path参数请填充Path参数\n" + e.getMessage());
			} else if (e instanceof UnknownHostException) {
				return ResultUtil.failed("\n无法识别主机:" + e.getMessage());
			}
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public void executeProxy(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Content-Type", "application/json");
			String method = request.getMethod();
			String query = request.getQueryString();
			String url = request.getHeader("x-url");
			if (StringUtil.isNullOrEmpty(url)) {
				response.setStatus(502);
				JSONObject result = new JSONObject();
				result.put("status", 500);
				result.put("msg", "Proxy failed!");
				result.put("err", "reuqest URL is null or empty");
				try {
					response.getOutputStream().write(result.toString().getBytes("utf-8"));
					response.flushBuffer();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;
			}
			System.out.println("proxy-url: " + url + (query == null ? "" : "?" + query));
			OrionHttpRequest req = new OrionHttpRequest(method, url + "?" + query);
			HttpClient client = HttpClientBuilder.create().build();
			String header = request.getHeader("x-header");
			if (header != null) {
				System.out.println("proxy-header: " + header);
				JSONObject hd = new JSONObject(header);
				Set<String> set = hd.keySet();
				for (String key : set) {
					req.addHeader(key, hd.getString(key));
				}
			}

			String xtype = request.getHeader("x-type");
			if (!StringUtil.isNullOrEmpty(xtype)) {
				System.out.println("proxy-type: " + xtype);
				req.setHeader("Content-Type", xtype);
			}
			int length = request.getContentLength();
			if (length > 0) {
				System.out.println("body length:" + length);
				req.setEntity(new ByteArrayEntity(readInputStream(request.getInputStream()).toByteArray()));
			}
			HttpResponse resp = client.execute(req);
			HeaderIterator iterator = resp.headerIterator();
			while (iterator.hasNext()) {
				Header h = iterator.nextHeader();
				response.addHeader(h.getName(), h.getValue());
			}
			response.setStatus(resp.getStatusLine().getStatusCode());
			resp.getEntity().writeTo(response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(502);
			JSONObject result = new JSONObject();
			result.put("status", 502);
			result.put("msg", "Proxy failed!");
			String message = e.getMessage();
			if (message != null) {
				result.put("err", message);
			} else {
				if (e instanceof ClientProtocolException) {
					result.put("err", "CLIENT PROTOCOL EXCEPTION");
				}
			}
			try {
				response.getOutputStream().write(result.toString().getBytes("utf-8"));
				response.flushBuffer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 读取InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	private ByteArrayOutputStream readInputStream(InputStream in) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = in.read()) != -1) {
			outputStream.write(ch);
			builder.append((char) ch);
		}
		System.out.println("proxy-body:\n" + builder);
		return outputStream;
	}
}
