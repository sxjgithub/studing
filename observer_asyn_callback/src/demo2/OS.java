package demo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
	
public class OS implements Runnable{
	List<CallBack> list = new ArrayList<CallBack>();
	
	
	public OS(){}
	public OS (CallBack callBack){
		register(callBack);
	}
	//OS��������
	public void register(CallBack callBack) {
		
		list.add(callBack);  //�������
	}
	 
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
		callBack.setFlag(true);
		
	}
	
}
