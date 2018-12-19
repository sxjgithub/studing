package sxj;
import sxj.* ;

public class MsgProcessor {
	private String msg ;
	private FiterChain fiterChain ;
	
	public void setFiterChain(FiterChain fiterChain) {
		this.fiterChain = fiterChain;
	}
	public FiterChain getFiterChain() {
		return fiterChain;
	}
	
	public MsgProcessor(String msg) {
		this.msg = msg ;
	}
	
	public String process(){
		String msg_processed = this.msg;
		
		 msg_processed = this.fiterChain.doFiter(msg_processed) ;
		return msg_processed ;
	}	
}

