package java_io_based;
/*���̵���������InputStream input = System.in ;
 * 			int len = input.read(data) ;
 * */
import java.io.InputStream;

public class SystemIO {
	public static void main(String[] args) throws Exception {
		InputStream input = System.in ; // ��ʱ��������Ϊ��������---------
		System.out.print("��������Ϣ��");
		byte [] data = new byte [1024] ;
		int len = input.read(data) ; //-------------------------
		System.out.println("��������Ϊ��" + new String(data, 0, len));
	}
}

