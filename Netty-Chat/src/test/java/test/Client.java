package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageTypeConstant;

public class Client {
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		final int port = 10000;
		Socket socket = new Socket("127.0.0.1", port);
		final OutputStream out = socket.getOutputStream();
		final InputStream input = socket.getInputStream();
//
//		short a = 0;
		// for (int i = 0; i < 1; i++) {
		// try {
		//// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// ChatMessage message=new ChatMessage();
		// message.setType(MessagType.HEARTBEAT);
		//

		ChatMessage message = new ChatMessage();
		message.setType(MessageTypeConstant.NORMAL_MESSAGE);;
		message.setUid(1);
		System.out.println(message);
		byte[] b = ChatMessage.ByteBuilder.newBuilder().build(message);
        System.out.println(b.length);
		out.write(b);
		out.flush();
		input.read(b);

	}
	// }

}
