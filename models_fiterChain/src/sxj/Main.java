package sxj;
import org.omg.CORBA.PRIVATE_MEMBER;

import sxj.*;

public class Main {
	public static void main(String[] args) {
		String msg = "��ð���������С����<javascript>, gogo,���У�Ц��" ;
		FiterChain fiterChain = new FiterChain() ;
		fiterChain.add(new Html_fiter());
		fiterChain.add(new Sentive_fiter());
		//MsgProcessor mp = new MsgProcessor(msg) ;
		MsgProcessor mp = new MsgProcessor() ;
		mp.setMsg(msg);
		mp.setFiterChain(fiterChain);
		String msg_processed = mp.process() ;
		
		System.out.println(msg_processed) ;
	}
}
