package AIO1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
 
public class AsyncTimeServerHandler implements Runnable{
 
    private int port;
 
     CountDownLatch latch;
 
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
 
 
    public AsyncTimeServerHandler(int port){
        //����������˿ں�
        this.port = port;
        try {
            //����һ��AsynchronousServerSocketChannel���󣬹�������
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            //�󶨶˿ں�
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("the time server is start on port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void run() {
        //��һ�����������࣬������һ���̵߳ȴ���һ���߳���ɺ���ִ��
        //������Ϊ�˷�ֹ���߳�������ɺ�ر�
     latch = new CountDownLatch(1);
     doAccept();
        try {
            //����ֱ��count down to 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
 
    public void doAccept(){
        //�÷������첽�Ľ������Ը�ͨ���Ŀͻ��˵������������ӳɹ������CompletionHandler��completed����failed����
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
