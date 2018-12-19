package demo1;
/**
 * lamba表达式接口只能有一个方法；
 * @author Administrator
 *
 */
interface A{
	void add(int i, int j);
}
public class Test2 {
	public static void main(String[] args) {
		A a = (q,w)->{    //把他想象成匿名内部类，只是不写类名之类的，只写方法的实现就行了
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
