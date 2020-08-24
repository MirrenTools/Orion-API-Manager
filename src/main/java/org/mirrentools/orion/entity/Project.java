package org.mirrentools.orion.entity;

/**
 * 项目实体类
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class Project {
	/** 项目的id */
	private String key;
	/** 项目的名字 */
	private String name;
	/** 项目的版本 */
	private String versions;
	/** 项目的描述 */
	private String description;
	/**
	 * 可用的服务地址(JsonArray(JsonObject)):<br>
	 * url(String): 服务地址: 协议://主机:端口/更路径<br>
	 * description(String): 这个服务的描述
	 * 
	 */
	private String servers;
	/** 拓展文档 */
	private String externalDocs;
	/** 联系名称 */
	private String contactName;
	/** 联系信息 */
	private String contactInfo;
	/** 拓展信息 */
	private String extensions;
	/** 最后操作时间 */
	private Integer sorts;
	/** 最后操作时间 */
	private Long lastTime;

	/**
	 * 实例化
	 */
	public Project() {
		super();
	}

	/**
	 * 获取key
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取versions
	 * 
	 * @return
	 */
	public String getVersions() {
		return versions;
	}

	/**
	 * 设置versions
	 * 
	 * @param versions
	 */
	public void setVersions(String versions) {
		this.versions = versions;
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
	 * 获取服务地址
	 * 
	 * @return
	 */
	public String getServers() {
		return servers;
	}

	/**
	 * 设置服务地址
	 * 
	 * @param servers
	 */
	public void setServers(String servers) {
		this.servers = servers;
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
	 * 获取contactName
	 * 
	 * @return
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * 设置contactName
	 * 
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * 获取contactInfo
	 * 
	 * @return
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * 设置contactInfo
	 * 
	 * @param contactInfo
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
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

	/**
	 * 获取lastTime
	 * 
	 * @return
	 */
	public Long getLastTime() {
		return lastTime;
	}

	/**
	 * 设置lastTime
	 * 
	 * @param lastTime
	 */
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}

	@Override
	public String toString() {
		return "Project [key=" + key + ", name=" + name + ", versions=" + versions + ", description=" + description + ", servers=" + servers
				+ ", externalDocs=" + externalDocs + ", contactName=" + contactName + ", contactInfo=" + contactInfo + ", extensions=" + extensions
				+ ", sorts=" + sorts + ", lastTime=" + lastTime + "]";
	}

}
