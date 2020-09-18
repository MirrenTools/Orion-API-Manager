package org.mirrentools.orion.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.mirrentools.orion.common.LoginRole;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.WebSocket;
import org.mirrentools.orion.service.ProjectService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 用于导入API的WebSocket
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@ServerEndpoint("/private/server/ws/project/fromJson/{sessionId}")
@Component
public class ImportWebSocketServer {
	private static final Logger LOG = LogManager.getLogger(ImportWebSocketServer.class);
	/** Spring的上下文 */
	private static ApplicationContext applicationContext;
	/** 项目服务接口 */
	private ProjectService proService;
	/**
	 * 存放所有在线的客户端
	 */
	private static Map<String, Session> clients = new ConcurrentHashMap<>();

	public static void setApplicationContext(ApplicationContext applicationContext) {
		ImportWebSocketServer.applicationContext = applicationContext;
	}

	@OnOpen
	public void onOpen(Session session, @PathParam(value = "sessionId") String sessionId) {
		LoginSession login = LoginSessionStore.get(sessionId);
		if (login == null || login.getUid() == null || LoginRole.CLIENT == login.getRole()) {
			session.getAsyncRemote().sendText(WebSocket.failed401());
			try {
				session.close();
			} catch (IOException e) {
				LOG.error("执行打开Websocket->关闭会话-->失败:", e);
			}
			return;
		}
		this.proService = applicationContext.getBean(ProjectService.class);
		session.setMaxTextMessageBufferSize(10 * 1024 * 1024);
		clients.put(sessionId, session);
	}

	/**
	 * 客户端关闭
	 * 
	 * @param session session
	 */
	@OnClose
	public void onClose(Session session, @PathParam(value = "sessionId") String sessionId) {
		clients.remove(sessionId);
	}

	/**
	 * 发生错误
	 * 
	 * @param throwable e
	 */
	@OnError
	public void onError(Throwable throwable, @PathParam(value = "sessionId") String sessionId) {
		Session session = clients.get(sessionId);
		try {
			session.close();
		} catch (IOException e) {
			LOG.error("执行Websocket发生异常->关闭会话-->失败:", e);
		}
		LOG.error("执行Websocket发生异常:", throwable);
	}

	/**
	 * 收到客户端发来消息
	 * 
	 * @param message 消息对象
	 */
	@OnMessage
	public void onMessage(String message, @PathParam(value = "sessionId") String sessionId) {
		LoginSession login = LoginSessionStore.get(sessionId);
		Session session = clients.get(sessionId);
		if (session == null) {
			return;
		}
		JSONObject msg = new JSONObject(message);
		if (msg.getInt("type") != WebSocket.TYPE) {
			session.getAsyncRemote().sendText(WebSocket.failed(WebSocket.UNRECOGNIZED));
			try {
				session.close();
			} catch (IOException e) {
			}
			return;
		}
		proService.saveProjectfromJsonWebSocket(login, msg.getString("data"), session);
		try {
			session.close();
		} catch (IOException e) {
			LOG.error("执行Websocket消息事件->关闭会话-->失败:", e);
		}
	}
}
