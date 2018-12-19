package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SimperServer {  
	  
    public SimperServer(int port) throws IOException {  
    	//��ServerSocketChannelͨ��
        final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));  
        
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() { //ϵͳִ�и�д��CompletionHandler�ص�����
            public void completed(AsynchronousSocketChannel ch, Void att) { //accept�ɹ�����������ͻ������ӳɹ�����Ĵ�����
                // ������һ������  
                listener.accept(null, this);  
  
                // ����ǰ���� ���Լ��ľ��崦����
                handle(ch);  //--------------------------
            }  
  
            public void failed(Throwable exc, Void att) {  //acceptʧ�ܣ���������ͻ�������ʧ�ܣ���Ĵ�����
            	
            }  
        }); 
        
        while(true){
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("�������ǷǶ����ģ�û�пͻ����������Ҽ���������������");
        }
    }  
  
    public void handle(AsynchronousSocketChannel ch) { 
    	Future<?> future = null;
    	System.out.println("�������ڴ���");
    	try {  //Ϊʲô�ڻص������������ʱ���Գ���sleep�ᱨ�쳣��
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);  
        try {  
        	future = ch.read(byteBuffer);
        	future.get(); //get����������ʹsocketChannelһֱread()  Ϊʲôȥ��get�����ᱨ�쳣��---------------------------------
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        byteBuffer.flip(); 
        
       while(true){
        	if(future.isDone()){
        		System.out.println(byteBuffer.get()); //-------------------------
        		System.out.println("�������");
        		break;
            }else{
            	System.out.println("û�������");
            }
        }
        
        // Do something  
    }  
    
    public static void main(String[] args) {
		
			try {
				SimperServer server = new SimperServer(7777);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*while(true){
		        	try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	System.out.println("�������ǷǶ����ģ�û�пͻ����������Ҽ���������������");
		        }*/
			
	}
      
}