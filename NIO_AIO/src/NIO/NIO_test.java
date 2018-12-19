package NIO;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author sxj
 *
 */
public class NIO_test {
	public static void main(String[] args) {
		nIO_file_method();
	}
	
	public static void nIO_file_method(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("src/io.txt","rw");
            
            FileChannel fileChannel = aFile.getChannel(); //获取文件的通道
            
            ByteBuffer buf = ByteBuffer.allocate(1024); // 创建接收文件通道内容的buffer,指定容量
            int bytesRead = fileChannel.read(buf);  
            
            //自己的做法--------------------------------------------------------------------
            System.out.println(bytesRead);
            buf.flip();
            System.out.println(new String(buf.array(), 0, bytesRead));
            buf.compact();
           
            //网上做法---------------------------------------------------------------------
            /*while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }*/
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
	/**
	 * 采用FileInputStream读取文件内容
	 */
	public static void inputStreamMethod(){
		InputStream in = null;
		//String lString;
		//new String(bytes, charset)
	    try{
	        in = new BufferedInputStream(new FileInputStream("src/io.txt"));
	        byte [] buf = new byte[1024];
	        int bytesRead = in.read(buf);
	        System.out.println(new String(buf, 0, bytesRead));
	        
	        /*while(bytesRead != -1)
	        	
	        {	System.out.println(bytesRead);
	            for(int i=0;i<bytesRead;i++)
	                System.out.print((char)buf[i]);
	            bytesRead = in.read(buf);
	        }*/
	    }catch (IOException e)
	    {
	        e.printStackTrace();
	    }finally{
	        try{
	            if(in != null){
	                in.close();
	            }
	        }catch (IOException e){
	            e.printStackTrace();
	        }
	    }
	}
}
