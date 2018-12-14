package decorator1;



public interface ICar {
	void move();
}

class Car implements ICar{

	@Override
	public void move() {
		System.out.println("陆地上跑！");
	}
	
}

//Decorator 装饰角色
class SuperCar implements ICar{
	private ICar car;
	
	
	public SuperCar(ICar car) {
		super();
		this.car = car;
	}

	@Override
	public void move() {
		this.car.move();
	}
	
}

class FlyCar extends SuperCar{

	public FlyCar(ICar car) {
		super(car);
	}
	
	protected void fly(){
		System.out.println("空中飞！");
	}
	
	@Override
	public void move() {
		super.move();
		fly();
	}
}

class WaterCar extends SuperCar{

	public WaterCar(ICar car) {
		super(car);
	}
	
	protected void swim(){
		System.out.println("水中游！");
	}
	
	@Override
	public void move() {
		super.move();
		swim();
	}
}