package factory_my1;

/*
 * ˼�������ⶨ�ƽ�ͨ�������ͣ�  �����ⶨ���������̡�����
 * */
public class Factory_test {
	public static void main(String[] args) {
		CarFactory car_factory = new CarFactory() ;
		Car car = car_factory.getInstance() ;
		car.run();
	}
}

class Car {  //������һ����ͨ��������
	public void run(){
		System.out.println("car run run");
	}
}
class CarFactory { //��������������
	public Car getInstance(){
		return new Car();
	}
}

class Plane{  //�ٶ�����һ����ͨ��������
	public void run(){
		System.out.println("plane run run") ;
	}
}
class PlaneFactory { //����������������
	public Plane getInstance(){
		return new Plane();
	}
}
//������ⶨ�ƽ�ͨ���ͣ�
