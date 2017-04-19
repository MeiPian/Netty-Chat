package com.meipian.chat.server.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.meipian.chat.session.Session;

import io.netty.channel.ChannelHandlerContext;

/**
 * 单机会受到内存的限制。想一想怎么做分布式
 * 
 * @author xujianxing
 *
 */
public class SessionManager {
	private static final Map<Integer, Session> sessions = new ConcurrentHashMap<>();
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
				sessionsWithContext.remove(session.getCtx());
				session = null;
			}
		};

	}

	public Session getSessionByUid(int uid) {

		return sessions.get(uid);
	}

	public Session addSession(ChannelHandlerContext ctx, int uid) {
		Session session = new Session();
		session.setListener(listener);
		session.setCtx(ctx);
		session.setUid(uid);
		sessions.put(uid, session);
		sessionsWithContext.put(ctx, session);
		return session;
	}

	public static SessionManager getInstance() {

		return instance;

	}

	public Session getSessionByChannelHandlerContext(ChannelHandlerContext ctx) {
		return sessionsWithContext.get(ctx);
	}

}
