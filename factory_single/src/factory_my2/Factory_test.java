package factory_my2;
/*�򵥹����ͳ��󹤳������𣺼򵥹�������չ��Ʒ����(��������ˮ��������չ��ͨ���ߣ�������ɹ������ģ���һ��Ʒ�־ͽ�һ������)��
��������չ��Ʒϵ�У�����Fruit������ƻ����������չ���ӣ�
���󹤳�ǡǡ�෴
*/

/*�򵥹���
 * ˼�������ⶨ�ƽ�ͨ�������ͣ�  �����ⶨ���������̡�����
 * */
public class Factory_test {
	public static void main(String[] args) {
		//CarFactory car_factory = new CarFactory() ;
		//Car car = car_factory.getInstance() ;    //��ʱ�뻻�ɿ��ɻ�����Ҫ�Ѵ��붼����
		//car.run();
		
		VehicleFactory vehicle_factory = new PlaneFactory() ; //ֻ��Ҫ����������
		Moveable vehicle = vehicle_factory.getInstance() ;
		vehicle.run(); 
		
		//����������µĽ�ͨ���� �ܷ��㣬���ⶨ�ƣ������������Ķ�����
		//��Ҫ����ˮ�����ͱ������½����µ�ˮ������
	}
}

class Car implements Moveable{  //ͨ��ʵ��Moveable�ӿ����ⶨ�ƽ�ͨ��������
	@Override
	public void run(){
		System.out.println("car run run");
	}
}
class CarFactory extends VehicleFactory{ //�̳�VehicleFactory����Ķ�����������
	public  Moveable getInstance(){
		return new Car();
	}
}

class Plane implements Moveable{  //ͬ�����ⶨ�ƽ�ͨ��������
	@Override
	public void run(){
		System.out.println("plane run run") ;
	}
}
class PlaneFactory extends VehicleFactory{ //�̳�VehicleFactory����Ķ�����������
	public  Moveable getInstance(){
		return new Plane();
	}
}

