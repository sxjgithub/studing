package demo3;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		AsynClient client = new AsynClient();
		
		
		MyCallBack myCallBack = new MyCallBack(); //�ƶ�һ������
		client.DispatherTask2OS(myCallBack);  //�ύ����
		
		client.asyDoOthers(); //�첽������������

		System.out.println(myCallBack.isDone()); //�鿴�����Ƿ����
		
		MyCallBack myCallBack2 = new MyCallBack(); //�ƶ�һ������
		client.DispatherTask2OS(myCallBack2);  //�ύ����
		client.asyDoOthers(); //�첽������������
		
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myCallBack.getFlag()); //getFlag����������Ϊprotected��Ϊʲô���ܹ�������������ʹ�ã�
		
		client.close();
	}
}
