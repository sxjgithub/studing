package demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
	
public class OS {
	List<CallBack> list = new ArrayList<CallBack>();
	
	//OS处理任务
	public void register(int x, int y, CallBack callBack) {
		
		list.add(callBack);  //加入队列
		
		update(x, y, callBack);//取出任务执行。
	}
	 
	//怎么多线程同时处理多个callback
	public void update(int x, int y, CallBack callBack){
		callBack.doExcute(x, y);
		
	}
	//怎么通知该任务已经完成？  任务的
	public void notifyAllClient(){
		for(Map.Entry<Client, CallBack> entry : mapping.entrySet()){
			Client.set
		}
	}
	
}
