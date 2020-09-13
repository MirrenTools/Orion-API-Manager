package org.mirrentools.orion.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一个简单的回话存储,默认验证码有效期为15分钟
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class VerifyCodeSessionStore {
	/** 删除过期回话的线程池 */
	private static ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
	/** 当前是否在删除过期回话标识 */
	private static AtomicBoolean IS_EXECUTE = new AtomicBoolean(false);
	/** 存储数据的 */
	private static Map<String, VerifyCodeSession> DATA = new ConcurrentHashMap<>();

	/**
	 * 保存回话
	 * 
	 * @param index 下标,也是回话的id
	 * @param value 正确的值
	 * @param data  需要点击的验证码
	 * @param code  验证码
	 * @return
	 */
	public static boolean save(String index, String value, String data, String code) {
		if (!IS_EXECUTE.get()) {
			try {
				EXECUTOR.execute(() -> {
					IS_EXECUTE.set(true);
					try {
						DATA.values().forEach(session -> {
							if ((System.currentTimeMillis() - session.getLastTime()) > (15 * 60 * 1000)) {
								DATA.remove(session.getIndex());
							}
						});
					} catch (Exception e) {
					}
					IS_EXECUTE.set(false);
				});
			} catch (Exception e) {
			}
		}
		VerifyCodeSession session = new VerifyCodeSession();
		session.setIndex(index).setValue(value).setData(data).setCode(code).setLastTime(System.currentTimeMillis());
		DATA.put(index, session);
		return true;
	}

	/**
	 * 获取会话对应的用户id
	 * 
	 * @param sessionId 会话id
	 * @return 用户id
	 */
	public static VerifyCodeSession get(String sessionId) {
		if (sessionId == null) {
			return null;
		}
		VerifyCodeSession session = DATA.get(sessionId);
		if (session == null) {
			return null;
		}
		if ((System.currentTimeMillis() - session.getLastTime()) > (15 * 60 * 1000)) {
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
