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
	
	private String method ; //请求方式
	private String url ; //请求资源
	private Map<String, List<String>> paramterMapValues ; //请求参数
	
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
			//如果输入的参数过长，这里会报错。。。.......................................
			//java.lang.StringIndexOutOfBoundsException: String index out of range: -1
			this.requestInfo = new String(data, 0, len) ;
		} catch (IOException e) {
			return ;
		}
		parseRequestInfo() ;
	}

//中文解码
	private String decode(String value, String code) {
		try {
			return java.net.URLDecoder.decode(value, code) ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
//分析请求信息
	private void parseRequestInfo(){
		if(null == requestInfo || (requestInfo = requestInfo.trim()).equals("")){
			return ;
		}
		
	//从信息的首行分解出：请求方式 请求路径 请求参数（get可能存在）
		//如：GET /index.html?name=sxj&pwd=java HTTP/1.1
		//如果为post方式，请求参数可能在最后正文中
		String paramString = "" ; //接收请求参数
		String firstLine = requestInfo.substring(0,requestInfo.indexOf(CRLF)) ;
		int idx = requestInfo.indexOf("/") ; //  第一个/ 的位置
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim() ;
		
		this.method = firstLine.substring(0, idx).trim() ; //取出请求方式
		if(this.method.equalsIgnoreCase("post")){
			this.url = urlStr ;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim() ;
		}else if(this.method.equalsIgnoreCase("get")){
			if(urlStr.contains("?")){
				String[] urlArray = urlStr.split("\\?") ; //因为？在正则表达式中有意义，所有需要转义
				this.url = urlArray[1] ;
				paramString = urlArray[1] ;
			}else {
				this.url = urlStr ;
			}
		}
		if(paramString.equals("")){ //不存在请求参数
			return ;
		}
		this.parseParam(paramString);
		
		System.out.println("requestInfo: " + requestInfo) ;//-------------------------------------
		System.out.println("method: " + method ) ;//-------------------------------------------------
		System.out.println("url: " + url) ;//--------------------------------------------------------------
	}
	
//封装request参数
	private void parseParam(String param){//param为 a=b&c=d 形式
			
			String[] keyValues = param.split("&") ;//keyValues = {a=b,c=d}
			for(int i = 0; i < keyValues.length; i++){
				String keyValue[] = keyValues[i].split("=") ;
				
				if(keyValue.length == 1){ //keyValue = {a} 的情况，即只有a=
					keyValue = Arrays.copyOf(keyValue, 2) ;
					keyValue[1] = null ;  //令keyValue = {a, null}
				}
				String key = keyValue[0].trim() ;
				//如果value 不是null，则要去空格
				String value = null == keyValue[1]?null:this.decode(keyValue[1].trim(), "gbk") ;
				
				if(!paramterMapValues.containsKey(key)){//?????????????????????????????????
					paramterMapValues.put(key, new ArrayList<String>()) ;
				}
				List<String> values = paramterMapValues.get(key) ;
				values.add(value) ;
			}	
	}
	
	/*根据页面的name获取对应的多个值
	 * */
	public String[] getParameterValues(String name){
		List<String> values = null ;
		if((values = paramterMapValues.get(name)) == null) {
			return null ;
		}else{
			return values.toArray(new String[0]) ;
		}
	}
// 根据页面的name获取对应的对应的单个值
	public String getParamter(String name){
		String[] values = getParameterValues(name) ;

		
		if(null == values){
			System.out.println("无");//----------------------------------------------------------
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
