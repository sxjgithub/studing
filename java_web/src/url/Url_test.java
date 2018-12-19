package url;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//URL.openStream java 爬虫基础
public class Url_test {
	public static void main(String[] args) throws IOException {
		//构建绝对路径
		URL url = new URL("http://www.baidu.com:80/index.html") ;
		
		//构建相对路径
		URL url1 = new URL("http://www.sxj.com/a/") ;
		URL url2 = new URL(url1, "b.txt") ;
		System.out.println(url2.toString()) ;
		
		System.out.println("获取路径：" + url2.getPath()) ;
		System.out.println("获取协议: " + url2.getProtocol()) ;
		
		//URL.openStream 打开url资源流，使可以读取内容
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8")) ;
		String data ;
		while( null != (data = br.readLine())){
			System.out.println(data) ;
			System.out.println("\n") ;
		}
		br.close();
	}
}
