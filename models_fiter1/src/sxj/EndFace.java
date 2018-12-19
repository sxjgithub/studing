package sxj;

public class EndFace implements Fiter{
	@Override
	public String doFiter(String msg) {
		String msg_processed = msg.replaceFirst("$+", "---“—æ≠…Û∫À") ;
		return msg_processed ;
	}
}
