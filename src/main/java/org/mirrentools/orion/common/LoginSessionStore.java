package org.mirrentools.orion.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个简单的数据存储,本设计仅用于存储登录信息,这并不是一个安全操作方式,纯属为了简单,默认会话保存7天,大于7天未登录则会话失效
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class LoginSessionStore {
	private static class Session {
		/** 用户的id */
		String uid;
		/** 最后时间 */
		long lastTime;
	}

	/** 存储数据的 */
	private static Map<String, Session> DATA = new ConcurrentHashMap<>();

	/**
	 * 保存会话
	 * 
	 * @param sessionId 会话的id
	 * @param uid       用户的id
	 */
	public static void save(String sessionId, String uid) {
		if (sessionId == null) {
			return;
		}
		Session session = new Session();
		session.uid = uid;
		session.lastTime = System.currentTimeMillis();
		DATA.put(sessionId, session);
	}

	/**
	 * 获取会话对应的用户id
	 * 
	 * @param sessionId 会话id
	 * @return 用户id
	 */
	public static String get(String sessionId) {
		if (sessionId == null) {
			return null;
		}
		Session session = DATA.get(sessionId);
		if (session == null) {
			return null;
		}
		if ((System.currentTimeMillis() - session.lastTime) > (7 * 24 * 60 * 60 * 1000)) {
			DATA.remove(sessionId);
			return null;
		} else {
			session.lastTime = System.currentTimeMillis();
			DATA.put(sessionId, session);
		}
		return session.uid;
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
