package sxj;

public class Sentive_fiter implements Fiter {

	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replace("����", "") ;
		return msg_processed ;
	}

}
