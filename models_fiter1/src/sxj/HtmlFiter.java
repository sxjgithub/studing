package sxj;

public class HtmlFiter implements Fiter {

	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replace("<", "[").replace(">", "]") ;
		return msg_processed ;
	}

}
