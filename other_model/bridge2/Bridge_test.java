package bridge2;
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
		Gift  gift = new WildGift(new Flower()) ;
		this.give(felman, gift) ;
	}//������Gift����Flower��Ring����WildGift��SoftGift���֣�
	// ������ Sotf + Flower ����Gift�أ�
	//����Sotf�̳� Flower���֣���Ϊ����������ͬһγ�ȣ��ҹ���̳в���
	
	private void give(Felman f, Gift g){
	}
}

//class Flower extends Gift{}
class Flower extends GiftImpl{//�ļ̳�GiftImpl
	public Flower() {
		System.out.println("Flower");
	}
}  
//class Ring extends Gift{}
class Ring extends GiftImpl{}

class WildGift extends Gift{   //class Gift{ GiftImpl gi ;} 
	public WildGift(GiftImpl gi) { // ʹGiftImpl ��ΪGift�ĳ�Ա��������̳���Gift��WildGift���Կ������⴫��Flower�ȣ�ʹFlower �� WildGift ���
		this.gi = gi ;
		System.out.println("wildGift");
	}
} 
class SoftGift extends Gift{
	public SoftGift (GiftImpl gi) {
		this.gi = gi ;
	}
}

class Felman{}