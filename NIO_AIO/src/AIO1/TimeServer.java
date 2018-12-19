package AIO1;

public class TimeServer {
	 
	 
    /**
     * 启动服务端，采用异步非阻塞模式
     * @param args
     */
    public static void main(String[] args) {
        int port = 8010;
        new Thread(new AsyncTimeServerHandler(8010), "AIO-AsyncTimeServerHandler-001").start();
    }
}
