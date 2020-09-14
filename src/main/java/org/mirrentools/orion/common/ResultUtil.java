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
