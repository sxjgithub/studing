package demo5_AIO;

public class Test {
	public static void main(String[] args) {
		int a = 10;
		String bString = "haha";
		new Test().fun(a,bString);
	}
	
	public <A,B> void fun(A a, B b){
		fun1(a, b);
	}
	public <A,B> void fun1(A a, B b){
		System.out.println(a);
		System.out.println(b);
	}
}
