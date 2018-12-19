package demo6;

import java.io.IOException;
import java.net.Socket;

import sun.awt.RepaintArea;

public class Dispatcher implements Runnable{
	private Response resp;
	private Request req ;
	private Socket client ;
	private int code = 200 ;
	
	public Dispatcher(Socket client) {
		this.client = client ;
	
		try {
			this.resp = new Response(client.getOutputStream()) ;
			this.req = new Request(client.getInputStream()) ;
		} catch (IOException e) {
			code = 500 ;
			return ;
		}
	}
	@Override
	public void run() {
		Serlvet ser = new Serlvet() ;
		ser.service(req, resp);
		
		try {
			resp.pushToClient(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			resp.pushToClient(500);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
