package demo1;
import java.util.concurrent.TimeUnit;

public class Client {
	OS os = new OS();
	public static void main(String[] args) {
		Client client = new Client();
		
		
		//os.register(client); // clientע�ᵽOS��
		
		client.DispatherTask2OS(3, 5, new CallBack() { //�ύ����
			
			@Override
			public void doExcute(int x, int y) {
				
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���Ϊ:" + (x + y));
				
			}
		});
		
		client.asyDoOthers(); //�첽������������
		client.idDone();
	}

	public void asyDoOthers() {
		System.out.println("�������Ҳ��õ��ˣ��첽����������");
		
	}
	
	
	//����1����ͬ������ô������ô���һ��ͳһ��DispatherTask2OS()����-----�Ѳ���Ҳ��װ��
	//����2����ô���OS�Ĵ���״̬���������ݵ�״̬
	//����2����ô���
	public void DispatherTask2OS(int x ,int y,CallBack callBack) {
		//OS������������� ���ղ�����ص�����	
		new Thread(()->os.register(x, y, this, callBack)).start(); //ע������(�ص�����),����OSִ������
		
		//ע���OS�ѻص������������  ��Ȼ��ȡ������ִ�С�
		//new Thread(()->new OS().update(x, y,callBack)).start();
		
	}
	
}
