package demo5;

public class Serlvet {
	public void service(Request req, Response resp){
		resp.println("<html><body><h3>hel") ;
		resp.println(req.getParamter("uname"));
		resp.println("</h3></body></html>") ;
	}
}
