package demo3;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * �첽�ͻ��� �������첽ͬʱ����������
 * @author sxj
 *
 */
public class AsynClient {
			static OS os = new OS();
			static ExecutorService pool = Executors.newCachedThreadPool();

	public void asyDoOthers() {
		System.out.println("�������Ҳ��õ��ˣ��첽����������");
		
	}
	
	
	//(����ʵ��,��ñ�д�������������)����1����ͬ������ô������ô���һ��ͳһ��DispatherTask2OS()����-----�Ѳ���Ҳ��װ��
	//(Callback ��������flag���)����2����ô���OS�Ĵ���״̬���������ݵ�״̬
	//()����2��OS��ôͬʱ����������
	public void DispatherTask2OS(CallBack callBack) {
		//OS������������� ���ղ�����ص�����	
		os.register(callBack);  //ע������(�ص�����)
		//new Thread(os).start(); //��OSִ����������������
		pool.execute(os);
	}
	
	public void close(){
		pool.shutdown();
	}
	
}
