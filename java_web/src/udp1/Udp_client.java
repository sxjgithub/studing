package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/*udp �ͻ��˵ķ��Ͳ��裺1.����udp�׽��� + �ͻ��˶˿� ��
 * 					DatagramSocket udp_client = new DatagramSocket(6666) ;
 * 				  2.׼�����͵����� ��װ��DatagramPacket���� ��Ҫ���͵����� + ������ip��port����
 * 					DatagramPacket packet = new DatagramPacket(data, data.length,
					new InetSocketAddress("localhost", 8888)) ;
				  3.�������ݣ�udp_client.send(packet);
				  4.�ر��׽��� ��udp_client.close();
	ע�����յ����ݺͷ��͵����ݶ�Ҫ��װ��DatagramPacket����
 * */
public class Udp_client {
	public static void main(String[] args) throws IOException {
		DatagramSocket udp_client = new DatagramSocket(6666) ;
		
		String msg = "��ð���" ;
		byte[] data =  msg.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length,
				new InetSocketAddress("localhost", 8888)) ;
		
		
		udp_client.send(packet);
		
		udp_client.close();
	}
}
