package demo2;
import java.util.concurrent.TimeUnit;

public class Client {
	OS os = new OS();
	public static void main(String[] args) {
		Client client = new Client();
		
		
		MyCallBack myCallBack = new MyCallBack();
		client.DispatherTask2OS(myCallBack);
		
		client.asyDoOthers(); //�첽������������
		//client.idDone();
		System.out.println(myCallBack.getFlag());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myCallBack.getFlag());
	}

	public void asyDoOthers() {
		System.out.println("�������Ҳ��õ��ˣ��첽����������");
		
	}
	
	
	//����1����ͬ������ô������ô���һ��ͳһ��DispatherTask2OS()����-----�Ѳ���Ҳ��װ��
	//����2����ô���OS�Ĵ���״̬���������ݵ�״̬
	//����2����ô���
	public void DispatherTask2OS(CallBack callBack) {
		//OS������������� ���ղ�����ص�����	
		os.register(callBack);
		new Thread(os).start(); //ע������(�ص�����),����OSִ������
		
		//ע���OS�ѻص������������  ��Ȼ��ȡ������ִ�С�
		//new Thread(()->new OS().update(x, y,callBack)).start();
		
	}
	
}
