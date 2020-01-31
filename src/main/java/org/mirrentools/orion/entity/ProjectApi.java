package org.mirrentools.orion.entity;

/**
 * 接口实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class ProjectApi {
	/** API的id */
	private String apiId;
	/** API分组的id */
	private String groupId;
	/** 请求的方法 */
	private String method;
	/** 请求的path */
	private String path;
	/** API的标题 */
	private String title;
	/** API的描述 */
	private String description;
	/** API的请求consumes */
	private String consumes;
	/** API的请求参数 */
	private String parameters;
	/** API的响应produces */
	private String produces;
	/** API的响应参数 */
	private String responses;
	/** API是否已经过时 */
	private String deprecated;
	/** 附加说明 */
	private String additional;
	/** 拓展文档 */
	private String externalDocs;
	/** 拓展属性 */
	private String extensions;

	/**
	 * 实例化
	 */
	public ProjectApi() {
		super();
	}

	/**
	 * 获取apiId
	 * 
	 * @return
	 */
	public String getApiId() {
		return apiId;
	}

	/**
	 * 设置apiId
	 * 
	 * @param apiId
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * 获取groupId
	 * 
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置groupId
	 * 
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取method
	 * 
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 设置method
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 获取path
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置path
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取title
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取consumes
	 * 
	 * @return
	 */
	public String getConsumes() {
		return consumes;
	}

	/**
	 * 设置consumes
	 * 
	 * @param consumes
	 */
	public void setConsumes(String consumes) {
		this.consumes = consumes;
	}

	/**
	 * 获取parameters
	 * 
	 * @return
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * 设置parameters
	 * 
	 * @param parameters
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * 获取produces
	 * 
	 * @return
	 */
	public String getProduces() {
		return produces;
	}

	/**
	 * 设置produces
	 * 
	 * @param produces
	 */
	public void setProduces(String produces) {
		this.produces = produces;
	}

	/**
	 * 获取responses
	 * 
	 * @return
	 */
	public String getResponses() {
		return responses;
	}

	/**
	 * 设置responses
	 * 
	 * @param responses
	 */
	public void setResponses(String responses) {
		this.responses = responses;
	}

	/**
	 * 获取deprecated
	 * 
	 * @return
	 */
	public String getDeprecated() {
		return deprecated;
	}

	/**
	 * 设置deprecated
	 * 
	 * @param deprecated
	 */
	public void setDeprecated(String deprecated) {
		this.deprecated = deprecated;
	}
	/**
	 * 获取附加说明
	 * 
	 * @return
	 */
	public String getAdditional() {
		return additional;
	}

	/**
	 * 设置附加说明
	 * 
	 * @param additional
	 */
	public void setAdditional(String additional) {
		this.additional = additional;
	}

	/**
	 * 获取externalDocs
	 * 
	 * @return
	 */
	public String getExternalDocs() {
		return externalDocs;
	}

	/**
	 * 设置externalDocs
	 * 
	 * @param externalDocs
	 */
	public void setExternalDocs(String externalDocs) {
		this.externalDocs = externalDocs;
	}

	/**
	 * 获取extensions
	 * 
	 * @return
	 */
	public String getExtensions() {
		return extensions;
	}

	/**
	 * 设置extensions
	 * 
	 * @param extensions
	 */
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	@Override
	public String toString() {
		return "ProjectApi [apiId=" + apiId + " , groupId=" + groupId + " , method=" + method + " , path=" + path + " , title=" + title
				+ " , description=" + description + " , consumes=" + consumes + " , parameters=" + parameters + " , produces=" + produces
				+ " , responses=" + responses + " , deprecated=" + deprecated + " , externalDocs=" + externalDocs + " , extensions=" + extensions
				+ "  ]";

	}

}
