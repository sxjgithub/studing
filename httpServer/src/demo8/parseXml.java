package demo8;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;



public class parseXml {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		//1�� ��ȡ��������
		SAXParserFactory factory = SAXParserFactory.newInstance() ;
		//2���ӽ���������ȡ������
		SAXParser parse = factory.newSAXParser() ;
		//3�������ĵ� Document ע�ᴦ����
		//4�� ��д�����ĵ�
		
		parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("demo8/1.xml"), new MyPersonHandler());
		
	}
}
