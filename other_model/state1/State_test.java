package state1;
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
	
	public void cry(){
		System.out.println("cry...");
	}
	
	public void simle(){
		System.out.println("simle...");
	}
}