package demo5_AIO;

import java.net.Socket;

import demo2.OS;

public class AsynSocketChannel {
	public Socket socket;
	private OSHandlerRead osHandlerRead = new OSHandlerRead();
	
	public void read(String msg, String msg1, ReadHandler readHandler){
		osHandlerRead.register(readHandler);
		new Thread(osHandlerRead).start();
	}
	
	public void write(){
		
	}
	
}

