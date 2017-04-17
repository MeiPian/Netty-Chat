package com.meipian.chat.server.codec;

import org.apache.log4j.Logger;

import com.meipian.chat.protocol.ChatMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码
 * 
 * @author xujianxing
 *
 */
public class ChatMessageEncoder extends MessageToByteEncoder<ChatMessage> {
	private static Logger logger = Logger.getLogger(ChatMessageDecoder.class);

	@Override
	protected void encode(ChannelHandlerContext arg0, ChatMessage message, ByteBuf arg2) throws Exception {
		arg2.writeBytes(ChatMessage.ByteBuilder.newBuilder().build(message));
	}

}
