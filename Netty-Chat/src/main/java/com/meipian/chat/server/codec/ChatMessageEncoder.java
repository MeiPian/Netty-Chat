package com.meipian.chat.server.codec;

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

	@Override
	protected void encode(ChannelHandlerContext arg0, ChatMessage message, ByteBuf arg2) throws Exception {
		byte[] bytes = new byte[1];
		bytes[0] = message.getType();
		arg2.writeBytes(bytes);
		arg2.writeLong(message.getUid());
		arg2.writeLong(message.getOid());
		arg2.writeDouble(message.getLongitude());
		arg2.writeDouble(message.getLatitude());
		arg2.writeInt(message.getAction());

	}

}
