package sxj;
import sxj.* ;
/*需求：
模拟web过滤器：比如有服务器收到一个字符串消息，需要把里面的的敏感字和谐掉，把里面一些特殊字符替换掉。
然后在发送出去。
 * */

/*初步构想是这样的：构建一个MsgProcessor类，传入要处理的字符串msg,
通过MsgProcessor类里的process方法处理msg，并返回处理好的字符
* */
public class Main {
	public static void main(String[] args) {
		String msg = "你好啊，哈哈，<script>,gogo,敏感，笑脸。" ;
		
		FiterChain fc = new FiterChain() ;
		fc.fiterAdd(new HtmlFiter()).fiterAdd(new SentiveFiter());
		
		FiterChain fc1= new FiterChain();
		fc1.fiterAdd(new FaceFiter()).fiterAdd(new EndFace()) ;
		fc1.fiterAdd(fc) ;//这里注意了????????????????????????????????????
		
		MsgProcessor mp = new MsgProcessor(msg) ;
		mp.setFiterChain(fc1);
		String msg_processed = mp.process();
		
		System.out.println(msg_processed);
	}
}
