package org.mirrentools.orion.entity;

/**
 * 项目的基本属性
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class ProjectInfo {
	private String key;
	private String name;
	private String version;
	private Long time;
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
		return "ProjectInfo [key=" + key + ", name=" + name + ", version=" + version + ", time=" + time + ", sorts=" + sorts
				+ "]";
	}

}
