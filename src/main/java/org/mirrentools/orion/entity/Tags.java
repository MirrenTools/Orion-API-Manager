package org.mirrentools.orion.entity;


/**
 * 标签表
 * @author <a href="https://mirrentools.org">Mirren</a>
 */ 
public class Tags {
	/** 标签的id */
	private String tid; 
	/** 标签的名称 */
	private String tname; 
	/** 项目排序 */
	private Integer sorts; 
	/** 创建的时间 */
	private Long ctime; 
	/**
	 * 获取标签的id
	 * 
	 * @return
	 */
	public String getTid() {
		return tid;
	}
	/**
	 * 设置标签的id
	 * 
	 * @param tid
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	/**
	 * 获取标签的名称
	 * 
	 * @return
	 */
	public String getTname() {
		return tname;
	}
	/**
	 * 设置标签的名称
	 * 
	 * @param tname
	 */
	public void setTname(String tname) {
		this.tname = tname;
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
	 * 获取创建的时间
	 * 
	 * @return
	 */
	public Long getCtime() {
		return ctime;
	}
	/**
	 * 设置创建的时间
	 * 
	 * @param ctime
	 */
	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Tags [tid=" + tid + " , tname=" + tname + " , sorts=" + sorts + " , ctime=" + ctime + "  ]";
	}
}
