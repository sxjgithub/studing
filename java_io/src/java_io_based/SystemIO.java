package java_io_based;
/*键盘的输入流：InputStream input = System.in ;
 * 			int len = input.read(data) ;
 * */
import java.io.InputStream;

public class SystemIO {
	public static void main(String[] args) throws Exception {
		InputStream input = System.in ; // 此时的输入流为键盘输入---------
		System.out.print("请输入信息：");
		byte [] data = new byte [1024] ;
		int len = input.read(data) ; //-------------------------
		System.out.println("输入内容为：" + new String(data, 0, len));
	}
}

