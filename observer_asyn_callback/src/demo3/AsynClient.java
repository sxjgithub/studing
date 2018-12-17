package demo3;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 异步客户端 ：可以异步同时处理多个任务
 * @author sxj
 *
 */
public class AsynClient {
			static OS os = new OS();
			static ExecutorService pool = Executors.newCachedThreadPool();

	public void asyDoOthers() {
		System.out.println("哈哈，我不用等了，异步干其他事情");
		
	}
	
	
	//(不好实现,最好编写具体的任务处理方法)问题1：不同参数怎么传，怎么设计一个统一的DispatherTask2OS()方法-----把参数也封装下
	//(Callback 里面设置flag标记)问题2：怎么获得OS的处理状态，处理数据的状态
	//()问题2：OS怎么同时处理多个任务
	public void DispatherTask2OS(CallBack callBack) {
		//OS怎样在这个方法 接收并处理回调函数	
		os.register(callBack);  //注册任务(回调函数)
		//new Thread(os).start(); //让OS执行任务队列里的任务
		pool.execute(os);
	}
	
	public void close(){
		pool.shutdown();
	}
	
}
