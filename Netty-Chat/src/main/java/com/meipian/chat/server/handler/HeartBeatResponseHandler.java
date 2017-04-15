package com.meipian.chat.server.handler;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessagType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务器接收心跳。如果超时没有接收到心跳。
 * @author xujianxing
 *
 */
public class HeartBeatResponseHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage chatMsg = (ChatMessage) msg;

		if (MessagType.HEARTBEAT == chatMsg.getType()) {
			
			ctx.writeAndFlush(chatMsg);
			ctx.fireChannelRead(chatMsg);  //这句话的作用是？
		}
	}


}
