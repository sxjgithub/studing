package distrubuted_lock_mysql;

import javax.annotation.Resource;

import distrubuted_lock1.*;


import distrubuted_lock1.AbstractLock;

public class MysqlLock extends AbstractLock{
	
	@Resource
	private LockMapper mapper;
	
	//所有的线程都往数据库插入主键值相同的数据
	private static final int LOCK_ID = 1;
	
	//非阻塞式加锁
	@Override
	public boolean tryLock() {
		try{
			mapper.insert(LOCK_ID); //第一个线程插入的能成功，后面线程不能插入成功
		}catch(Exception e){
			return false;
		}
		return true;
	}

	//让当前线程休眠一段时间
	@Override
	public void waitLock() {
		try{
			Thread.currentThread().sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void unLock() {
		// TODO Auto-generated method stub
		
	}
	
}
