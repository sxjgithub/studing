package demo1;

abstract class CallBack {
	boolean flag = false;
	
	
	public boolean getFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	abstract void doExcute(int x, int y);
}
