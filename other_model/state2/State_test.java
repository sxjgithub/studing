package state2;
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
	State state = new HappyState() ; //现在可以选择状态了
	public void cry(){
		//System.out.println("cry...");
		state.cry(); //根据状态做出动作
	}
	
	public void simle(){
		state.simle() ;
	}
}

//两种状态的类，继承了State{ cry(); simle(); }
class HappyState extends State{ 
	@Override
	public void cry() {
		System.out.println("喜极而泣");
	}
	@Override
	public void simle() {
		System.out.println("高兴的笑") ;
	}
}
class UnHappleState extends State{
	@Override
	public void cry() {
		System.out.println("悲伤而泣");
	}
	@Override
	public void simle() {
		System.out.println("疯癫的笑");
	}
}