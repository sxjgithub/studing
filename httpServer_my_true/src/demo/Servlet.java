package demo;

public abstract class Servlet {
	public void service(Request req, Response resp){
		this.doGet(req, resp);
		this.doPost(req, resp);
	}
	public abstract void doGet(Request req, Response resp) ;
	public abstract void doPost(Request req, Response resp) ;
}
