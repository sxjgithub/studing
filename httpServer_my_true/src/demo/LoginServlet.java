package demo;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req, Response resp) {
		
		String name = req.getParamter("uname") ;
		String pwd = req.getParamter("pwd") ;
		
		resp.println("���") ;
		if(login(name, pwd)){
			resp.println("��¼�ɹ�! ") ;
			resp.println("��ӭ��: " + name) ;
		}else{
			resp.println("��¼ʧ��! ") ;
		}
		
	}

	@Override
	public void doPost(Request req, Response resp) {
		// TODO Auto-generated method stub
		
	}
	public boolean login(String name, String pwd){
		return ("sxj".equals(name) && "java".equals(pwd)) ;
	}
}
