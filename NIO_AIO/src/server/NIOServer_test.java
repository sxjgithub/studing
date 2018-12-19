package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer_test
{
    private static final int BUF_SIZE=1024;
    private static final int PORT = 7777;
    private static final int TIMEOUT = 3000;
    public static void main(String[] args)
    {
        selector();
    }
    public static void handleAccept(SelectionKey key) throws IOException{
    	System.out.println("进入accept");//-------------------------------------------
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocateDirect(BUF_SIZE));
    }
    public static void handleRead(SelectionKey key) throws IOException{
    	System.out.println("进入read");//-------------------------------------------
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = (ByteBuffer)key.attachment();
        int bytesRead = sc.read(buf);
        
        while(bytesRead>0){
        	//System.out.println("长度："+ bytesRead);
            buf.flip();
            //网上做法-------------------------------
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            //自己做法(为什么错了？)------------------------------------------------------------------------------
          // System.out.print(new String(buf.array(), 0, bytesRead));
            //-----------------------------
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }
    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }
    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try{
            selector = Selector.open();
            ssc= ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT); //把ServerSocketChannel 注册到复选器selector中
            while(true){
                if(selector.select(TIMEOUT) == 0){ //TIMEOUT=3000，3秒检查一次复选器，如果里面没有数据，继续3秒检查一次
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();//复选器里有数据
                
                while(iter.hasNext()){//判断是哪种数据
                	System.out.println("判断是哪种数据");//-------------------------------------------
                    SelectionKey key = iter.next();
                    if(key.isAcceptable()){
                    	System.out.println("shi accpet");//-------------------------------------------
                        handleAccept(key);
                    }
                    if(key.isReadable()){
                    	System.out.println("shi read");//-------------------------------------------
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
