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
    	//打开ServerSocketChannel通道
        final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));  
        
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() { //系统执行复写的CompletionHandler回调方法
            public void completed(AsynchronousSocketChannel ch, Void att) { //accept成功（服务器与客户端连接成功）后的处理方法
                // 接受下一个连接  
                listener.accept(null, this);  
  
                // 处理当前连接 ，自己的具体处理方法
                handle(ch);  //--------------------------
            }  
  
            public void failed(Throwable exc, Void att) {  //accept失败（服务器与客户端连接失败）后的处理方法
            	
            }  
        }); 
        
        while(true){
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("哈哈我是非堵塞的，没有客户端请求那我继续做其他事情了");
        }
    }  
  
    public void handle(AsynchronousSocketChannel ch) { 
    	Future<?> future = null;
    	System.out.println("过两秒在处理");
    	try {  //为什么在回调函数里面加上时间稍长的sleep会报异常？
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);  
        try {  
        	future = ch.read(byteBuffer);
        	future.get(); //get方法堵塞，使socketChannel一直read()  为什么去掉get方法会报异常？---------------------------------
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        byteBuffer.flip(); 
        
       while(true){
        	if(future.isDone()){
        		System.out.println(byteBuffer.get()); //-------------------------
        		System.out.println("处理好了");
        		break;
            }else{
            	System.out.println("没处理好了");
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
		        	System.out.println("哈哈我是非堵塞的，没有客户端请求那我继续做其他事情了");
		        }*/
			
	}
      
}