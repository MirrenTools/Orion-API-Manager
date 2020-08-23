package org.mirrentools.orion.common;

import org.json.JSONObject;

/**
 * WebSocket消息协议与消息工具<br>
 * 请求协议:<br>
 * type(int):操作类型<br>
 * data(Object):数据信息<br>
 * 响应协议:<br>
 * code(int):状态码<br>
 * type(int):操作类型<br>
 * msg(String):操作说明<br>
 * data(Object):数据信息<br>
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class WebSocket extends WebSocketBase {
	/** 状态码-404:无法识别的指令! */
	public final static int UNRECOGNIZED = 404;
	/** 状态码-1000:保存项目检查1: project不能为空,应为项目的json字符串! */
	public final static int CHECK_PROJECT_JSON = 1000;
	/** 状态码-1001:保存项目检查2: 项目名称,服务集不能为空! */
	public final static int CHECK_PROJECT_NAME_SERVERS = 1001;
	/** 状态码-1002:保存项目,data=项目的名称 */
	public final static int PROJECT_SAVEING = 1002;
	/** 状态码-1003:保存项目成功,data=项目的名称 */
	public final static int PROJECT_SAVED = 1003;
	/** 状态码-1004:保存项目异常,data=项目的名称 */
	public final static int PROJECT_SAVE_EXCEPTION = 1004;
	/** 状态码-1005:保存分组,data:{name:"分组名称",index:当前第几个,count:总共多少个,result:受影响行数} */
	public final static int GROUP_SAVED = 1005;
	/** 状态码-1006:保存API,data:{name:"API名称",index:当前第几个,count:总共多少个,result:受影响行数} */
	public final static int API_SAVED = 1006;

	/**
	 * 权限不足或没有权限
	 * 
	 * @param data
	 * @return
	 */
	public static String failed401() {
		return failed(C401, M401, null);
	}

	/**
	 * 保存进度的模型,返回{name:"分组名称",index:当前第几个,count:总共多少个,result:受影响行数}
	 * 
	 * @param name
	 * @param index
	 * @param count
	 * @param result
	 * @return
	 */
	public static String progressModel(String name, int index, int count, int result) {
		JSONObject object = new JSONObject();
		object.put("name", name).put("index", index).put("count", count).put("result", result);
		return object.toString();
	}

}
