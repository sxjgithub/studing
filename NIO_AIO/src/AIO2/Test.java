package AIO2;

import java.util.Scanner;
import AIO2.*;
/**
 * ���Է���
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class Test {
	//����������
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		//���з�����
		Server.start();
		//����ͻ������ڷ���������ǰִ�д���
		Thread.sleep(100);
		//���пͻ��� 
		Client.start();
		System.out.println("������������Ϣ��");
		Scanner scanner = new Scanner(System.in);
		while(Client.sendMsg(scanner.nextLine()));
	}
}

