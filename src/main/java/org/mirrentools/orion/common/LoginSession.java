package org.mirrentools.orion.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户会话
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class LoginSession {

	/** 用户的id */
	private String uid;
	/** 用户的角色 */
	private LoginRole role;
	/** 用户的标签集 */
	private List<String> tags = new ArrayList<String>();
	/** 最后时间 */
	private long lastTime;

	/**
	 * 获取用户的id
	 * 
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * 设置用户的id
	 * 
	 * @param uid
	 * @return
	 */
	public LoginSession setUid(String uid) {
		this.uid = uid;
		return this;
	}

	/**
	 * 获取用户的角色
	 * 
	 * @return
	 */
	public LoginRole getRole() {
		return role;
	}

	/**
	 * 设置用户的角色
	 * 
	 * @param role
	 * @return
	 */
	public LoginSession setRole(LoginRole role) {
		this.role = role;
		return this;
	}

	/**
	 * 获取用户的标签
	 * 
	 * @return
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * 给用户添加标签
	 * 
	 * @param tag
	 * @return
	 */
	public LoginSession addTag(String tag) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
		}
		this.tags.add(tag);
		return this;
	}

	/**
	 * 设置用户的标签
	 * 
	 * @param tags
	 * @return
	 */
	public LoginSession setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	/**
	 * 获取最后访问时间
	 * 
	 * @return
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * 设置最后访问时间
	 * 
	 * @param lastTime
	 * @return
	 */
	public LoginSession setLastTime(long lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public String toString() {
		return "LoginSession [uid=" + uid + ", role=" + role + ", tags=" + tags + ", lastTime=" + lastTime + "]";
	}

}
