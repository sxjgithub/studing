package demo4_AIO;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsynServerHandler implements Runnable {
	public CountDownLatch latch;
	public AsynServerSocketChannel channel;
	public AsynServerHandler(int port) {
		try {
			//���������ͨ��
			channel = AsynServerSocketChannel.open();
			//�󶨶˿�
			channel.bind(port);
			System.out.println("���������������˿ںţ�" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//CountDownLatch��ʼ��
		//�������ã������һ������ִ�еĲ���֮ǰ������ǰ���ֳ�һֱ����
		//�˴������ֳ��ڴ���������ֹ�����ִ����ɺ��˳�
		//Ҳ����ʹ��while(true)+sleep 
		//���ɻ����Ͳ���Ҫ����������⣬��Ϊ������ǲ����˳���
		latch = new CountDownLatch(1);
		//���ڽ��տͻ��˵�����
		channel.accept(this,new AcceptHandler());
		System.out.println("���Ƿ��������������ҿ����첽��");
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("���Ƿ��������������ҿ����첽��");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

