package demo8;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;



public class parseXml {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		//1、 获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance() ;
		//2、从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser() ;
		//3、加载文档 Document 注册处理器
		//4、 编写处理文档
		
		parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("demo8/1.xml"), new MyPersonHandler());
		
	}
}
