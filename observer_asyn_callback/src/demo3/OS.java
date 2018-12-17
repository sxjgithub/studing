package demo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
	
public class OS implements Runnable{
	Vector<CallBack> list = new Vector<CallBack>();
	
	
	public OS(){}
	
	public OS (CallBack callBack){
		register(callBack);
	}
	//ע������
	public void register(CallBack callBack) {
		
		list.add(callBack);  //�������
	}
	 
	//��������
	//��ô���߳�ͬʱ������callback
	public void update(CallBack callBack){
		callBack.doExcute();
	}
	
	//��ô֪ͨ�������Ѿ���ɣ�  �����
	public void notifyAllClient(){
		
	}

	@Override
	public void run() {
		
		CallBack callBack = list.remove(list.size()-1); //�Ӷ�����ȡ������
		update(callBack); //ִ������
		
		//֪ͨClient��������ɡ�
		callBack.Finished();
		
	}
	
}
