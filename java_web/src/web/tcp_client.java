package web;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class tcp_client {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in)) ;
	public static void main(String[] args) throws Exception {
		Socket client = new Socket("localhost",8005) ; // �������˵�������Ϣ
		// ���ڵĿͻ�����Ҫ������������Ĳ���֧�֣�������ȻҪ׼����Scanner��PrintWriter 
		Scanner scan = new Scanner(client.getInputStream()) ; // ���շ������˵���������
		scan.useDelimiter("\b") ;
		PrintStream out = new PrintStream(client.getOutputStream()) ; // ��������˷�������
		boolean flag = true ;
		while(flag) {
			String input = getString("������Ҫ���͵����ݣ�").trim() ;
			out.println(input); // �ӻ���
			if (scan.hasNext()) {	// ���������л�Ӧ��
				System.out.println(scan.next());
			}
			if ("byebye".equalsIgnoreCase(input)) {
				flag = false ;
			}
		}
		scan.close();
		out.close();
		client.close(); 
	}
	public static String getString(String prompt) throws Exception {
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine() ;
		return str ;
	}
}