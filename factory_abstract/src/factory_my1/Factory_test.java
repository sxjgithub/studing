package factory_my1;
/*简单工厂和抽象工厂的区别：简单工厂好扩展产品种类(比如生产水果，在扩展交通工具，容易造成工厂泛滥，有一个品种就建一个工厂)，
				但不好扩展产品系列（比如Fruit里有了苹果，不好扩展橘子）
		抽象工厂恰恰相反
*/

/*抽象工厂：一个工厂可以生产多种种类的产品
 * 
 * 思考：随意定制产品类型，  和任意定制产品生产过程。。。
 * 		且一个工厂可以生产各种不同类型的产品
 * */
public class Factory_test {
	public static void main(String[] args) {
		//工厂Factory_one 可以生产Car 和 Apple
		Factory_one fo = new Factory_one() ; 
		//现在另一个工厂生产两种相像的产品：比如Orange 和 Plane
		//且方法是getOrangeInfo() 和 getPlaneInfo()
		//?????怎样在不大改动的情况下 能替换成这个类？
		
		Car car = fo.getCar() ;
		car.getCarInfo();
		
		Apple apple = fo.getApple() ;
		apple.getAppleInfo();
	}
}

class Factory_one{ // 工厂可以生产Car 和 Apple
	public Car getCar(){
		return new Car() ;
	}
	public Apple getApple(){
		return new Apple();
	}
}
class Car {
	public void getCarInfo(){
		System.out.println("car.... ");
	}
}

class Apple{
	public void getAppleInfo(){
		System.out.println("apple....");
	}
}


