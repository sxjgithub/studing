package command1;
/*Female 可以命令 (order)她的男朋友Man 做很多事
 * */
public class Command_test {
 public static void main(String[] args) {
	Female f = new Female("小乔") ;
	Man m = new Man() ;
	f.order(m);
}
}


class Female{
	private String name ;
	public Female(String name) {
		this.name = name ;
	}
	
	public void order(Man man){
		man.doThing1() ;  //初始想法是设计一个Man类里设计doThing1方法、doThing2方法
						//但是这样不好扩展，比如需要增加任务时，就需要修改Man类，和Female类的order方法
	//这时候可以考虑增加一个Command类
	}
}

class Man{
	public void doThing1(){
		System.out.println("man doThing1");
	}
}