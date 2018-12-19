package demo8;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
	private String requestInfo ;
	private InputStream is ;
	
	private String method ; //����ʽ
	private String url ; //������Դ
	private Map<String, List<String>> paramterMapValues ; //�������
	
	public static final String CRLF = "\r\n" ;
	public static final String BLANK = " " ;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Request(){
		this.method = "" ;
		this.url = "" ;
		this.paramterMapValues = new HashMap<>() ;
		this.requestInfo = "" ;
	}
	
	public Request(InputStream is){
		this() ;
		this.is = is ;
		
		byte[] data = new byte[20480] ;
		int len;
		try {
			len = is.read(data);
			//�������Ĳ�������������ᱨ������.......................................
			//java.lang.StringIndexOutOfBoundsException: String index out of range: -1
			this.requestInfo = new String(data, 0, len) ;
		} catch (IOException e) {
			return ;
		}
		parseRequestInfo() ;
	}

//���Ľ���
	private String decode(String value, String code) {
		try {
			return java.net.URLDecoder.decode(value, code) ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
//����������Ϣ
	private void parseRequestInfo(){
		if(null == requestInfo || (requestInfo = requestInfo.trim()).equals("")){
			return ;
		}
		
	//����Ϣ�����зֽ��������ʽ ����·�� ���������get���ܴ��ڣ�
		//�磺GET /index.html?name=sxj&pwd=java HTTP/1.1
		//���Ϊpost��ʽ������������������������
		String paramString = "" ; //�����������
		String firstLine = requestInfo.substring(0,requestInfo.indexOf(CRLF)) ;
		int idx = requestInfo.indexOf("/") ; //  ��һ��/ ��λ��
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim() ;
		
		this.method = firstLine.substring(0, idx).trim() ; //ȡ������ʽ
		if(this.method.equalsIgnoreCase("post")){
			this.url = urlStr ;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim() ;
		}else if(this.method.equalsIgnoreCase("get")){
			if(urlStr.contains("?")){
				String[] urlArray = urlStr.split("\\?") ; //��Ϊ����������ʽ�������壬������Ҫת��
				this.url = urlArray[1] ;
				paramString = urlArray[1] ;
			}else {
				this.url = urlStr ;
			}
		}
		if(paramString.equals("")){ //�������������
			return ;
		}
		this.parseParam(paramString);
		
		System.out.println("requestInfo: " + requestInfo) ;//-------------------------------------
		System.out.println("method: " + method ) ;//-------------------------------------------------
		System.out.println("url: " + url) ;//--------------------------------------------------------------
	}
	
//��װrequest����
	private void parseParam(String param){//paramΪ a=b&c=d ��ʽ
			
			String[] keyValues = param.split("&") ;//keyValues = {a=b,c=d}
			for(int i = 0; i < keyValues.length; i++){
				String keyValue[] = keyValues[i].split("=") ;
				
				if(keyValue.length == 1){ //keyValue = {a} ���������ֻ��a=
					keyValue = Arrays.copyOf(keyValue, 2) ;
					keyValue[1] = null ;  //��keyValue = {a, null}
				}
				String key = keyValue[0].trim() ;
				//���value ����null����Ҫȥ�ո�
				String value = null == keyValue[1]?null:this.decode(keyValue[1].trim(), "gbk") ;
				
				if(!paramterMapValues.containsKey(key)){//?????????????????????????????????
					paramterMapValues.put(key, new ArrayList<String>()) ;
				}
				List<String> values = paramterMapValues.get(key) ;
				values.add(value) ;
			}	
	}
	
	/*����ҳ���name��ȡ��Ӧ�Ķ��ֵ
	 * */
	public String[] getParameterValues(String name){
		List<String> values = null ;
		if((values = paramterMapValues.get(name)) == null) {
			return null ;
		}else{
			return values.toArray(new String[0]) ;
		}
	}
// ����ҳ���name��ȡ��Ӧ�Ķ�Ӧ�ĵ���ֵ
	public String getParamter(String name){
		String[] values = getParameterValues(name) ;

		
		if(null == values){
			System.out.println("��");//----------------------------------------------------------
			return null ;
		}
		for(int i = 0; i < values.length; i ++){
			System.out.println(values[i]) ;//----------------------------------------------------
		}
		return values[0] ;
	}
	
	/*public static void main(String[] args) {
		String aString = "a=b&c=d&e=f" ;
		new Request().requestParam(aString);
	}*/
	
}
