package AIO1;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
 
/**
 * �ͻ������ӳɹ�����ʧ�ܺ�Ļص�������
 */
 
public class AcceptCompletionHandler implements CompletionHandler <AsynchronousSocketChannel,AsyncTimeServerHandler>{
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        //������Ѿ����տͻ��˳ɹ��ˣ�Ϊʲô��Ҫ����accept��������Ϊһ��channel���Խ��ճ�ǧ������ͻ���
        //������asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler())�����������µ�
        //�ͻ������ӽ��룬������Ҫ������������accept���������������ͻ��˵Ľ��룬�����γ�һ��ѭ��
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //�첽�������������Ķ��壺��һ�����������ջ������������첽��channel��ȡ���ݰ���
        //�ڶ����������첽channelЯ���ĸ�����֪ͨ�ص���ʱ����Ϊ��β�������������ΪReadCompletionHandler�����
        //֪ͨ�ص���ҵ��handler��Ҳ�������ݴ�channel����ByteBuffer��ɺ�Ļص�handler��������ReadCompletionHandler
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }
 
    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        AsyncTimeServerHandler result = (AsyncTimeServerHandler) attachment;
        result.latch.countDown();
    }
}

