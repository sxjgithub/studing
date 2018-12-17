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
	//OS处理任务
	public void register(CallBack callBack) {
		
		list.add(callBack);  //加入队列
	}
	 
	//怎么多线程同时处理多个callback
	public void update(CallBack callBack){
		callBack.doExcute();
	}
	
	//怎么通知该任务已经完成？  任务的
	public void notifyAllClient(){
		
	}

	@Override
	public void run() {
		
		CallBack callBack = list.remove(list.size()-1); //从队列中取出任务
		update(callBack); //执行任务
		
		//通知Client该任务完成。
		callBack.setFlag(true);
		
	}
	
}
