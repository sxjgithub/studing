package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server_test {
	
	public static void main(String[] args) {
		Server_test.server();
	}
	
	 public static void server(){
	        ServerSocket serverSocket = null;
	        InputStream in = null;
	        try
	        {
	            serverSocket = new ServerSocket(7777);
	            int recvMsgSize = 0;
	            byte[] recvBuf = new byte[1024];
	            while(true){
	                Socket clntSocket = serverSocket.accept();
	                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
	                System.out.println("Handling client at "+clientAddress);
	                
	                in = clntSocket.getInputStream();
	                while((recvMsgSize=in.read(recvBuf))!=-1){
	                	//网上做法----------------------------------------------------
	                   /* byte[] temp = new byte[recvMsgSize];
	                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
	                    System.out.println(new String(temp));*/
	                	
	                	//我的做法-----------------------------------------------------
	                	String recvString = new String(recvBuf, 0, recvMsgSize);
	                	System.out.println("服务器收到信息: " +recvString);
	                }
	            }
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(serverSocket!=null){
	                    serverSocket.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }catch(IOException e){
	                e.printStackTrace();
	            }
	        }
	    }
}
