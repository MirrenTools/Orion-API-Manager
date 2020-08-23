package org.mirrentools.orion.common;

import org.json.JSONObject;

/**
 * WebSocket响应协议
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class WebSocketBase {

	/** 操作类型,当前默认只有1000 */
	public final static int TYPE = 1000;

	/** 状态码-成功 */
	protected final static int C200 = 200;
	/** 状态码-登录超时 */
	protected final static int C401 = 401;
	/** 状态码-失败 */
	protected final static int C500 = 500;
	/** 状态码-操作完成 */
	protected final static int C999 = 999;

	/** 消息说明成功 */
	protected final static String M200 = "成功!";
	/** 消息说明登录超时 */
	protected final static String M401 = "登录超时,请重新登录!";
	/** 消息说明失败 */
	protected final static String M500 = "失败!";
	/** 消息说明操作完成 */
	protected final static String M999 = "操作完成!";

	/** 协议中的code */
	private final static String KEY_CODE = "code";
	/** 协议中的type */
	private final static String KEY_TYPE = "type";
	/** 协议中的data */
	private final static String KEY_DATA = "data";
	/** 协议中的msg */
	private final static String KEY_MSG = "msg";

	/**
	 * 操作完成
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static String end() {
		return result(C999, TYPE, M200, null);
	}

	/**
	 * 成功状态
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static String success(int code, String data) {
		return result(code, TYPE, M200, data);
	}

	/**
	 * 成功状态
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String success(int code, String msg, String data) {
		return result(code, TYPE, msg, data);
	}

	/**
	 * 异常或失败
	 * 
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String failed(String msg) {
		return result(C500, TYPE, msg, null);
	}

	/**
	 * 异常或失败
	 * 
	 * @param code
	 * @return
	 */
	public static String failed(int code) {
		return result(code, TYPE, M500, null);
	}

	/**
	 * 异常或失败
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static String failed(int code, String data) {
		return result(code, TYPE, M500, data);
	}

	/**
	 * 异常或失败
	 * 
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String failed(String msg, String data) {
		return result(C500, TYPE, msg, data);
	}

	/**
	 * 异常或失败
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String failed(int code, String msg, String data) {
		return result(code, TYPE, msg, data);
	}

	/**
	 * 响应信息
	 * 
	 * @param code
	 * @param type
	 * @param state
	 * @param msg
	 * @param data
	 * @return
	 */
	protected static String result(int code, int type, String msg, String data) {
		JSONObject result = new JSONObject();
		result.put(KEY_CODE, code);
		result.put(KEY_TYPE, type);
		result.put(KEY_MSG, msg);
		result.put(KEY_DATA, data);
		return result.toString();
	}
}
