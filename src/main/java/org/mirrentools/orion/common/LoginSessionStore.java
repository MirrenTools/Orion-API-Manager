package org.mirrentools.orion.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个简单的数据存储,本设计仅用于存储登录信息,为了简单,这并不是一个安全操作方式,默认会话保存7天,大于7天未登录则会话失效
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class LoginSessionStore {

	/** 存储数据的 */
	private static Map<String, LoginSession> DATA = new ConcurrentHashMap<>();

	/**
	 * 保存会话
	 * 
	 * @param sessionId 会话的id
	 * @param uid       用户的id
	 * @param rule      用户的角色
	 * @return 保存成功返回true,保存失败返回false,失败通常是三个参数存在控制
	 */
	public static boolean save(String sessionId, String uid, LoginRole rule) {
		return save(sessionId, uid, rule, null);
	}

	/**
	 * 保存会话
	 * 
	 * @param sessionId 会话的id
	 * @param uid       用户的id
	 * @param rule      用户的角色
	 * @param tags      用户的标签
	 * @return 保存成功返回true,保存失败返回false,失败通常是三个参数存在控制
	 */
	public static boolean save(String sessionId, String uid, LoginRole rule, List<String> tags) {
		if (sessionId == null || uid == null || rule == null) {
			return false;
		}
		LoginSession session = new LoginSession();
		session.setUid(uid);
		session.setRole(rule);
		if (tags != null) {
			session.setTags(tags);
		}
		session.setLastTime(System.currentTimeMillis());
		DATA.put(sessionId, session);
		return true;
	}

	/**
	 * 获取会话对应的用户id
	 * 
	 * @param sessionId 会话id
	 * @return 用户id
	 */
	public static LoginSession get(String sessionId) {
		if (sessionId == null) {
			return null;
		}
		LoginSession session = DATA.get(sessionId);
		if (session == null) {
			return null;
		}
		if ((System.currentTimeMillis() - session.getLastTime()) > (7 * 24 * 60 * 60 * 1000)) {
			DATA.remove(sessionId);
			return null;
		} else {
			session.setLastTime(System.currentTimeMillis());
			DATA.put(sessionId, session);
		}
		return session;
	}

	/**
	 * 删除会话
	 * 
	 * @param sessionId 会话的id
	 */
	public static void remove(String sessionId) {
		if (sessionId == null) {
			return;
		}
		DATA.remove(sessionId);
	}

}
