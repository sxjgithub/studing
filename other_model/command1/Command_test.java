package command1;
/*Female �������� (order)����������Man ���ܶ���
 * */
public class Command_test {
 public static void main(String[] args) {
	Female f = new Female("С��") ;
	Man m = new Man() ;
	f.order(m);
}
}


class Female{
	private String name ;
	public Female(String name) {
		this.name = name ;
	}
	
	public void order(Man man){
		man.doThing1() ;  //��ʼ�뷨�����һ��Man�������doThing1������doThing2����
						//��������������չ��������Ҫ��������ʱ������Ҫ�޸�Man�࣬��Female���order����
	//��ʱ����Կ�������һ��Command��
	}
}

class Man{
	public void doThing1(){
		System.out.println("man doThing1");
	}
}