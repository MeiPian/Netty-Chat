package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.meipian.chat.protocol.MessagType;
import com.meipian.chat.server.utils.ByteUtils;

public class Client {
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		final int port = 10000;
		Socket socket = new Socket("127.0.0.1", port);
		final OutputStream out = socket.getOutputStream();

		short a = 0;
		for (int i = 0; i < 1; i++) {
			// try {
			//// Thread.sleep(500);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			// ChatMessage message=new ChatMessage();
			// message.setType(MessagType.HEARTBEAT);
			//
			
			byte[] b=new byte[5];
			ByteUtils.int2Byte(1, b, 0);
			b[4]=MessagType.HEARTBEAT;
			out.write(b);
			out.flush();
			a++;

		}
	}

}
