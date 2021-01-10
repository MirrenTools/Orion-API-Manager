package org.mirrentools.orion.common;

/**
 * 响应状态码
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public enum ResultCode {
	/** 状态码:200, "success", "成功" */
	DEFAULT(200, "Succeeded", "成功"),
	/** 状态码:200, "success", "成功" */
	R200(200, "Succeeded", "成功"),
	/**
	 * 状态码:202, <br>
	 * "The operation is complete, but you need to check whether it is successful",
	 * <br>
	 * "操作完成,但需要你检查是否成功"
	 */
	R202(202, "The operation is complete, but you need to check whether it is successful", "操作完成,但需要你检查是否成功"),
	/**
	 * 状态码:401,<br>
	 * "Login timeout, please login again",<br>
	 * "登录超时,请重新登录!"
	 */
	R401(401, "Login timeout, please login again", "登录超时,请重新登录"),
	/**
	 * 状态码:402, <br>
	 * "Login failed, invalid account or password", <br>
	 * "登录失败,账号或密码错误"
	 */
	R402(402, "Login failed, invalid account or password", "登录失败,账号或密码错误"),
	/**
	 * 状态码:403,<br>
	 * "Illegal operation or no permission", <br>
	 * "非法操作或者没有权限"
	 */
	R403(403, "Illegal operation or no permission,if you have the permission, please login again",
			"非法操作或者没有权限,如果你有该权限请重新登录"),
	/**
	 * 状态码:404,<br>
	 * "The resource does not exist", <br>
	 * "资源不存在"
	 */
	R404(404, "The resource does not exist", "资源不存在"),
	/**
	 * 状态码:405,<br>
	 * "View password is incorrect or invalid, please re-enter", <br>
	 * "查看密码错误,或查看密码无效,请重新输入"
	 */
	R405(405, "View password is incorrect or invalid, please re-enter", "查看密码错误,或查看密码无效,请重新输入"),
	/**
	 * 状态码:412, <br>
	 * "Please fill in all required parameters", <br>
	 * "请按要求填写所有必填项"
	 */
	R412(412, "Please fill in all required parameters", "请按要求填写所有必填项"),
	/**
	 * 状态码:413, <br>
	 * "Invalid request parameter", <br>
	 * "无效的请求参数"
	 */
	R413(413, "Invalid request parameter", "无效的请求参数"),
	/** 状态码:500, "failed", "失败" */
	R500(500, "Failed", "失败"),
	/**
	 * 状态码:555, <br>
	 * "failed. Please try again later. Please check the console for more
	 * information",<br>
	 * "请稍后重试,更多信息请查看控制台"
	 */
	R555(555, "Failed. Please try again later. Please check the console for more information", "请稍后重试,更多信息请查看控制台"),
	/**
	 * 状态码:1303, <br>
	 * "Failed to get the Captcha. Please try again later",<br>
	 * "获取验证码失败,请稍后重试"
	 */
	R1303(1303, "Failed to get the Captcha. Please try again later", "获取验证码失败,请稍后重试"),
	/**
	 * 状态码:1304, <br>
	 * "The Captcha is incorrect, please select again", <br>
	 * "验证码错误,请重新选择"
	 */
	R1304(1304, "The Captcha is incorrect, please select again", "验证码错误,请重新选择"),
	/**
	 * 状态码:1001, <br>
	 * "The user's account already exists!", <br>
	 * "该用户的账号已存在!"
	 */
	R1001(1001, "The user's account already exists!", "该用户的账号已存在!"),
	/**
	 * 状态码:1002, <br>
	 * "Ordinary administrator cannot create user with administrator role!", <br>
	 * "普通管理员无法创建管理员角色的用户!"
	 */
	R1002(1002, "Ordinary administrator cannot create user with administrator role!", "普通管理员无法创建管理员角色的用户!"),
	/**
	 * 状态码:1003,<br>
	 * "Failed. invalid role", <br>
	 * "失败,无效的角色"
	 */
	R1003(1003, "Failed. invalid role", "失败,无效的角色"),
	/**
	 * 状态码:1004, <br>
	 * "Login failed, account password and Captcha cannot be empty!",<br>
	 * "登录失败,账号密码与验证码不能为空!"
	 */
	R1004(1004, "Login failed, account password and Captcha cannot be empty!", "登录失败,账号密码与验证码不能为空!"),
	/**
	 * 状态码:1010, <br>
	 * "Ordinary administrators can only delete ordinary users",<br>
	 * "普通管理员只能删除普通用户"
	 */
	R1010(1010, "Ordinary administrators can only delete ordinary users", "普通管理员只能删除普通用户"),
	/**
	 * 状态码:1011, <br>
	 * "Only administrators can delete users", <br>
	 * "只有管理员可以删除用户"
	 */
	R1011(1011, "Only administrators can delete users", "只有管理员可以删除用户"),
	/**
	 * 状态码:1100, <br>
	 * "The tags is referenced by the project and cannot be deleted",<br>
	 * "该标签已被项目引用,无法进行删除"
	 */
	R1100(1100, "The tags is referenced by the project and cannot be deleted", "该标签已被项目引用,无法进行删除"),
	/**
	 * 状态码:1101, <br>
	 * "The tags is referenced by the users and cannot be deleted",<br>
	 * "该标签下有用户,无法进行删除"
	 */
	R1101(1101, "The tags is referenced by the users and cannot be deleted", "该标签下有用户,无法进行删除"),
	/**
	 * 状态码: 1501, <br>
	 * "Invalid URL path. If there is a path parameter, please fill in the path
	 * parameter", <br>
	 * "无效的URL路径,如果有Path参数请填充Path参数"
	 */
	R1501(1501, "Invalid URL path. If there is a path parameter, please fill in the path parameter",
			"无效的URL路径,如果有Path参数请填充Path参数"),
	/**
	 * 状态码: 1502, <br>
	 * "Unrecognized host or address", <br>
	 * "无法识别主机"
	 */
	R1502(1502, "Unrecognized host or address", "无法识别主机"),;

	/** 状态信息 */
	private int code;
	/** 状态信息 */
	private String msg;
	/** 提示信息 */
	private String explain;

	private ResultCode(int code, String msg, String explain) {
		this.code = code;
		this.msg = msg;
		this.explain = explain;
	}

	/**
	 * 获取状态码
	 * 
	 * @return
	 */
	public int code() {
		return code;
	}

	/**
	 * 获取状态信息
	 * 
	 * @return
	 */
	public String msg() {
		return msg;
	}

	/**
	 * 获取描述信息
	 * 
	 * @return
	 */
	public String explain() {
		return explain;
	}

}
