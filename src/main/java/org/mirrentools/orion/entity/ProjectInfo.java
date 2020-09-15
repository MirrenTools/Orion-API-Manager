package org.mirrentools.orion.entity;

/**
 * 项目的基本属性
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class ProjectInfo {
	/** 项目的id */
	private String key;
	/** 项目的负责人 */
	private String owner;
	/** 项目名称 */
	private String name;
	/** 项目版本 */
	private String version;
	/** 项目最后更新时间 */
	private Long time;
	/** 项目的排序 */
	private Integer sorts;

	public ProjectInfo() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	@Override
	public String toString() {
		return "ProjectInfo [key=" + key + ", owner=" + owner + ", name=" + name + ", version=" + version + ", time=" + time
				+ ", sorts=" + sorts + "]";
	}

}
