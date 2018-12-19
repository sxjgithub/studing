package java_io_based;
/*�Լ����ַ�װһ����ӡ��
 * 	out =  new PrintWrite_My(new FileOutputStream(file)) ;  
 *  out.prinln(��ӡ������)   ������ݵ��ļ���ȥ
 * */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
public class Print_My{
	public static void main(String[] args) throws Exception {
		File file = new File("D:" + File.separator + "s.txt") ;
		if(!file.getParentFile().exists()){ //�ж��ļ�·��ʱ�����
			file.getParentFile().mkdirs() ;
		}
		if(!file.exists()){ //�ж��ļ�ʱ�����
			file.createNewFile() ;			
		}
		PrintWrite_My pw = new PrintWrite_My(new FileOutputStream(file)) ;
		pw.println(57);
		pw.close();	
	}
}
class PrintWrite_My implements AutoCloseable{
	OutputStream out ;
	public PrintWrite_My(OutputStream out) {
		this.out = out ;
	}
	private void print(String str){
		try {
			this.out.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void println(String str){
		this.print(str + "\r\n");
	}
	public void println(long num){
		this.println(String.valueOf(num));
	}
	@Override
	public void close() throws Exception {
		this.out.close();
	}
}
