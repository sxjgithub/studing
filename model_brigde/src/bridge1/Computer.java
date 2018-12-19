package bridge1;

public class Computer {
	Brand brand;
	public Computer(Brand brand) {
		this.brand = brand;
	}
	void info(){
		this.brand.info();
	}
}

class Desktop extends Computer{

	public Desktop(Brand brand) {
		super(brand);
	}
	
	@Override
	void info() {
		super.info();
		System.out.println("̨ʽ��");
	}
	
}

class Laptop extends Computer{

	public Laptop(Brand brand) {
		super(brand);
	}
	
	@Override
	void info() {
		super.info();
		System.out.println("�ʼǱ�");
	}
	
}
