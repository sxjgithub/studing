package bridge2;
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
		Gift  gift = new WildGift(new Flower()) ;
		this.give(felman, gift) ;
	}//现在是Gift既有Flower、Ring又有WildGift、SoftGift这种，
	// 怎样送 Sotf + Flower 这种Gift呢？
	//不能Sotf继承 Flower这种，因为两种类似在同一纬度，且过多继承不好
	
	private void give(Felman f, Gift g){
	}
}

//class Flower extends Gift{}
class Flower extends GiftImpl{//改继承GiftImpl
	public Flower() {
		System.out.println("Flower");
	}
}  
//class Ring extends Gift{}
class Ring extends GiftImpl{}

class WildGift extends Gift{   //class Gift{ GiftImpl gi ;} 
	public WildGift(GiftImpl gi) { // 使GiftImpl 成为Gift的成员变量，则继承类Gift的WildGift可以可以随意传入Flower等，使Flower 与 WildGift 组合
		this.gi = gi ;
		System.out.println("wildGift");
	}
} 
class SoftGift extends Gift{
	public SoftGift (GiftImpl gi) {
		this.gi = gi ;
	}
}

class Felman{}