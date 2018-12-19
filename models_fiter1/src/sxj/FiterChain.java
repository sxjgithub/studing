package sxj;

import java.util.ArrayList;
import java.util.List;

import sxj.* ;
/*管理过滤类 
 * */

//FiterChain  被称为“过滤条”，其管理一系列过滤规则
//注意这里实现Fiter接口，使一个FiterChain对象可以像Fiter一样，能够被FiterChain使用fiterAdd方法添加
public class FiterChain implements Fiter{  //??????????????????????????????????????????
	private List<Fiter> fiters = new ArrayList<>() ;
	
	/*public void fiterAdd(Fiter fiter){
		fiters.add(fiter) ;
	}*/  
	public FiterChain fiterAdd(Fiter fiter){
		fiters.add(fiter) ;
		return this ;   //使用这样就可以使用链式编程：fiterChain.fiterAdd(one).fiterAdd(two)？？？？？？？？？？？？？？？？？
	}
	// 试着把doFiter方法改成doFiters,然后就必须要实现Fiter的doFiter方法，也行，但是累赘？？？？？？？？
	/*@Override
	public String doFiter(String msg) {
		String msg_processed = msg;
		for(Fiter fiter:fiters){
		msg_processed = fiter.doFiter(msg_processed) ;
		}
		return msg_processed ;
	}*/
	public String doFiter(String msg){
	//public String doFiters(String msg){
		String msg_processed = msg;
		for(Fiter fiter:fiters){
			msg_processed = fiter.doFiter(msg_processed) ;
			//（1）当fiter=Fiter对象时，是进入Fiter的doFiter方法执行。
			//（2）当执行到fiter=FiterChain对象时，  
			//fiter.doFiter方法还是会进入FiterChain的doFiter方法里执行，然后在执行Fiter.doFiter方法
		}
		return msg_processed ;
	}
}
