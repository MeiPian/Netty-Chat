package com.meipian.chat.server.core;

import com.meipian.chat.protocol.ChatMessage;

import io.netty.channel.ChannelHandlerContext;

public class ChatConnection {

	private long uid; // 我的ID

	private ChannelHandlerContext ctx;

	public ChatConnection(Long uid, ChannelHandlerContext ctx) {
		this.uid = uid;
		this.ctx = ctx;
	}

	public long getId() {
		return uid;
	}

	public void sendResponse(ChatMessage resp) {
		ctx.writeAndFlush(resp);
	}

	public void close() {
		ctx.close();
	}

	public ChannelHandlerContext getChannelHandlerContext() {
		return ctx;
	}

}
