package org.mirrentools.orion.entity;

import java.util.List;

/**
 * 接口分组的实体类
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class ProjectApiGroup {
	/** 分组的id */
	private String groupId;
	/** 项目的id */
	private String projectId;
	/** 分组的名字 */
	private String name;
	/** 分组的简单描述 */
	private String summary;
	/** 分组的详细描述 */
	private String description;
	/**
	 * 分组的拓展文档(JsonObject):<br>
	 * description(String):附加文档说明<br>
	 * url(String): 附加文档路径
	 */
	private String externalDocs;
	/** 分组的拓展属性 */
	private String extensions;
	/** 分组的排序 */
	private Integer sorts;

	/** 该分组下的所有接口 */
	private List<ProjectApi> apis;

	/**
	 * 实例化
	 */
	public ProjectApiGroup() {
		super();
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
	 * 获取projectId
	 * 
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * 设置projectId
	 * 
	 * @param projectId
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	 * 获取summary
	 * 
	 * @return
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * 设置summary
	 * 
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public List<ProjectApi> getApis() {
		return apis;
	}

	public void setApis(List<ProjectApi> apis) {
		this.apis = apis;
	}

	@Override
	public String toString() {
		return "ProjectApiGroup [groupId=" + groupId + ", projectId=" + projectId + ", name=" + name + ", summary="
				+ summary + ", description=" + description + ", externalDocs=" + externalDocs + ", extensions=" + extensions
				+ ", sorts=" + sorts + ", apis=" + apis + "]";
	}

}
