package demo1;
/**
 * lamba���ʽ�ӿ�ֻ����һ��������
 * @author Administrator
 *
 */
interface A{
	void add(int i, int j);
}
public class Test2 {
	public static void main(String[] args) {
		A a = (q,w)->{    //��������������ڲ��ֻ࣬�ǲ�д����֮��ģ�ֻд������ʵ�־�����
			int r = q + w;
			System.out.println(r);
		};
		a.add(2, 3);
		
		
		
		for(int i = 0; i < 3; i++){
			new Thread(()->{
				for(int j = 0; j < 3; j++){
					System.out.println(Thread.currentThread().getName());
				}
			},"t" + i).start();
		}
	}
}
