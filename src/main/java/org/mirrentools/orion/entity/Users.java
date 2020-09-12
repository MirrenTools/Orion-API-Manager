package org.mirrentools.orion.entity;


/**
 * 用户表
 * @author <a href="https://mirrentools.org">Mirren</a>
 */ 
public class Users {
	/** 用户的账号 */
	private String uid; 
	/** 用户的角色 */
	private String role; 
	/** 用户的标签 */
	private String tags; 
	/** 用户的密码 */
	private String pwd; 
	/** 用户的昵称 */
	private String nickname; 
	/** 用户的联系信息 */
	private String contact; 
	/** 用户的简介 */
	private String summary; 
	/** 创建的时间 */
	private Long ctime; 
	/** 最后访问时间 */
	private Long lasttime; 
	/**
	 * 获取用户的账号
	 * 
	 * @return
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置用户的账号
	 * 
	 * @param uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取用户的角色
	 * 
	 * @return
	 */
	public String getRole() {
		return role;
	}
	/**
	 * 设置用户的角色
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * 获取用户的标签
	 * 
	 * @return
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * 设置用户的标签
	 * 
	 * @param tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取用户的密码
	 * 
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * 设置用户的密码
	 * 
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取用户的昵称
	 * 
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置用户的昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取用户的联系信息
	 * 
	 * @return
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置用户的联系信息
	 * 
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取用户的简介
	 * 
	 * @return
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置用户的简介
	 * 
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
	/**
	 * 获取最后访问时间
	 * 
	 * @return
	 */
	public Long getLasttime() {
		return lasttime;
	}
	/**
	 * 设置最后访问时间
	 * 
	 * @param lasttime
	 */
	public void setLasttime(Long lasttime) {
		this.lasttime = lasttime;
	}

	@Override
	public String toString() {
		return "Users [uid=" + uid + " , role=" + role + " , tags=" + tags + " , pwd=" + pwd + " , nickname=" + nickname + " , contact=" + contact + " , summary=" + summary + " , ctime=" + ctime + " , lasttime=" + lasttime + "  ]";
	}
}
