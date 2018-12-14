package observer1;
/**
 * 场景：有公共新闻，当有新的新闻时候，都会向订阅者推送。（订阅者都会收到新的新闻）
 * 即 观察者都会观察到被观察者的事务的改变。
 * @author Administrator
 *
 */

public class Test {
	public static void main(String[] args) {
		SportsPublicNews publicNews = new SportsPublicNews(); //公共新闻对象
		
		SportSubscriber subscriber1 = new SportSubscriber(); //订阅者1
		SportSubscriber subscriber2 = new SportSubscriber();
		SportSubscriber subscriber3 = new SportSubscriber();
		
		publicNews.register(subscriber1); //订阅者1订阅该新闻
		publicNews.register(subscriber2);
		publicNews.register(subscriber3);
		
		publicNews.updateSportNews("新的一篇新闻"); //写了一篇新的新闻
		
		System.out.println(subscriber1.getSportMsg()); //订阅者1收到推送的新闻
		System.out.println(subscriber2.getSportMsg());
		System.out.println(subscriber3.getSportMsg());
	}
}


class SportsPublicNews extends PublicNews{
	String sportMsg;
	
	public void updateSportNews(String sportMsg){
		this.sportMsg = sportMsg;
		
		notifyAllSubscribers();
	}

	public String getSportMsg() {
		return sportMsg;
	}

	public void setSportMsg(String sportMsg) {
		this.sportMsg = sportMsg;
	}
	
}

class SportSubscriber implements Subscriber{
	String sportMsg;
	
	
	@Override
	public void update(PublicNews publicNews) {
		this.sportMsg = ((SportsPublicNews)publicNews).getSportMsg();
	}



	public String getSportMsg() {
		return sportMsg;
	}



	public void setSportMsg(String sportMsg) {
		this.sportMsg = sportMsg;
	}
	
	
}
