package static_my;


/*static可以修饰类属性 或 类方法：表示内存只有一份，
 * 可以不被实例化就直接用：Static_test.getVar()
 * 所以：不能使用this（this表示对象，必须实例化了才有）使用static变量
 * 
 *static方法里不能使用非static变量（因为static可以不被实例化就直接用，非static只有实例化才有）
 * */
public class Test {
	public static void main(String[] args) {
		System.out.println(Static_test.getVar()) ; //
	}
	
}

class Static_test{
	private static String var = "好学生" ; //表示所有的类共用一个var变量，只有一份
	//private Static_test var1 = new Static_test() ;
	
	public static String getVar(){
		//Static_test v = this.var1 ;
		return var ;
		//return this.var ;
		
	}
}