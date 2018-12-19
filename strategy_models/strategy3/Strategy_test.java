package strategy3;
import strategy3.*;

	/*如果你想写排序类，你会怎么写呢？   先写基本的框架
	 * */
public class Strategy_test {
	public static void main(String[] args) {
		//站在使用者的角度考虑
		//int a[] = {2,5,3,8,7,10,6} ;
		
		Cat c[] = {new Cat(5), new Cat(4), new Cat(3), new Cat(9),new Cat(6)} ;
		MySort.sort(c) ;
		MySort.print(c) ;
	}
	
}


class MySort{
	
	//可能只是直接修改里面的一些代码，不可取，
	/* public static void sort(Cat a[]){  //冒泡排序
		for(int i = a.length -1 ; i > 0 ; i --){
			for(int j = 0 ; j < i ; j ++){
				if(a[j].getWeight() < a[j+1].getWeight()){ //比较的内容在这两行，如果排序其他内容，则需要修改则两行        
					swap(a, j, j+1) ;				//现在改变这两行的内容
					//写一个Comparable接口    里面有一个compareTo方法，然后要排序的类实现这个接口不就可以实现对象的比较吗？
				}
			}
		}
	}*/
	

	public static void sort(Comparable c[]){
		for(int i = c.length-1 ; i > 0 ; i--){
			for(int j = 0 ; j < i ; j++){
				if(c[j].compareTo(c[j+1])){
					swap(c, j, j+1);
				}
			}
		}
	}
	private static void swap(Comparable[] c, int x, int y){
		Comparable temp =c[x];
		c[x] = c[y] ;
		c[y] = temp ;
	}
	/*private static void swap(Cat a[], int x, int y){
		int temp = a[x].getWeight() ;
		a[x].setWeight(a[y].getWeight()) ;
		a[y].setWeight(temp) ;
	}*/
	
	public static void print(Comparable[] a){
		for(int i = 0 ; i < a.length ; i ++){
			System.out.print(a[i] + " ");
		}
	}
}

class Cat implements Comparable {
	Comparetor comparetor = new CatWeightComparetor() ;//以后只需要修改这里，或者在构造函数里初始化
	private int height ;
	private int weight ;
	private String name ;
	
	public Cat(int weight) {
		this.weight = weight ;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
		public String toString(){
		return this.getWeight() + " " ;
	}
		
	/*@Override
	public boolean compareTo(Comparable c) {
		Cat cat = (Cat)c ;
		if(this.getWeight() > cat.getWeight()){
			return true ;
		}
		return false;
	}*/
	@Override
	public boolean compareTo(Comparable c) {
		return comparetor.compare(this, c);//以后这里不需要修改了
	}

}
class CatWeightComparetor implements Comparetor{//实现Comparetor接口
	@Override
	public boolean compare(Comparable c1, Comparable c2) {
		Cat cat1 = (Cat)c1 ;
		Cat cat2 = (Cat)c2 ;
		if(cat1.getWeight() < cat2.getWeight()){
			return true ;
		}
		return false;
	}
	
}
/*现在sort方法确实不需要修改就能排序各种类了，只要该类实现了Comparable接口
 * 但是现在如果要比较同一个类里面的其他属性，还是需要修改该类的compareTo方法（只有一个），现在该怎么办呢？
 * 解决方法：因为换属性比较就要修改compareTo方法， 
 * 			干脆在compareTo方法里的核心代码放在Comparetor接口里，利用多态的属性。
 * 			类里面可以写多个比较不同属性的类，需要比较哪个属性，则只需要写一个实现了Comparetor接口的的属性比较类
 * */