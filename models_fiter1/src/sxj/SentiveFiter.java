package sxj;

public class SentiveFiter implements Fiter {

	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replace("����", "") ; 
		return msg_processed ;
	}

}
