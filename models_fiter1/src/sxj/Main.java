package sxj;
import sxj.* ;
/*����
ģ��web�������������з������յ�һ���ַ�����Ϣ����Ҫ������ĵ������ֺ�г����������һЩ�����ַ��滻����
Ȼ���ڷ��ͳ�ȥ��
 * */

/*���������������ģ�����һ��MsgProcessor�࣬����Ҫ������ַ���msg,
ͨ��MsgProcessor�����process��������msg�������ش���õ��ַ�
* */
public class Main {
	public static void main(String[] args) {
		String msg = "��ð���������<script>,gogo,���У�Ц����" ;
		
		FiterChain fc = new FiterChain() ;
		fc.fiterAdd(new HtmlFiter()).fiterAdd(new SentiveFiter());
		
		FiterChain fc1= new FiterChain();
		fc1.fiterAdd(new FaceFiter()).fiterAdd(new EndFace()) ;
		fc1.fiterAdd(fc) ;//����ע����????????????????????????????????????
		
		MsgProcessor mp = new MsgProcessor(msg) ;
		mp.setFiterChain(fc1);
		String msg_processed = mp.process();
		
		System.out.println(msg_processed);
	}
}
