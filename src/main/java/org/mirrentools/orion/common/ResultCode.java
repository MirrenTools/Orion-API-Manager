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
	/** 状态码:202, "The operation is complete, but you need to check whether it is successful", "操作完成,但需要你检查是否成功" */
	R202(202, "The operation is complete, but you need to check whether it is successful", "操作完成,但需要你检查是否成功"),
	/** 状态码:401, "Login timeout, please login again", "登录超时,请重新登录!" */
	R401(401, "Login timeout, please login again", "登录超时,请重新登录"),
	/** 状态码:402, "Login failed, invalid account or password", "登录失败,账号或密码错误" */
	R402(402, "Login failed, invalid account or password", "登录失败,账号或密码错误"),
	/** 状态码:403, "Illegal operation or no permission", "非法操作或者没有权限" */
	R403(403, "Illegal operation or no permission", "非法操作或者没有权限"),
	/** 状态码:412, "Please fill in all required parameters", "请按要求填写所有必填项" */
	R412(412, "Please fill in all required parameters", "请按要求填写所有必填项"),
	/** 状态码:500, "failed", "失败" */
	R500(500, "Failed", "失败"),
	/** 状态码:555, "failed. Please try again later. Please check the console for more information", "请稍后重试,更多信息请查看控制台" */
	R555(555, "Failed. Please try again later. Please check the console for more information", "请稍后重试,更多信息请查看控制台"),
	/** 状态码:1303, "Failed to get the Captcha. Please try again later", "获取验证码失败,请稍后重试" */
	R1303(1303, "Failed to get the Captcha. Please try again later", "获取验证码失败,请稍后重试"),
	/** 状态码:1304, "The Captcha is incorrect, please select again", "验证码错误,请重新选择" */
	R1304(1304, "The Captcha is incorrect, please select again", "验证码错误,请重新选择"),
	/** 状态码:*/
	R1001(1001, "The user's account already exists!", "该用户的账号已存在!"),
	/** 状态码:*/
	R1002(1002, "Ordinary administrator cannot create user with administrator role!", "普通管理员无法创建管理员角色的用户!"),
	/** 状态码:*/
	R1003(1003, "Failed. invalid role", "失败,无效的角色"),
	/** 状态码:1004, "Login failed, account password and Captcha cannot be empty!", "登录失败,账号密码与验证码不能为空!" */
	R1004(1004, "Login failed, account password and Captcha cannot be empty!", "登录失败,账号密码与验证码不能为空!"),
	/** 状态码: */
	R1010(1010, "Ordinary administrators can only delete ordinary users", "普通管理员只能删除普通用户"),
	/** 状态码: */
	R1011(1011, "Only administrators can delete users", "只有管理员可以删除用户"),
	/** 状态码: */
	R1100(1100, "The tags is referenced by the project and cannot be deleted", "该标签已被项目引用,无法进行删除"),
	/** 状态码: */
	R1101(1101, "The tags is referenced by the users and cannot be deleted", "该标签下有用户,无法进行删除"),
	/** 状态码: */
	R1501(1501, "Invalid URL path. If there is a path parameter, please fill in the path parameter", "无效的URL路径,如果有Path参数请填充Path参数"),
	/** 状态码: */
	R1502(1502, "Unrecognized host or address", "无法识别主机"),
	/** 状态码: */
	R9999(403, "Login timeout, please login again!", "登录超时,请重新登录!"),
	;

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
