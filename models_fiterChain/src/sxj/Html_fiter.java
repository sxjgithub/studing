package sxj;

public class Html_fiter implements Fiter {

	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replace("<", "[").replace(">", "]") ;
		return msg_processed ;	
	}

}
