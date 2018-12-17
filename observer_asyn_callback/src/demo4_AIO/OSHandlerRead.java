package demo4_AIO;

import java.io.IOException;
import java.io.InputStream;

public class OSHandlerRead implements Runnable{
	private ReadHandler readHandler;
	private AsynSocketChannel asynSocketChannel;
	
	public void  setChaneel(AsynSocketChannel asynSocketChannel){
		this.asynSocketChannel = asynSocketChannel;
	}
	
	public void register(ReadHandler readHandler){
		this.readHandler = readHandler;
	}
	
	
	@Override
	public void run() {
		InputStream in = null;
		byte[] buffer = new byte[1024];
		try {
			in = readHandler.getAsynSocketChannel().socket.getInputStream();
			int len = 0;
			while(-1 != (len=(in.read(buffer)))){
				String msg = new String(buffer, 0, len);
				readHandler.completed(msg, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
