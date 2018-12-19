package constructor1;
/**
 * ������ģʽ����builder������(���)  +  ��director��װ�䣨�����
 * ������Ҫ����һ������������������������ӣ���ǣ�
 * @author sxj
 *
 */
public class Constructor_test {
	public static void main(String[] args) {
		Car car = new Car();
		CarBuilder cb = new SxjCarBuilder();
		CarConstructor cc = new SxjCarConstructor(car);
		Car newCar = cc.createCar(cb);
		System.out.println(newCar);
	}
}	

class Car{
	private Engine engine;
	private Tyre tyre;
	private Shell shell;
	
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Tyre getTyre() {
		return tyre;
	}
	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}
	public Shell getShell() {
		return shell;
	}
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Engine:"+ engine.getName() + "|" 
				+ "tyre:" + tyre.getName() + "|"
				+ "shell:" + shell.getName();
	}
}


class Engine{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Engine(String name) {
		super();
		this.name = name;
	}
	
}
class Tyre{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tyre(String name) {
		super();
		this.name = name;
	}	
	
}

class Shell{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Shell(String name) {
		super();
		this.name = name;
	}
}
class SxjCarBuilder implements CarBuilder{
	
	@Override
	public Engine build_engine() {
		System.out.println("��װ������");
		return new Engine("sxj�Ʒ�����");
	}

	@Override
	public Tyre build_tyre() {
		System.out.println("��װ��̥");
		return new Tyre("sxj����̥");
	}

	@Override
	public Shell build_shell() {
		System.out.println("��װ���");
		return new Shell("sxj�����");
	}

}

class SxjCarConstructor implements CarConstructor{
	
	Car car;
	public SxjCarConstructor(Car car) {
		this.car = car;
	}
	@Override
	public Car createCar(CarBuilder cb) {
		Engine e = cb.build_engine();
		Tyre t = cb.build_tyre();
		Shell s = cb.build_shell();
		
		this.car.setEngine(e);
		this.car.setTyre(t);
		this.car.setShell(s);
		return this.car;
	}

	

	
	
}