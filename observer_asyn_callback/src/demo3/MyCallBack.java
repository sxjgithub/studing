package demo3;

import java.util.concurrent.TimeUnit;

public class MyCallBack extends CallBack{
		
		@Override
		public void doExcute() {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("哈哈，OS已经完成任务了");
		}

		
		
}
