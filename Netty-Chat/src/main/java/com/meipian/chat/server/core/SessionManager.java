package com.meipian.chat.server.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

public class SessionManager {
	private static final Map<Long, Session> sessions = new ConcurrentHashMap<>();
	private static final SessionManager instance = new SessionManager();

	private static SessionOperationListener listener = null;

	public SessionManager() {
		init();
	}

	private void init() {
		listener = new SessionOperationListener() {
			@Override
			public void doClose(Session session) {
				sessions.remove(session.getUid());
			}
		};

	}

	public Session getSessionByUid(long uid) {

		return sessions.get(uid);
	}

	public void addSession(ChannelHandlerContext ctx, long uid) {
		ChatConnection connection = new ChatConnection(uid, ctx);
		Session session = new Session();
		session.setConnection(connection);
		session.setListener(listener);
		sessions.put(uid, session);
	}

	public static SessionManager getInstance() {

		return instance;

	}

}
