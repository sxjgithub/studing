package AIO1;


public class TimeClient {
    /**
     * 启动客户端，采用异步非阻塞模式
     * @param args
     */
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8010;
        new Thread(new AsyncTimeClientHandler(host, port), "AIO-AsyncTimeClientHandler-001").start();
    }
}
