package bridge1;
/*�Ž�ģʽ����Ҫ������������ͬγ�ȵ��ࣨ���̳�Ϊ���ԣ����������
 * */

/*һ���е�׷Ů��
 * */
public class Bridge_test {
	public static void main(String[] args) {
		Man man = new Man("����") ;
		Felman f = new Felman() ;
		man.catching(f) ; //׷	
	}
}

class Man{
	private String name ;
	public Man(String name) {
		this.name = name ;
	}
	public void catching(Felman felman){
		Gift gift = new Gift() ;
		this.give(felman, gift) ;
	}//������Gift����Flower��Ring����WildGift��SoftGift���֣�
	// ������ Sotf + Flower ����Gift�أ�
	//����Sotf�̳� Flower���֣���Ϊ����������ͬһγ�ȣ��ҹ���̳в���
	
	private void give(Felman f, Gift g){}
}

class Flower extends Gift{}
class Ring extends Gift{}
class WildGift extends Gift{} //��ʼ��ƶ��̳�Gift
class SoftGift extends Gift{}

class Felman{}