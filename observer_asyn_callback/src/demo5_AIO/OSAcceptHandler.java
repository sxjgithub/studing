package demo5_AIO;

import java.io.IOException;
import java.net.Socket;

public class OSAcceptHandler extends OSHandler{

	@Override
	AsynSocketChannel acceptHandler(AsynServerSocketChannel channel) {
		Socket socket = null;
		try {
			socket = channel.serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AsynSocketChannel socketChannel = new AsynSocketChannel();
		socketChannel.socket = socket;
		return socketChannel;
	}

}
