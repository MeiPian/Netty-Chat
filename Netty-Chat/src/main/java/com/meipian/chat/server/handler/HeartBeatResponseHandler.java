package com.meipian.chat.server.handler;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessagType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务器接收心跳。如果超时没有接收到心跳。关闭会话
 * http://blog.csdn.net/u013252773/article/details/21195593中说道：
 * 
 * @author xujianxing 在使用Handler的过程中，需要注意： 1、ChannelInboundHandler之间的传递，通过调用
 *         ctx.fireChannelRead(msg) 实现；调用ctx.write(msg)
 *         将传递到ChannelOutboundHandler。 2、ctx.write()方法执行后，需要调用flush()方法才能令它立即执行。
 *         3、ChannelOutboundHandler
 *         在注册的时候需要放在最后一个ChannelInboundHandler之前，否则将无法传递到ChannelOutboundHandler。
 *         不过这个暂时没有用到
 */
public class HeartBeatResponseHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage chatMsg = (ChatMessage) msg;

		if (MessagType.HEARTBEAT == chatMsg.getType()) {

			ctx.writeAndFlush(chatMsg);
			ctx.fireChannelRead(chatMsg); 
		}
	}

}
