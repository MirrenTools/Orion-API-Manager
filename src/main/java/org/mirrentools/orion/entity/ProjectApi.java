package org.mirrentools.orion.entity;


/**
 * API接口表
 * @author <a href="https://mirrentools.org">Mirren</a>
 */ 
public class ProjectApi {
	/** API的id */
	private String apiId; 
	/** 分组的id */
	private String groupId; 
	/** Method */
	private String method; 
	/** Path */
	private String path; 
	/** 接口的名称 */
	private String title; 
	/** 接口的描述 */
	private String description; 
	/** 请求类型 */
	private String consumes; 
	/** 请求参数 */
	private String parameters; 
	/** 请求body */
	private String body; 
	/** 响应类型 */
	private String produces; 
	/** 响应参数 */
	private String responses; 
	/** 接口是否已经过期 */
	private String deprecated; 
	/** 附加信息 */
	private String additional; 
	/** 附加文档 */
	private String externalDocs; 
	/** 拓展信息 */
	private String extensions; 
	/** 项目排序 */
	private Integer sorts; 
	/**
	 * 获取API的id
	 * 
	 * @return
	 */
	public String getApiId() {
		return apiId;
	}
	/**
	 * 设置API的id
	 * 
	 * @param apiId
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	/**
	 * 获取分组的id
	 * 
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * 设置分组的id
	 * 
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取Method
	 * 
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置Method
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取Path
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置Path
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取接口的名称
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置接口的名称
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取接口的描述
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置接口的描述
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取请求类型
	 * 
	 * @return
	 */
	public String getConsumes() {
		return consumes;
	}
	/**
	 * 设置请求类型
	 * 
	 * @param consumes
	 */
	public void setConsumes(String consumes) {
		this.consumes = consumes;
	}
	/**
	 * 获取请求参数
	 * 
	 * @return
	 */
	public String getParameters() {
		return parameters;
	}
	/**
	 * 设置请求参数
	 * 
	 * @param parameters
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	/**
	 * 获取请求body
	 * 
	 * @return
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置请求body
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取响应类型
	 * 
	 * @return
	 */
	public String getProduces() {
		return produces;
	}
	/**
	 * 设置响应类型
	 * 
	 * @param produces
	 */
	public void setProduces(String produces) {
		this.produces = produces;
	}
	/**
	 * 获取响应参数
	 * 
	 * @return
	 */
	public String getResponses() {
		return responses;
	}
	/**
	 * 设置响应参数
	 * 
	 * @param responses
	 */
	public void setResponses(String responses) {
		this.responses = responses;
	}
	/**
	 * 获取接口是否已经过期
	 * 
	 * @return
	 */
	public String getDeprecated() {
		return deprecated;
	}
	/**
	 * 设置接口是否已经过期
	 * 
	 * @param deprecated
	 */
	public void setDeprecated(String deprecated) {
		this.deprecated = deprecated;
	}
	/**
	 * 获取附加信息
	 * 
	 * @return
	 */
	public String getAdditional() {
		return additional;
	}
	/**
	 * 设置附加信息
	 * 
	 * @param additional
	 */
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	/**
	 * 获取附加文档
	 * 
	 * @return
	 */
	public String getExternalDocs() {
		return externalDocs;
	}
	/**
	 * 设置附加文档
	 * 
	 * @param externalDocs
	 */
	public void setExternalDocs(String externalDocs) {
		this.externalDocs = externalDocs;
	}
	/**
	 * 获取拓展信息
	 * 
	 * @return
	 */
	public String getExtensions() {
		return extensions;
	}
	/**
	 * 设置拓展信息
	 * 
	 * @param extensions
	 */
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	/**
	 * 获取项目排序
	 * 
	 * @return
	 */
	public Integer getSorts() {
		return sorts;
	}
	/**
	 * 设置项目排序
	 * 
	 * @param sorts
	 */
	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	@Override
	public String toString() {
		return "ProjectApi [apiId=" + apiId + " , groupId=" + groupId + " , method=" + method + " , path=" + path + " , title=" + title + " , description=" + description + " , consumes=" + consumes + " , parameters=" + parameters + " , body=" + body + " , produces=" + produces + " , responses=" + responses + " , deprecated=" + deprecated + " , additional=" + additional + " , externalDocs=" + externalDocs + " , extensions=" + extensions + " , sorts=" + sorts + "  ]";
	}
}
