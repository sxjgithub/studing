package demo8;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req, Response resp) throws Exception {
		String name = req.getParamter("uname") ;
		String pwd = req.getParamter("pwd") ;
		if(login(name, pwd)){
			resp.println("��¼�ɹ�") ;
		}else{
			resp.println("��¼ʧ��") ;
		}
		
	}

	@Override
	public void doPost(Request req, Response resp) throws Exception {
		
	}
	public boolean login(String name, String pwd){
		return name.equals("sxj") && pwd.equals("java") ;
	}
	
}
