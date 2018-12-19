package java_io_File;
import java.io.File;
/*文件的操作：File类
 * 注意点:1.File类只能作文件的创建，删除操作，不能修改文件里的内容
 * 		2.File既可以操作文件，也可以操作文件夹
 * 
 * File的基本操作:
 * 	一.创建文件
 * 		1.创建File对象： File file = new File("d:" + File.separator + "文件名");
 * 		  File.separator 是表示文件路径分隔符；如windows中是"\"
 *  	2.判断文件的路径时候正确: if(! file.getParentFile().exists() )
 *    	     如果路径不存在创建文件夹： file.getParentFile().mkdirs();
 *  	3.创建文件：file.createNewFile();
 *  二：创建文件夹：
 *  	File file = new File("d:" + File.separator + "文件夹名称");
 * 		file.mkdirs()
 * 
 * File类里的方法： 所有文件，文件夹都需要包裹成File对象操作
 * 		文件是否存在：File.exists()
 *  	文件删除：File.delete()
 *  	路径分隔符：File.separator
 *  	文件的父路径: File.getParentFile()  
 *  		//返回值File，如F:\1\2.txt 的父路径为F:\1\
 *  	文件是否可读: File.canRead()
 *  	文件是否可写：  File.canWrite()
 *  	文件大小: File.length()
 *  	最后的修改时间: File.lastModified()
 *  	是目录吗: File.isDirectory()
 *  	是文件吗: File.isFile()
 *------列出目录中的全部内容: File.listFiles()
 *------重命名：File.renameTo(File new_file)
 * */
public class file_op {
	public static void main(String[] args) throws Exception{
			
		//创建File对象
		File file = new File("d:" + File.separator + "java_File.txt");
		
		if(!file.getParentFile().exists()){ //判断父路径是否存在
			file.getParentFile().mkdirs(); //创建父路径文件夹
		}
		if(file.exists()){
			//file.delete();
		}else{
			file.createNewFile(); //创建文件
			//返回值：true、flase
		}
		File re_file = new File("d:" + File.separator + "java_ff.txt");
		file.renameTo(re_file); //文件的重命名
		
	}
}