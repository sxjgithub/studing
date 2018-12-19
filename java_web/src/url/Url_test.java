package url;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//URL.openStream java �������
public class Url_test {
	public static void main(String[] args) throws IOException {
		//��������·��
		URL url = new URL("http://www.baidu.com:80/index.html") ;
		
		//�������·��
		URL url1 = new URL("http://www.sxj.com/a/") ;
		URL url2 = new URL(url1, "b.txt") ;
		System.out.println(url2.toString()) ;
		
		System.out.println("��ȡ·����" + url2.getPath()) ;
		System.out.println("��ȡЭ��: " + url2.getProtocol()) ;
		
		//URL.openStream ��url��Դ����ʹ���Զ�ȡ����
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8")) ;
		String data ;
		while( null != (data = br.readLine())){
			System.out.println(data) ;
			System.out.println("\n") ;
		}
		br.close();
	}
}
