package demo7;
/*���ܣ�����û����ܣ����ӳ��url-->����ģ�����ƣ����ӳ�书��ģ������--->��Ӧ��Servlet
 * ��ӳ�伯ͨ�� ����ģ��������ϵ�� 
 * */
import java.util.Map;

public class WebApp {
	private  static ServletContext contxt;
	
	static{
		contxt = new ServletContext() ;
		
		Map<String, String> mapping = contxt.getMapping() ;
		mapping.put("/login", "login") ;
		mapping.put("/log", "login");
		//mapping.put("/reg", "register");
		
		Map<String, Servlet> servlet = contxt.getServlet() ;
		servlet.put("login", new LoginServlet()) ;
	}
	
//��ȡurl��Ӧ�� Servlet
	public static Servlet getServlet(String url){
		if((null == url) || (url = url.trim()).equals("")){
			return null ;
		}
		return contxt.getServlet().get(contxt.getMapping().get(url)) ;
	}
}
