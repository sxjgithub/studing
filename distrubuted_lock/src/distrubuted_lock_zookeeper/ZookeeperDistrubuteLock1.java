package distrubuted_lock_zookeeper;

import java.util.concurrent.CountDownLatch;
/**
 * 基于zookeeper的独占锁
 * @author Administrator
 *
 */
public class ZookeeperDistrubuteLock1 extends ZookeeperAbstractLock {
	private CountDownLatch countDownLatch = null;
	
	//尝试获得锁
	@Override
	public boolean tryLock(){
		try{
			zkClient.createEphemeral(PATH);
			return true;
		}catch(Exception e){
			//如果创建失败报出异常
			//e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void waitLock() {
	
		IZkDataListener izkDataListener = new IZkDataListener() {
			public void handleDataDeleted(String path) throws Exception {
				//唤醒被等待的线程
				if(countDownLatch != null){
					countDownLatch.countDown(); //如果锁结点删除了，就countDownLatch为0了
				}
			}
			public void handleDataChange(String path, Object data) throws Exception{
				
			}
		};
		//注册事件
		zkClient.subscribeDataChange(PATH, izkDataListener);
		
		//如果结点存
		if(zkClient.exists(PATH)){
			countDownLatch = new CountDownLatch(1);//如果锁结点存在，则new CountDownLatch(1)
			try{
				//等待，一直等到接受到事件通知
				countDownLatch.await();//锁结点删除了，countDownLatch为0了,就可以继续向下运行了。
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
