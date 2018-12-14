package decorator1;
/**
 * 装饰模式：Car有“陆地上跑！”功能，要求把Car装饰增加空中飞！功能，甚至增加其他功能
 * 用继承会导致类膨胀
 * @author sxj
 *
 */
public class Test {
	public static void main(String[] args) {
		Car car = new Car();  //需要装饰的对象
		car.move();
		
		System.out.println("-----------------------------------------");
		FlyCar flyCar = new FlyCar(car); //把car装饰（增加）flyCar的功能
		flyCar.move();
		
		System.out.println("-----------------------------------------");
		WaterCar waterCar = new WaterCar(flyCar); // 把flyCar 装饰(增加)waterCar的功能
		waterCar.move();
	}	
}
