package sxj;

import java.util.ArrayList;
import java.util.List;

import sxj.* ;
/*��������� 
 * */

//FiterChain  ����Ϊ�����������������һϵ�й��˹���
//ע������ʵ��Fiter�ӿڣ�ʹһ��FiterChain���������Fiterһ�����ܹ���FiterChainʹ��fiterAdd�������
public class FiterChain implements Fiter{  //??????????????????????????????????????????
	private List<Fiter> fiters = new ArrayList<>() ;
	
	/*public void fiterAdd(Fiter fiter){
		fiters.add(fiter) ;
	}*/  
	public FiterChain fiterAdd(Fiter fiter){
		fiters.add(fiter) ;
		return this ;   //ʹ�������Ϳ���ʹ����ʽ��̣�fiterChain.fiterAdd(one).fiterAdd(two)����������������������������������
	}
	// ���Ű�doFiter�����ĳ�doFiters,Ȼ��ͱ���Ҫʵ��Fiter��doFiter������Ҳ�У�������׸����������������
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
			//��1����fiter=Fiter����ʱ���ǽ���Fiter��doFiter����ִ�С�
			//��2����ִ�е�fiter=FiterChain����ʱ��  
			//fiter.doFiter�������ǻ����FiterChain��doFiter������ִ�У�Ȼ����ִ��Fiter.doFiter����
		}
		return msg_processed ;
	}
}
