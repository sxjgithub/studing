package AIO1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
 
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
 
    private AsynchronousSocketChannel channel;
 
    public ReadCompletionHandler(AsynchronousSocketChannel channel){
        if(this.channel == null)
            this.channel = channel;
    }
 
    /**
     * ҵ������ByteBuffer��ȡҵ�����ݣ���ҵ�����
     * @param result
     * @param attachment
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
     attachment.flip();
     byte [] body = new byte[attachment.remaining()];
     attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("the time server receive order:" + req );
            String currentTime = "query time order".equals(req) ? new Date(System.currentTimeMillis()).toString(): "bad order";
            dowrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * ���ͻ��˵�д����
     * @param currentTime
     */
 
    public  void dowrite(String currentTime){
        if(currentTime != null && currentTime.trim().length() > 0){
            byte[] bytes = currentTime.getBytes();
            //����һ��д����
            ByteBuffer write = ByteBuffer.allocate(bytes.length);
            System.out.println("reponsbody=" + currentTime);
            //����������д�뻺��
            write.put(bytes);
            write.flip();
            //������д��channel
            channel.write(write, write, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    //������ֻ�������ûд�꣬����д
                    if(buffer.hasRemaining()) {
                        channel.write(buffer, buffer, this);
                    }
                }
 
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        //дʧ�ܣ��ر�channel�����ͷ���channel�������һ����Դ
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
 
    }
 
    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            //�����ر�channel�����ͷ���channel�������һ����Դ
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

