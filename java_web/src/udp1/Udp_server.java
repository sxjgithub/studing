package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/*udp ���������ղ��裺1.����udp�׽��� + �ͻ��˶˿� ��
 * 					DatagramSocket udp_server = new DatagramSocket(8888);
 * 				  2.׼�����յ����� ��װ��DatagramPacket���� + �������飺
 * 					DatagramPacket packet = new DatagramPacket(msg, msg.length) ;
				  3.����packet���ݰ���udp_client.receive(packet);
				  4.�������ݣ�byte[] data = packet.getData()
				    .......
				  5.�ر��׽��� ��udp_client.close();
	ע�����յ����ݺͷ��͵����ݶ�Ҫ��װ��DatagramPacket����
 * */
public class Udp_server {
	public static void main(String[] args) throws IOException {
		DatagramSocket udp_server = new DatagramSocket(8888);
		
		byte[] container= new byte[1024] ;
		DatagramPacket packet = new DatagramPacket(container, container.length) ;
		udp_server.receive(packet); //�����ȴ�����
		
		byte[] data = packet.getData() ;
		int len = packet.getLength() ;
		
		System.out.println("data: " +new String(data,0,len)) ;
		
		udp_server.close();
	}
}
