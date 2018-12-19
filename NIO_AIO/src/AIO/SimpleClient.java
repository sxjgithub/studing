package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleClient {
	private AsynchronousSocketChannel client;  
    
    public SimpleClient(String host, int port) throws Exception {  
        this.client = AsynchronousSocketChannel.open();   //socketChannelͨ����
        Future<?> future = client.connect(new InetSocketAddress(host, port));  
       System.out.println("�ͻ������ӳɹ�����" + future.isDone());
       future.get();  
       System.out.println("�ͻ������ӳɹ�����" + future.isDone());
    }  
      
    public void write(byte b) {  
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);  
        byteBuffer.put(b);  
        byteBuffer.flip();  
        client.write(byteBuffer);  
    } 
    
    public static void main(String[] args) throws Exception {
		SimpleClient client = new SimpleClient("localhost", 7777);
		client.write((byte) 11);
	}
}
