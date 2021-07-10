package org.mirrentools.orion.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.ResultCode;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.entity.OrionHttpRequest;
import org.mirrentools.orion.service.HttpApiProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 代理的默认实现
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@Service
public class HttpApiProxyImpl implements HttpApiProxy {
	private static final Logger LOG = LogManager.getLogger(HttpApiProxyImpl.class);
	/** 配置文件是否允许未登录的用户使用代理 */
	@Value("${proxyAllowUnauthorized}")
	private String proxyAllowUnauthorized;

	@Override
	public Map<String, Object> getProxy(LoginSession loginSession, String url) {
		try {
			if (!"true".equalsIgnoreCase(proxyAllowUnauthorized)) {
				if (checkSession(loginSession)) {
					LOG.info("\n未登录用户请求使用代理-->检查不通过!");
					return ResultUtil.format403();
				}
			}
			if (url == null) {
				return ResultUtil.format(ResultCode.R412, "project url is null");
			}
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() != 200) {
				return ResultUtil.format(ResultCode.R500, response.getStatusLine());
			} else {
				String result = EntityUtils.toString(response.getEntity());
				return ResultUtil.format200(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.format(ResultCode.R500, e.getMessage());
		}
	}

//	@Override
//	public Map<String, Object> executeProxy(RequestData data) {
//		try {
//			OrionHttpRequest request = new OrionHttpRequest(data.getType(), data.getUrl() + data.getQueryParams());
//			System.out.println(data);
//			LOG.info("执行代理请求:" + data);
//			HttpClient client = HttpClientBuilder.create().build();
//			if (data.getHeaders() != null) {
//				JSONObject object = new JSONObject(data.getHeaders());
//				Iterator<?> keys = object.keys();
//				while (keys.hasNext()) {
//					String key = (String) keys.next();
//					if (object.has(key)) {
//						request.setHeader(key, object.getString(key));
//					}
//				}
//			}
//			if (data.getContentType() != null) {
//				request.setHeader("Content-Type", data.getContentType());
//			}
//			if (data.getData() != null) {
//				request.setEntity(new ByteArrayEntity(new JSONObject(data.getData()).toString().getBytes()));
//			}
//			HttpResponse response = client.execute(request);
//			if (response.getStatusLine().getStatusCode() != 200) {
//				return ResultUtil.format(ResultCode.R500, response.getStatusLine());
//			} else {
//				String result = EntityUtils.toString(response.getEntity());
//				return ResultUtil.format200(result);
//			}
//		} catch (Exception e) {
//			if (e instanceof URISyntaxException || e instanceof IllegalArgumentException) {
//				return ResultUtil.format(ResultCode.R1501, "\n无效的URL路径,如果有Path参数请填充Path参数\n" + e.getMessage());
//			} else if (e instanceof UnknownHostException) {
//				return ResultUtil.format(ResultCode.R1502, "\n无法识别主机:" + e.getMessage());
//			}
//			LOG.error("执行代理请求-->失败:", e);
//			return ResultUtil.format(ResultCode.R500, e.getMessage());
//		}
//	}

	@Override
	public void executeProxy(LoginSession loginSession, HttpServletRequest request, HttpServletResponse response) {
		if (!"true".equalsIgnoreCase(proxyAllowUnauthorized)) {
			if (checkSession(loginSession)) {
				response.setStatus(403);
				JSONObject result = new JSONObject();
				result.put("status", 403);
				result.put("type", "Proxy format!");
				result.put("msg", ResultCode.R403.msg());
				result.put("explain", ResultCode.R403.explain());
				try {
					response.getOutputStream().write(result.toString().getBytes("utf-8"));
					response.flushBuffer();
				} catch (IOException e1) {
				}
				LOG.info("\n未登录用户请求使用代理-->检查不通过!");
				return;
			}
		}
		try {
			response.setHeader("Content-Type", "application/json");
			String method = request.getMethod();
			String query = request.getQueryString();
			String url = request.getHeader("x-url");
			if (StringUtil.isNullOrEmpty(url)) {
				response.setStatus(502);
				JSONObject result = new JSONObject();
				result.put("status", 500);
				result.put("type", "Proxy format!");
				result.put("msg", "reuqest URL is null or empty");
				result.put("explain", "请求的URL为null或者为空");
				try {
					response.getOutputStream().write(result.toString().getBytes("utf-8"));
					response.flushBuffer();
				} catch (IOException e1) {
				}
				return;
			}
			LOG.info("proxy-url: " + url + (query == null ? "" : "?" + query));
			OrionHttpRequest req = new OrionHttpRequest(method, url + "?" + query);
			HttpClient client = HttpClientBuilder.create().build();
			String header = request.getHeader("x-header");
			if (header != null) {
				LOG.info("proxy-header: " + header);
				JSONObject hd = new JSONObject(header);
				Set<String> set = hd.keySet();
				for (String key : set) {
					req.addHeader(key, hd.getString(key));
				}
			}

			String xtype = request.getHeader("x-type");
			if (!StringUtil.isNullOrEmpty(xtype)) {
				LOG.info("proxy-type: " + xtype);
				req.setHeader("Content-Type", xtype);
			}
			int length = request.getContentLength();
			if (length > 0) {
				LOG.info("body length:" + length);
				req.setEntity(new ByteArrayEntity(readInputStream(request.getInputStream()).toByteArray()));
			}
			HttpResponse resp = client.execute(req);
			HeaderIterator iterator = resp.headerIterator();
			while (iterator.hasNext()) {
				Header h = iterator.nextHeader();
				response.setHeader(h.getName(), h.getValue());
			}
			response.setStatus(resp.getStatusLine().getStatusCode());
			resp.getEntity().writeTo(response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(502);
			JSONObject result = new JSONObject();
			result.put("status", 502);
			result.put("msg", "Proxy format!");
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
		LOG.info("proxy-body:\n" + builder);
		return outputStream;
	}

	/**
	 * 检查用户是否不满足条件
	 * 
	 * @param loginSession
	 * @return
	 */
	private boolean checkSession(LoginSession loginSession) {
		return loginSession == null || loginSession.getUid() == null || loginSession.getRole() == null;
	}
}
