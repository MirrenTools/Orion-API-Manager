package org.mirrentools.orion.entity;


/**
 * API接口表
 * @author <a href="https://mirrentools.org">Mirren</a>
 */ 
public class ProjectApi {
	/**1.0.1版本拓展属性:隐藏API的一个属性*/
	public static final String EXT_HIDE_API="x_api_hide";
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
	/** 接口的描述(支持HTML) */
	private String description; 
	/** 请求类型(JsonArray(String)) */
	private String consumes; 
	/** 请求参数(JsonArray(JsonObject)):
	 * required(Boolean): 是否必填,
	 * in(enum): 参数位置(query|body|path|header),
	 * type(enum): 参数类型(string|int|long|object|array|float|double|number|boolean),
	 * name(String): 参数的名称,
	 * description(String): 参数的描述(支持HTML),
	 * def(String): 默认值,
	 * enums(JsonArray(String)): 枚举值,
	 * minLength(int): 字符串类型最小长度,
	 * maxLength(int): 字符串类型最大长度,
	 * minimum(Number): 数值最小值,
	 * maximum(Number): 数值最大值,
	 * pattern(String): 正则表达式,
	 * items(JsonArray(JsonObject)): 响应参数:
	 *   type(String): 响应类型与parameters的类型一致,
	 *   name(String): 参数的名称,
	 *   description(String): 参数的描述(支持HTML),
	 *   items(JsonArray(JsonObject)): 与items一致
	 */
	private String parameters; 
	/** 请求body */
	private String body; 
	/** 响应类型(JsonArray(String)) */
	private String produces; 
	/** 响应参数(JsonArray(JsonObject)): 
	 * 	[{
	 *    status(Integer): 状态码,
	 *    msg(String): 状态信息,
	 *    data(JsonArray(JsonObject)): 响应参数:
	 *      in(enum): 响应位置(header|body),
	 *      type(String): 响应类型与parameters的类型一致,
	 *      name(String): 参数的名称,
	 *      description(String): 参数的描述(支持HTML),
	 *      items(JsonArray(JsonObject)): 与data一致
	 *  }]
	 */
	private String responses; 
	/** 接口是否已经过期(Boolean) */
	private String deprecated; 
	/** 附加信息-已弃用 */
	private String additional; 
	/**
	 * 附加文档(JsonObject):
	 * description(String):附加文档说明
	 * url(String): 附加文档路径
	 */
	private String externalDocs; 
	/**
	 * 拓展信息String|String(JsonArray)|String(JsonObject) <br>
	 * @since 1.0.1 拓展属性"x_api_hide":0,存在该属性则代表该API已回收,系统通过判断not like '%"x_api_hide"%'判断是否为已回收接口
	 */
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
	 * @since 1.0.1 拓展属性"x_api_hide":0,存在该属性则代表该API已回收,系统通过判断not like '%"x_api_hide"%'判断是否为已回收接口
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
