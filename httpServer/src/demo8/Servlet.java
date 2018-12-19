package demo8;
//抽象为一个父类
public abstract class Servlet {
	public void service(Request req, Response resp) throws Exception{
		this.doGet(req, resp);
		this.doPost(req, resp);
	}
	public abstract void doGet(Request req, Response resp) throws Exception ;
	public abstract void doPost(Request req, Response resp) throws Exception ;
}
