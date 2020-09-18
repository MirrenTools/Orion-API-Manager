package org.mirrentools.orion.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * 返回结果工具
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class ResultUtil {

	/**
	 * 格式化为成功 {@link org.mirrentools.orion.common.ResultCode#R200}
	 * 
	 * @return
	 */
	public static Map<String, Object> format200(Object data) {
		return format(ResultCode.R200, data);
	}

	/**
	 * 格式化为登录超时 {@link org.mirrentools.orion.common.ResultCode#R401}
	 * 
	 * @return
	 */
	public static Map<String, Object> format401() {
		return format(ResultCode.R401);
	}

	/**
	 * 格式化为没有权限 {@link org.mirrentools.orion.common.ResultCode#R403}
	 * 
	 * @return
	 */
	public static Map<String, Object> format403() {
		return format(ResultCode.R403);
	}

	/**
	 * 格式化为资源不存在 {@link org.mirrentools.orion.common.ResultCode#R404}
	 * 
	 * @return
	 */
	public static Map<String, Object> format404() {
		return format(ResultCode.R404);
	}

	/**
	 * 响应数据
	 * 
	 * @param status 响应的状态
	 * @param data   响应的数据
	 * @return
	 */
	public static Map<String, Object> format(ResultCode status) {
		if (status == null) {
			status = ResultCode.DEFAULT;
		}
		Map<String, Object> result = new HashMap<>();
		result.put("code", status.code());
		result.put("msg", status.msg());
		result.put("explain", status.explain());
		return result;
	}

	/**
	 * 响应数据
	 * 
	 * @param status 响应的状态
	 * @param data   响应的数据
	 * @return
	 */
	public static Map<String, Object> format(ResultCode status, Object data) {
		if (status == null) {
			status = ResultCode.DEFAULT;
		}
		Map<String, Object> result = new HashMap<>();
		result.put("code", status.code());
		result.put("msg", status.msg());
		result.put("explain", status.explain());
		result.put("data", data);
		return result;
	}

	/**
	 * 返回格式化为JSON字符串
	 * 
	 * @param status 响应的状态
	 * @return
	 * @throws Exception
	 */
	public static String formatAsString(ResultCode status) {
		JSONObject result = new JSONObject();
		result.put("code", status.code());
		result.put("msg", status.msg());
		result.put("explain", status.explain());
		return result.toString();
	}

	/**
	 * 返回格式化为JSON字符串
	 * 
	 * @param status 响应的状态
	 * @param data   响应的数据
	 * @return
	 * @throws Exception
	 */
	public static String formatAsString(ResultCode status, Object data) {
		JSONObject result = new JSONObject();
		result.put("code", status.code());
		result.put("msg", status.msg());
		result.put("explain", status.explain());
		result.put("data", data);
		return result.toString();
	}

}
