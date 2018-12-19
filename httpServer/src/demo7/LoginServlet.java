package demo7;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req, Response resp) throws Exception {
		String name = req.getParamter("uname") ;
		String pwd = req.getParamter("pwd") ;
		if(login(name, pwd)){
			resp.println("µÇÂ¼³É¹¦") ;
		}else{
			resp.println("µÇÂ¼Ê§°Ü") ;
		}
		
	}

	@Override
	public void doPost(Request req, Response resp) throws Exception {
		
	}
	public boolean login(String name, String pwd){
		return "sxj".equals(name) && "java".equals(pwd) ;
	}
	
}
