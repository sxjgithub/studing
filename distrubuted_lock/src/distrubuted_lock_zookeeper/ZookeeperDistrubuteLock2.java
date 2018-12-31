package distrubuted_lock_zookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import distrubuted_lock1.*;

public class ZookeeperDistrubuteLock2 extends ZookeeperAbstractLock{
	private CountDownLatch countDownLatch = null;
	private String beforePath; //当前请求的结点的前一个结点
	private String currentPath; //当前请求的结点
	
	public public ZookeeperDistrubuteLock2() {
		if(!this.zkClient.exists(PATH2)){
			this.zkClient.creatPersistent(PATH2);
		}
	}
	

	@Override
	public boolean tryLock() {
		//如果currentPath为空，则为第一次尝试加锁，第一次加锁赋值currentPath
		if(currentPath == null || currentPath.length()<= 0) {
			currentPath = this.zkClient.createEphemeralSequential(path:PATH2 + "/", data:"lock");
		}
		//获取所有临时结点并排序，临时结点名称为自增长的字符串如：0000040
		List<String> childrens = this.zkClient.getChildren(PATH2);
		Collections.sort(childrens);
		
		if(currentPath.equals(PATH2 + "/" + childrens.get(0))){//如果当前结点在所有结点中排名第一则获取锁成功
			return true;
		}else{//如果当前结点在所有结点中排名中不是排名第一，则获取前面的结点名称，并赋值给beforePath
			int wz = Collections.binarySearch(childrens, currentPath.substring(7));
			beforePath = PATH2 + "/"  + childrens.get(wz-1);
		}
		return false;
	}

	@Override
	public void waitLock() {
		IZDataListener listener = new IZDataListener(){
			
			public void handleDataDeleted(String dataPath) throws Exception{
				if(countDownLatch != null){
					countDownLatch.countDown();
				}
			}
			
			public void handleDataChange(String dataPath, Object data) throws Exception{
				
			}
		};
		//给排在前面的结点增加数据删除的watcher，本质是启动另外一个线程去监听前置结点
		this.zkClient.subscribeDataChanges(beforePath, listener);
		
		if(this.zkClient.exists(beforePath)){
			countDownLatch = new CountDownLatch(1);
			try{
				countDownLatch.await();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.zkClient.unsubscribeDataChanges(beforePath, listener);
	}
	
	@Override
	public void unLock() {
		//删除当前临时结点
		zkClient.delete(currentPath);
		zkClient.close();
	}
	
}
