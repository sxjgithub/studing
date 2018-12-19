package server;

import server.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler{
	private ServletContext sc;
	private String servletOrMapping;//
	private String flag;
	private Map<String, String> servlet;
	private Map<String, String> mapping;
	private Data data1;
	private Data data2;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("处理文档开始");
		sc = new ServletContext();
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
		flag = null;
		servletOrMapping = null;
		
		
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("开始一个元素" +qName);
		
		if(null !=qName && qName.equalsIgnoreCase("servlet")){
			servletOrMapping = "servlet";
			data1 = new Data();
			
		}else if(null !=qName && qName.equalsIgnoreCase("servlet-mapping")){
			servletOrMapping = "servlet-mapping";
			data2 = new Data();
		}
		
		if(null != servletOrMapping 
				&& ("servlet".equalsIgnoreCase(servletOrMapping) ||"servlet-mapping".equalsIgnoreCase(servletOrMapping))){
			if(null !=qName && !qName.equalsIgnoreCase("servlet") && !qName.equalsIgnoreCase("servlet-mapping")){
				flag = qName;
			}
			
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str =new String(ch,start,length);
		
		if(null != servletOrMapping && "servlet".equalsIgnoreCase(servletOrMapping)){
			if(null != flag && "servlet-name".equalsIgnoreCase(flag)){
				//key放入servlet Map 中   
				data1.setName(str);
			}else if(null != flag && "servlet-class".equalsIgnoreCase(flag)){
				//value放入servlet Map 中
				data1.setOhter(str);
			}
		}else if(null != servletOrMapping && "servlet-mapping".equalsIgnoreCase(servletOrMapping)){
			if(null != flag && "servlet-name".equalsIgnoreCase(flag)){
				//value放入servlet Map 中
				data2.setOhter(str);
			}else if(null != flag && "url-pattern".equalsIgnoreCase(flag)){
				//key放入servlet Map 中
				data2.setName(str);
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("结束一个元素" +qName);
		
		if(qName.equals("servlet")){
			this.servlet.put(data1.getName(), data1.getOhter());
			servletOrMapping = null;
		}else if(qName.equals("servlet-mapping")){
			this.mapping.put(data2.getName(), data2.getOhter());
			servletOrMapping = null;
		}
		
		flag = null;
		
		
		
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("文档处理结束");
		this.sc.setServlet(servlet);
		this.sc.setMapping(mapping);
	}
	
	public ServletContext getServletContext(){
		return sc;
	}
}
class Data{
	private String name;
	private String ohter;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOhter() {
		return ohter;
	}
	public void setOhter(String ohter) {
		this.ohter = ohter;
	}
	
}
