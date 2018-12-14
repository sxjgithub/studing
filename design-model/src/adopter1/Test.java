package adopter1;

/**
 * 适配器模式
 * 要求：现在有一台电脑有USB接口，现在需要把一个S1接口的键盘用在该电脑上，则需要制定一个S1-->USB的适配器(S1ToUSB类)
 * @author sxj
 *
 */
public class Test {
	public static void main(String[] args) {
		S1 keyboard = new S1_keyboard();
		
		USB usb = new S1ToUSB(keyboard); //传入S1对象 ，通过S1ToUSB实现USB接口，转换成USB对象
		
		Computer computer = new Computer();
		
		computer.accessDevice(usb);
		
		computer.useUSBDevice();
	}
	
}

class Computer{
	USB usb;
	
	public void accessDevice(USB usb){
		this.usb = usb;
	}
	public void useUSBDevice(){
		usb.use();
	}
}
interface USB{
	void use();
}

class S1ToUSB implements USB{ //适配器实现USB接口是重点
	S1 s1;
	public S1ToUSB(S1 s1) {
		this.s1 = s1;
	}
	
	@Override
	public void use() {
		s1.use();
	}
}


interface S1{
	void use();
}

class S1_keyboard implements S1{

	@Override
	public void use() {
		System.out.println("s1接口的键盘");
	}
	
}