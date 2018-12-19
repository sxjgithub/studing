package factory_my2;

/*思考：随意定制产品类型，  和任意定制产品生产过程。。。
 * 		且一个工厂可以生产各种不同类型的产品
 * */
public class Factory_test {
	public static void main(String[] args) {
		//工厂Factory_one 可以生产Car 和 Apple
		AbstractFactory fo = new Factory_one() ; 
		//现在另一个工厂生产两种相像的产品：比如Orange 和 Plane
		//且方法是getOrangeInfo() 和 getPlaneInfo()
		//?????怎样在不大改动的情况下 能替换成这个类？
		
		Vehicle vh = fo.getVehicle() ;
		vh.getVehicleInfo();
		
		Fruit ft = fo.getFruit() ;
		ft.getFruitInfo();
	}
}//现在只需要换生产类就行了其他不需要改变，例如：AbstractFactory fo = new Factory_two()

class Factory_one extends AbstractFactory{ // 继承抽象类
	public Vehicle getVehicle(){
		return new Car() ;
	}
	public Fruit getFruit(){
		return new Apple();
	}
}
class Car extends Vehicle{ 
	@Override
	public void getVehicleInfo(){
		System.out.println("car.... ");
	}
}

class Apple extends Fruit{
	@Override
	public void getFruitInfo(){
		System.out.println("apple....");
	}
}


