package AIO1;

public class TimeServer {
	 
	 
    /**
     * ��������ˣ������첽������ģʽ
     * @param args
     */
    public static void main(String[] args) {
        int port = 8010;
        new Thread(new AsyncTimeServerHandler(8010), "AIO-AsyncTimeServerHandler-001").start();
    }
}
