package factory_my2;
/*简单工厂和抽象工厂的区别：简单工厂好扩展产品种类(比如生产水果，在扩展交通工具，容易造成工厂泛滥，有一个品种就建一个工厂)，
但不好扩展产品系列（比如Fruit里有了苹果，不好扩展橘子）
抽象工厂恰恰相反
*/

/*简单工厂
 * 思考：随意定制交通工具类型，  和任意定制生产过程。。。
 * */
public class Factory_test {
	public static void main(String[] args) {
		//CarFactory car_factory = new CarFactory() ;
		//Car car = car_factory.getInstance() ;    //此时想换成开飞机就需要把代码都改了
		//car.run();
		
		VehicleFactory vehicle_factory = new PlaneFactory() ; //只需要换生产过程
		Moveable vehicle = vehicle_factory.getInstance() ;
		vehicle.run(); 
		
		//如果想生产新的交通工具 很方便，随意定制，主方法里代码改动很少
		//但要生产水果，就必须重新建立新的水果工厂
	}
}

class Car implements Moveable{  //通过实现Moveable接口随意定制交通工具类型
	@Override
	public void run(){
		System.out.println("car run run");
	}
}
class CarFactory extends VehicleFactory{ //继承VehicleFactory任意的定制生产过程
	public  Moveable getInstance(){
		return new Car();
	}
}

class Plane implements Moveable{  //同样随意定制交通工具类型
	@Override
	public void run(){
		System.out.println("plane run run") ;
	}
}
class PlaneFactory extends VehicleFactory{ //继承VehicleFactory任意的定制生产过程
	public  Moveable getInstance(){
		return new Plane();
	}
}

