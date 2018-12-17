package demo3;

abstract class CallBack {
	boolean flag = false;
	abstract void doExcute();
	
	public boolean isDone(){
		return getFlag();
	}
	
	public void Finished(){
		setFlag(true);
	}
	protected boolean getFlag() {
		return flag;
	}
	protected void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
