package org.mirrentools.orion.entity;


/**
 * API接口模板
 * @author 
 */ 
public class ProjectApiTemplate {
	/** 模板的id */
	private String tid; 
	/** 模板所属用户的id */
	private String uid; 
	/** 模板的名称 */
	private String name; 
	/** API基本信息 */
	private String api; 
	/** API中的请求参数 */
	private String parameters; 
	/** API中的响应参数 */
	private String responses; 
	/** 模板创建的时间 */
	private Long ctime; 
	/**
	 * 获取模板的id
	 * 
	 * @return
	 */
	public String getTid() {
		return tid;
	}
	/**
	 * 设置模板的id
	 * 
	 * @param tid
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	/**
	 * 获取模板所属用户的id
	 * 
	 * @return
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置模板所属用户的id
	 * 
	 * @param uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取模板的名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置模板的名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取API基本信息
	 * 
	 * @return
	 */
	public String getApi() {
		return api;
	}
	/**
	 * 设置API基本信息
	 * 
	 * @param api
	 */
	public void setApi(String api) {
		this.api = api;
	}
	/**
	 * 获取API中的请求参数
	 * 
	 * @return
	 */
	public String getParameters() {
		return parameters;
	}
	/**
	 * 设置API中的请求参数
	 * 
	 * @param parameters
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	/**
	 * 获取API中的响应参数
	 * 
	 * @return
	 */
	public String getResponses() {
		return responses;
	}
	/**
	 * 设置API中的响应参数
	 * 
	 * @param responses
	 */
	public void setResponses(String responses) {
		this.responses = responses;
	}
	/**
	 * 获取模板创建的时间
	 * 
	 * @return
	 */
	public Long getCtime() {
		return ctime;
	}
	/**
	 * 设置模板创建的时间
	 * 
	 * @param ctime
	 */
	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "ProjectApiTemplate [tid=" + tid + " , uid=" + uid + " , name=" + name + " , api=" + api + " , parameters=" + parameters + " , responses=" + responses + " , ctime=" + ctime + "  ]";
	}
}
