package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/*udp 服务器接收步骤：1.建立udp套接字 + 客户端端口 ：
 * 					DatagramSocket udp_server = new DatagramSocket(8888);
 * 				  2.准备接收的数据 包装成DatagramPacket对象 + 接收数组：
 * 					DatagramPacket packet = new DatagramPacket(msg, msg.length) ;
				  3.接收packet数据包：udp_client.receive(packet);
				  4.解析数据：byte[] data = packet.getData()
				    .......
				  5.关闭套接字 ：udp_client.close();
	注：接收的数据和发送的数据都要包装成DatagramPacket对象
 * */
public class Udp_server {
	public static void main(String[] args) throws IOException {
		DatagramSocket udp_server = new DatagramSocket(8888);
		
		byte[] container= new byte[1024] ;
		DatagramPacket packet = new DatagramPacket(container, container.length) ;
		udp_server.receive(packet); //堵塞等待数据
		
		byte[] data = packet.getData() ;
		int len = packet.getLength() ;
		
		System.out.println("data: " +new String(data,0,len)) ;
		
		udp_server.close();
	}
}
