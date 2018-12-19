package AIO1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
 
public class AsyncTimeClientHandler implements CompletionHandler<Void,AsyncTimeClientHandler>, Runnable {
 
    private AsynchronousSocketChannel client;
 
    private String host;
 
    private int port;
 
    private CountDownLatch latch;
 
    public AsyncTimeClientHandler(String host, int port){
        this.host = host;
        this.port = port;
        try {
            //��ʼ��һ��AsynchronousSocketChannel
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
 
 
    @Override
    public void run() {
     latch = new CountDownLatch(1);
     //���ӷ���ˣ�����������Ϊ���ӳɹ�ʱ�Ļص�handler
     client.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
 
    /**
     * ���ӷ���˳ɹ�ʱ�Ļص�
     * @param result
     * @param attachment
     */
    @Override
    public void completed(Void   result, AsyncTimeClientHandler attachment) {
        //�������
      byte [] req = "query time order".getBytes();
      //����д������
      ByteBuffer write = ByteBuffer.allocate(req.length);
      //��д������д����body
      write.put(req);
      write.flip();
      //�������е�����д��channel��ͬʱʹ�������ڲ�������ɺ�ص�
     client.write(write, write, new CompletionHandler<Integer, ByteBuffer>() {
         @Override
         public void completed(Integer result, ByteBuffer byteBuffer) {
             //������������л������ݣ�����д
              if(byteBuffer.hasRemaining()){
                  client.write(byteBuffer, byteBuffer, this);
              }else{
                  ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                  //��ȡ����˵ķ��ص����棬���������ڲ�����д�껺���Ļص�handler
                  client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                      /**
                       * �ӻ����ж�ȡ���ݣ���ҵ����
                       * @param result
                       * @param buffer
                       */
                      @Override
                      public  void completed(Integer result, ByteBuffer buffer) {
                          buffer.flip();
                         byte [] bytes = new byte[buffer.remaining()];
                         buffer.get(bytes);
                          String body;
                          try {
                               body =  new String(bytes, "UTF-8");
                              System.out.println("now body is:" + body);
                              latch.countDown();
                          } catch (UnsupportedEncodingException e) {
                              e.printStackTrace();
                          }
                      }
 
 
                      /**
                       * �ӻ����ȡ����ʧ��
                       * �ر�client���ͷ�channel�������һ����Դ
                       * @param exc
                       * @param attachment
                       */
                      @Override
                      public void failed(Throwable exc, ByteBuffer attachment) {
                          try {
                              client.close();
                              latch.countDown();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }
                  });
              }
 
         }
 
         /**
          * ����д��channelʧ��
          * �ر�client���ͷ�channel�������һ����Դ
          * @param exc
          * @param attachment
          */
         @Override
         public void failed(Throwable exc, ByteBuffer attachment) {
             {
                 try {
                     client.close();
                     latch.countDown();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
     });
    }
 
    /**
     * ���ӷ����ʧ��
     * @param exc
     * @param attachment
     */
 
    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        {
            try {
                client.close();
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

