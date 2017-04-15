package com.meipian.chat.server.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;


/**
 * 单机会受到内存的限制。想一想怎么做分布式
 * @author xujianxing
 *
 */
public class SessionManager {
	private static final Map<Long, Session> sessions = new ConcurrentHashMap<>();
	private static final Map<ChannelHandlerContext, Session> sessionsWithContext = new ConcurrentHashMap<>();
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
				sessionsWithContext.remove(session.getConnection().getChannelHandlerContext());
				session = null;
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
		sessionsWithContext.put(ctx, session);
	}

	public static SessionManager getInstance() {

		return instance;

	}

	public Session getSessionByChannelHandlerContext(ChannelHandlerContext ctx) {
		return sessionsWithContext.get(ctx);
	}

}
