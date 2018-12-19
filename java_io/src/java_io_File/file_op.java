package java_io_File;
import java.io.File;
/*�ļ��Ĳ�����File��
 * ע���:1.File��ֻ�����ļ��Ĵ�����ɾ�������������޸��ļ��������
 * 		2.File�ȿ��Բ����ļ���Ҳ���Բ����ļ���
 * 
 * File�Ļ�������:
 * 	һ.�����ļ�
 * 		1.����File���� File file = new File("d:" + File.separator + "�ļ���");
 * 		  File.separator �Ǳ�ʾ�ļ�·���ָ�������windows����"\"
 *  	2.�ж��ļ���·��ʱ����ȷ: if(! file.getParentFile().exists() )
 *    	     ���·�������ڴ����ļ��У� file.getParentFile().mkdirs();
 *  	3.�����ļ���file.createNewFile();
 *  ���������ļ��У�
 *  	File file = new File("d:" + File.separator + "�ļ�������");
 * 		file.mkdirs()
 * 
 * File����ķ����� �����ļ����ļ��ж���Ҫ������File�������
 * 		�ļ��Ƿ���ڣ�File.exists()
 *  	�ļ�ɾ����File.delete()
 *  	·���ָ�����File.separator
 *  	�ļ��ĸ�·��: File.getParentFile()  
 *  		//����ֵFile����F:\1\2.txt �ĸ�·��ΪF:\1\
 *  	�ļ��Ƿ�ɶ�: File.canRead()
 *  	�ļ��Ƿ��д��  File.canWrite()
 *  	�ļ���С: File.length()
 *  	�����޸�ʱ��: File.lastModified()
 *  	��Ŀ¼��: File.isDirectory()
 *  	���ļ���: File.isFile()
 *------�г�Ŀ¼�е�ȫ������: File.listFiles()
 *------��������File.renameTo(File new_file)
 * */
public class file_op {
	public static void main(String[] args) throws Exception{
			
		//����File����
		File file = new File("d:" + File.separator + "java_File.txt");
		
		if(!file.getParentFile().exists()){ //�жϸ�·���Ƿ����
			file.getParentFile().mkdirs(); //������·���ļ���
		}
		if(file.exists()){
			//file.delete();
		}else{
			file.createNewFile(); //�����ļ�
			//����ֵ��true��flase
		}
		File re_file = new File("d:" + File.separator + "java_ff.txt");
		file.renameTo(re_file); //�ļ���������
		
	}
}