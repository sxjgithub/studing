package state1;
/*state模式：根据不同的状态选择不同的方法.
 * */

/*一个Female有方法：cry、simle 。
 * 
 * 现在要求女孩能够根据状态做出 cry，比如喜极而泣或悲伤而泣，怎么做？
 * */
public class State_test {
	public static void main(String[] args) {
		Female f = new Female() ;
		f.cry() ;
	}
}

class Female {
	
	public void cry(){
		System.out.println("cry...");
	}
	
	public void simle(){
		System.out.println("simle...");
	}
}