package factory_my1;
/*�򵥹����ͳ��󹤳������𣺼򵥹�������չ��Ʒ����(��������ˮ��������չ��ͨ���ߣ�������ɹ������ģ���һ��Ʒ�־ͽ�һ������)��
				��������չ��Ʒϵ�У�����Fruit������ƻ����������չ���ӣ�
		���󹤳�ǡǡ�෴
*/

/*���󹤳���һ����������������������Ĳ�Ʒ
 * 
 * ˼�������ⶨ�Ʋ�Ʒ���ͣ�  �����ⶨ�Ʋ�Ʒ�������̡�����
 * 		��һ�����������������ֲ�ͬ���͵Ĳ�Ʒ
 * */
public class Factory_test {
	public static void main(String[] args) {
		//����Factory_one ��������Car �� Apple
		Factory_one fo = new Factory_one() ; 
		//������һ������������������Ĳ�Ʒ������Orange �� Plane
		//�ҷ�����getOrangeInfo() �� getPlaneInfo()
		//?????�����ڲ���Ķ�������� ���滻������ࣿ
		
		Car car = fo.getCar() ;
		car.getCarInfo();
		
		Apple apple = fo.getApple() ;
		apple.getAppleInfo();
	}
}

class Factory_one{ // ������������Car �� Apple
	public Car getCar(){
		return new Car() ;
	}
	public Apple getApple(){
		return new Apple();
	}
}
class Car {
	public void getCarInfo(){
		System.out.println("car.... ");
	}
}

class Apple{
	public void getAppleInfo(){
		System.out.println("apple....");
	}
}


