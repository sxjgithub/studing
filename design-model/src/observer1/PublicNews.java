package observer1;

import java.util.ArrayList;
import java.util.List;
/**
 * 怎样使Subscriber 和 publicNews 产生联系呢？
 * 继承？。。。不行
 * 那就组合，让PublicNews 里有Subscriber对象。  
 * 把具体的Subscriber 传入PublicNews，
 * -----------当PublicNews改变状态值时，把改变的状态值赋给里面的Subscriber对象
 * @author 
 *
 */
public class PublicNews {
	protected List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	public void register(Subscriber subscriber){
		subscribers.add(subscriber);
	}
	
	/**
	 * 这是核心，
	 */
	protected void notifyAllSubscribers(){
		for(Subscriber subscriber : subscribers){
			subscriber.update(this); //注意这里
		}
	}
}
