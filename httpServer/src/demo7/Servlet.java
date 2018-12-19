package demo7;
//抽象为一个父类, 把request数据中的一些数据加到response中，及处理业务
public abstract class Servlet {
	public void service(Request req, Response resp) throws Exception{
		this.doGet(req, resp);
		this.doPost(req, resp);
	}
	public abstract void doGet(Request req, Response resp) throws Exception ;
	public abstract void doPost(Request req, Response resp) throws Exception ;
}
