package demo1;

import java.util.concurrent.TimeUnit;

public class MyCallBack extends CallBack{
		
		@Override
		public void doExcute(int x, int y) {
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("½á¹ûÎª:" + (x + y));	
		}
	
}
