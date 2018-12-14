package component1;

import java.util.ArrayList;
import java.util.List;


/**
 * 模拟扫描磁盘杀毒
 * @author sxj
 *
 */
//抽象构建
public interface AbstractFile {
	void killVirus(); //杀毒
}

//容器文件
class Folder implements AbstractFile{
	String name;
	List<AbstractFile> fileList = new ArrayList<AbstractFile>();
	
	public Folder(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file){
		fileList.add(file);
	}
	public void remove(AbstractFile file){
		fileList.remove(file);
	}
	public AbstractFile getChild(int index){
		return fileList.get(index);
	}
	
	@Override
	public void killVirus() {
		System.out.println("---文件夹:" + name + ",进行查杀");
		
		for(AbstractFile file : fileList){ //主要是这一步，
			file.killVirus();  //组合模式的有点体现在这------天然的递归
		}
	}
	
}


class Image implements AbstractFile {
	private String name;
	
	public Image(String name) {
		super();
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("---图像文件:" + name + ",进行查杀！");
	}
	
}

class Text implements AbstractFile {
	private String name;
	
	public Text(String name) {
		super();
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("---文本文件:" + name + ",进行查杀！");
	}
	
}
