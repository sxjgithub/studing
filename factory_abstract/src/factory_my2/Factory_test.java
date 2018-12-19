package factory_my2;

/*˼�������ⶨ�Ʋ�Ʒ���ͣ�  �����ⶨ�Ʋ�Ʒ�������̡�����
 * 		��һ�����������������ֲ�ͬ���͵Ĳ�Ʒ
 * */
public class Factory_test {
	public static void main(String[] args) {
		//����Factory_one ��������Car �� Apple
		AbstractFactory fo = new Factory_one() ; 
		//������һ������������������Ĳ�Ʒ������Orange �� Plane
		//�ҷ�����getOrangeInfo() �� getPlaneInfo()
		//?????�����ڲ���Ķ�������� ���滻������ࣿ
		
		Vehicle vh = fo.getVehicle() ;
		vh.getVehicleInfo();
		
		Fruit ft = fo.getFruit() ;
		ft.getFruitInfo();
	}
}//����ֻ��Ҫ���������������������Ҫ�ı䣬���磺AbstractFactory fo = new Factory_two()

class Factory_one extends AbstractFactory{ // �̳г�����
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


