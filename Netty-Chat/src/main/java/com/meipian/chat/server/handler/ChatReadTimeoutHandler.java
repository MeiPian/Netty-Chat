package com.meipian.chat.server.handler;

import java.util.concurrent.TimeUnit;

import com.meipian.chat.server.core.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 超时要清空session
 * 
 * @author xujianxing
 *
 */
public class ChatReadTimeoutHandler extends ReadTimeoutHandler {

	public ChatReadTimeoutHandler(long timeout, TimeUnit unit) {
		super(timeout, unit);
	}

	@Override
	protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
		super.readTimedOut(ctx);
		SessionManager.getInstance().getSessionByChannelHandlerContext(ctx).close();

	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		super.disconnect(ctx, promise);
	}

}
