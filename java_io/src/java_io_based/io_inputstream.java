package java_io_based;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class io_inputstream {
	public static void main(String[] args) throws Exception {
		File file = new File("d:" + File.separator + "mldn.txt"); // 定义操作文件
		PrintWriter pu = new PrintWriter(new FileOutputStream(file)) ;
		pu.println("姓名：小强子");
		pu.print("年龄：");
		pu.println(78);
		pu.close();
	}
	
}


