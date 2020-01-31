package org.mirrentools.orion.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.entity.OrionHttpRequest;
import org.mirrentools.orion.entity.RequestData;
import org.mirrentools.orion.service.HttpApiProxy;
import org.springframework.stereotype.Service;

@Service
public class HttpApiProxyImpl implements HttpApiProxy {

	@Override
	public Map<String, Object> getProxy(String url) {
		try {
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
			String method = request.getMethod();
			String query = request.getQueryString();
			String url = request.getHeader("x-url");
			System.out.println("proxy-url: " + url + "?" + query);
			OrionHttpRequest req = new OrionHttpRequest(method, url + "?" + query);
			HttpClient client = HttpClientBuilder.create().build();
			String header = request.getHeader("x-header");
			System.out.println("proxy-header: " + header);
			if (header != null) {
				JSONObject hd = new JSONObject(header);
				Set<String> set = hd.keySet();
				for (String key : set) {
					req.addHeader(key, hd.getString(key));
					
				}
			}
			String xtype = request.getHeader("x-type");
			System.out.println("proxy-type: " + xtype);
			if (xtype != null && "x-www-form-urlencoded".equals(xtype)) {
				String body = request.getParameter("x-wfubody");
				System.out.println("proxy-body: " + body);
				if (body != null) {
					req.setHeader("Content-Type", "application/x-www-form-urlencoded");
					List<BasicNameValuePair> list = new ArrayList<>();
					JSONObject hd = new JSONObject(body);
					Set<String> set = hd.keySet();
					for (String key : set) {
						list.add(new BasicNameValuePair(key, hd.getString(key)));
					}
					req.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
				}
			} else {
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
			result.put("msg", "代理请求失败!");
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
		int ch;
		while ((ch = in.read()) != -1) {
			outputStream.write(ch);
		}
		return outputStream;
	}
}
