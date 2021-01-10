package org.mirrentools.orion.entity;


/**
 * 项目记录表
 * @author 
 */ 
public class ProjectShare {
	/** 分享的id */
	private String sid; 
	/** 项目的id */
	private String pid; 
	/** 查看密码 */
	private String pwd; 
	/** 分享的时间 */
	private Long shareTime; 
	/**
	 * 获取分享的id
	 * 
	 * @return
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * 设置分享的id
	 * 
	 * @param sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 获取项目的id
	 * 
	 * @return
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置项目的id
	 * 
	 * @param pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取查看密码
	 * 
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * 设置查看密码
	 * 
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取分享的时间
	 * 
	 * @return
	 */
	public Long getShareTime() {
		return shareTime;
	}
	/**
	 * 设置分享的时间
	 * 
	 * @param shareTime
	 */
	public void setShareTime(Long shareTime) {
		this.shareTime = shareTime;
	}

	@Override
	public String toString() {
		return "ProjectShare [sid=" + sid + " , pid=" + pid + " , pwd=" + pwd + " , shareTime=" + shareTime + "  ]";
	}
}
