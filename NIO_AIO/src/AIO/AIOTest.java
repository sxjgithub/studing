package AIO;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AIOTest { 
	      
	public static void main(String[] args) throws Exception {
		AIOTest aio = new AIOTest();
		aio.testServer();
		aio.testClient();
	}
	
	    @SuppressWarnings("unused")
		public void testServer() throws IOException, InterruptedException {  
	        SimperServer server = new SimperServer(7777);  
	          
	        Thread.sleep(70000);  
	    }  
	      
	    public void testClient() throws Exception {  
	        SimpleClient client = new SimpleClient("localhost", 7777);  
	        client.write((byte) 11);  
	    }  
	  
}
