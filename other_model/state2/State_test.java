package state2;
/*stateģʽ�����ݲ�ͬ��״̬ѡ��ͬ�ķ���.
 * */

/*һ��Female�з�����cry��simle ��
 * 
 * ����Ҫ��Ů���ܹ�����״̬���� cry������ϲ���������˶�������ô����
 * */
public class State_test {
	public static void main(String[] args) {
		Female f = new Female() ;
		f.cry() ;
	}
}

class Female {
	State state = new HappyState() ; //���ڿ���ѡ��״̬��
	public void cry(){
		//System.out.println("cry...");
		state.cry(); //����״̬��������
	}
	
	public void simle(){
		state.simle() ;
	}
}

//����״̬���࣬�̳���State{ cry(); simle(); }
class HappyState extends State{ 
	@Override
	public void cry() {
		System.out.println("ϲ������");
	}
	@Override
	public void simle() {
		System.out.println("���˵�Ц") ;
	}
}
class UnHappleState extends State{
	@Override
	public void cry() {
		System.out.println("���˶���");
	}
	@Override
	public void simle() {
		System.out.println("����Ц");
	}
}