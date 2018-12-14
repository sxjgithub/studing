package component1;
/**
 * 组合模式 用来处理树形结构，    天然的递归
 * @author sxj
 *
 */
public class Test {
	public static void main(String[] args) {
		AbstractFile text = new Text("我的记事本");
		AbstractFile image = new Image("我的帅照");
		
		System.out.println("-------------------------");
		Folder folder1 = new Folder("我的收藏夹1");
		folder1.add(text);
		folder1.add(image);
		folder1.killVirus(); //递归调用，结合多态
		
		System.out.println("-------------------------");
		Folder folder2 = new Folder("我的收藏夹");
		AbstractFile image1 = new Image("我的私密照");
		
		folder2.add(image1);
		folder2.add(folder1); //还可以加文件夹
		folder2.killVirus();
	}
	
	
}
