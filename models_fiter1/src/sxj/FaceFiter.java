package sxj;

public class FaceFiter implements Fiter{
	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replace("Ð¦Á³", "^v^") ;
		return msg_processed ;
	}
}
