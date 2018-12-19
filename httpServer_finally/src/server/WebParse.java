package server;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import server.ServletContext;

public class WebParse {
	public static ServletContext getServletContext(String xmlFile) throws Exception{
		//1、获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse =factory.newSAXParser();
		//3、加载文档 Document 注册处理器
		//4、编写处理器
		WebHandler handler=new WebHandler();
		parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlFile), handler );
		
		return handler.getServletContext();
		
		/*Map<String, String> servlet = sc.getServlet();
		Map<String, String> mapping = sc.getMapping();
		
		System.out.println("\n\nservlet:\n");
		System.out.println("*********************");
		for(Entry<String, String> e:servlet.entrySet()){	
			System.out.print(e.getKey() + "|");
			System.out.println(e.getValue());
		}
		System.out.println("*********************");
		
		System.out.println("\n\nservlet-mapping:\n");
		System.out.println("*********************");
		for(Entry<String, String> e:mapping.entrySet()){
			
			System.out.print(e.getKey() + "|");
			System.out.print(e.getValue() + "\n");
		}
		System.out.println("*********************");*/
	}
		
}
