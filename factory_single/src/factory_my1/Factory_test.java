package factory_my1;

/*
 * 思考：随意定制交通工具类型，  和任意定制生产过程。。。
 * */
public class Factory_test {
	public static void main(String[] args) {
		CarFactory car_factory = new CarFactory() ;
		Car car = car_factory.getInstance() ;
		car.run();
	}
}

class Car {  //定制了一个交通工具类型
	public void run(){
		System.out.println("car run run");
	}
}
class CarFactory { //定制了生产过程
	public Car getInstance(){
		return new Car();
	}
}

class Plane{  //再定制了一个交通工具类型
	public void run(){
		System.out.println("plane run run") ;
	}
}
class PlaneFactory { //定制了其生产过程
	public Plane getInstance(){
		return new Plane();
	}
}
//如何随意定制交通类型？
