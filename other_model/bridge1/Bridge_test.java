package bridge1;
/*桥接模式：想要任意的组合两个同纬度的类（话继承为属性），排列组合
 * */

/*一个男的追女的
 * */
public class Bridge_test {
	public static void main(String[] args) {
		Man man = new Man("张三") ;
		Felman f = new Felman() ;
		man.catching(f) ; //追	
	}
}

class Man{
	private String name ;
	public Man(String name) {
		this.name = name ;
	}
	public void catching(Felman felman){
		Gift gift = new Gift() ;
		this.give(felman, gift) ;
	}//现在是Gift既有Flower、Ring又有WildGift、SoftGift这种，
	// 怎样送 Sotf + Flower 这种Gift呢？
	//不能Sotf继承 Flower这种，因为两种类似在同一纬度，且过多继承不好
	
	private void give(Felman f, Gift g){}
}

class Flower extends Gift{}
class Ring extends Gift{}
class WildGift extends Gift{} //初始设计都继承Gift
class SoftGift extends Gift{}

class Felman{}