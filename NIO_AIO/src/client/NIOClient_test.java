package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class NIOClient_test {
	
	public static void main(String[] args) {
		NIOClient_test.client();
	}
	 public static void client(){
	        ByteBuffer buffer = ByteBuffer.allocate(1024);
	        SocketChannel socketChannel = null;
	        try
	        {
	            socketChannel = SocketChannel.open(); //Socket通道打开
	            socketChannel.configureBlocking(false);
	            socketChannel.connect(new InetSocketAddress("localhost",7777));
	            if(socketChannel.finishConnect())
	            {
	                int i=0;
	                while(true)
	                {
	                    TimeUnit.SECONDS.sleep(1);
	                    String info = "I'm "+i+++"-th information from client" + NIOClient_test.class;
	                    buffer.clear();
	                    buffer.put(info.getBytes());
	                    buffer.flip();
	                    while(buffer.hasRemaining()){                  
	                        socketChannel.write(buffer); //把buffer中数据写入socketChannel中
	                    }
	                }
	            }
	        }
	        catch (IOException | InterruptedException e)
	        {
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(socketChannel!=null){
	                    socketChannel.close(); //关闭socketChannel
	                }
	            }catch(IOException e){
	                e.printStackTrace();
	            }
	        }
	    }
}
