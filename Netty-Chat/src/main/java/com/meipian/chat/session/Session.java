package com.meipian.chat.session;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.server.core.SessionOperationListener;

import io.netty.channel.ChannelHandlerContext;

public class Session {

	private long uid; // 我的ID

	private ChannelHandlerContext ctx;

	private SessionOperationListener listener;

	public void sendResponse(ChatMessage response) {
		ctx.writeAndFlush(response);
		ctx.fireChannelRead(response);
	}

	public SessionOperationListener getListener() {
		return listener;
	}

	public void setListener(SessionOperationListener listener) {
		this.listener = listener;
	}

	public int getUid() {
		return (int) this.uid;
	}

	
	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public void close() {
		ctx.close();
		listener.doClose(this);
	}

}
