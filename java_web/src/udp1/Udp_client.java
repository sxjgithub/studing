package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/*udp 客户端的发送步骤：1.建立udp套接字 + 客户端端口 ：
 * 					DatagramSocket udp_client = new DatagramSocket(6666) ;
 * 				  2.准备发送的数据 包装成DatagramPacket对象 （要发送的数据 + 服务器ip及port）：
 * 					DatagramPacket packet = new DatagramPacket(data, data.length,
					new InetSocketAddress("localhost", 8888)) ;
				  3.发送数据：udp_client.send(packet);
				  4.关闭套接字 ：udp_client.close();
	注：接收的数据和发送的数据都要包装成DatagramPacket对象
 * */
public class Udp_client {
	public static void main(String[] args) throws IOException {
		DatagramSocket udp_client = new DatagramSocket(6666) ;
		
		String msg = "你好啊！" ;
		byte[] data =  msg.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length,
				new InetSocketAddress("localhost", 8888)) ;
		
		
		udp_client.send(packet);
		
		udp_client.close();
	}
}
