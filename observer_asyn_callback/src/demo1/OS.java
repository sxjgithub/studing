package demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
	
public class OS {
	List<CallBack> list = new ArrayList<CallBack>();
	
	//OS��������
	public void register(int x, int y, CallBack callBack) {
		
		list.add(callBack);  //�������
		
		update(x, y, callBack);//ȡ������ִ�С�
	}
	 
	//��ô���߳�ͬʱ������callback
	public void update(int x, int y, CallBack callBack){
		callBack.doExcute(x, y);
		
	}
	//��ô֪ͨ�������Ѿ���ɣ�  �����
	public void notifyAllClient(){
		for(Map.Entry<Client, CallBack> entry : mapping.entrySet()){
			Client.set
		}
	}
	
}
