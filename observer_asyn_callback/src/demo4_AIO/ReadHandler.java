package demo4_AIO;

import java.io.IOException;
import java.io.InputStream;

public class ReadHandler implements Handler<String, String>{
	AsynSocketChannel asynSocketChannel;
	
	
	public AsynSocketChannel getAsynSocketChannel() {
		return asynSocketChannel;
	}

	public void setAsynSocketChannel(AsynSocketChannel asynSocketChannel) {
		this.asynSocketChannel = asynSocketChannel;
	}

	public ReadHandler(AsynSocketChannel asynSocketChannel) {
		this.asynSocketChannel = asynSocketChannel;
	}
	
	@Override
	public void completed(String t, String v) {
			System.out.println("收到数据为:" + t);
	}

	@Override
	public void fail() {
		// TODO Auto-generated method stub
		
	}
	
}
