package distrubuted_lock1;

public interface Lock {
	//获取到锁的资源
	public void getLock();
	//释放锁
	public void unLock();
	
}
