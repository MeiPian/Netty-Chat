package com.meipian.chat.server.codec;

import java.util.HashMap;
import java.util.Map;

import com.meipian.chat.protocol.ChatMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ChatMessageDecoder extends LengthFieldBasedFrameDecoder {

	public ChatMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	@Override
	protected Object decode(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		ChatMessage message = new ChatMessage();
		byte type = arg1.readByte();
		message.setType(type);
		long uid = arg1.readLong();
		message.setUid(uid);
		long oid = arg1.readLong();
		message.setOid(oid);
		double longitude = arg1.readDouble();
		message.setLatitude(longitude);
		double latitude = arg1.readDouble();
		message.setLatitude(latitude);
		int action = arg1.readInt();
		message.setAction(action);
		int bodyByteSize = arg1.readInt();
		if (bodyByteSize > 0 && bodyByteSize < 1024) {
			Map<String, String> body = new HashMap<>(bodyByteSize);
		}
		return message;
	}
}
