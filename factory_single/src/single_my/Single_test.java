package single_my;

import sun.security.jca.GetInstance;

public class Single_test {
	public static void main(String[] args) {
		//new Single() ;
		Single1 s1 =Single1.getInstance() ;
		s1.print();
		
		Single2 s2 = Single2.getInstance() ;
		s2.print();
		
	}
}

//单例模式1
/*分析:（1）要想实现单例，则不能任意的new了，就把构造函数私有化，
 * 		（2）但这样就不能实例化了，于是在类里主动调用构造函数示例化：private Single INSTANCE = new Single();
 * 			class Single{
					private Single INSTANCE = new Single();
					private Single() {}
			}

 * 		（3）但这样怎么在主类里使用这个类，方法可以是 Single.getInstance() 这种方法，getInstance 就必须是static方法，
 * 				INSTANCE也只能有一份，也要是static， 同时INSTANCE 不能初始化后不能改变，所有加上final。
 * */
class Single1{
	private static final Single1 INSTANCE = new Single1();
	private Single1() {}
	public static Single1 getInstance(){
		return INSTANCE ;
	}
	public void print(){
		System.out.println(INSTANCE) ;
	}
}

//单例模式2
class Single2{
	private static Single2 single ;
	private Single2(){}
//判断，通过getInstance方法初始化 Single2类	
	public static Single2 getInstance(){
		if(null == single){
			single = new Single2() ;
		}
		return single ;
	}
	public void print(){
		System.out.println(single) ;
	}
}