package strategy2;
import strategy2.Comparable;

	/*�������д�����࣬�����ôд�أ�   ��д�����Ŀ��
	 * */
public class Strategy_test {
	public static void main(String[] args) {
		//վ��ʹ���ߵĽǶȿ���
		//int a[] = {2,5,3,8,7,10,6} ;
		
		Cat c[] = {new Cat(5), new Cat(4), new Cat(3), new Cat(9),new Cat(6)} ;
		MySort.sort(c) ;
		MySort.print(c) ;
	}
	
}


class MySort{
	
	//����ֻ��ֱ���޸������һЩ���룬����ȡ��
	/* public static void sort(Cat a[]){  //ð������
		for(int i = a.length -1 ; i > 0 ; i --){
			for(int j = 0 ; j < i ; j ++){
				if(a[j].getWeight() < a[j+1].getWeight()){ //�Ƚϵ������������У���������������ݣ�����Ҫ�޸�������        
					swap(a, j, j+1) ;				//���ڸı������е�����
					//дһ��Comparable�ӿ�    ������һ��compareTo������Ȼ��Ҫ�������ʵ������ӿڲ��Ϳ���ʵ�ֶ���ıȽ���
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

class Cat implements Comparable { //ʵ��Comparable�ӿ�
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
		
	@Override
	public boolean compareTo(Comparable c) { //
		Cat cat = (Cat)c ;
		if(this.getWeight() > cat.getWeight()){
			return true ;
		}
		return false;
	}

}
/*����sort����ȷʵ����Ҫ�޸ľ�������������ˣ�ֻҪ����ʵ����Comparable�ӿ�
 * �����������Ҫ�Ƚ�ͬһ����������������ԣ�������Ҫ�޸ĸ����compareTo������ֻ��һ���������ڸ���ô���أ�
 * �����������Ϊ�����ԱȽϾ�Ҫ�޸�compareTo������ 
 * 			�ɴ���compareTo������ĺ��Ĵ�����ڽӿ�����ö�̬�����ԡ�
 * 			���������д����Ƚϲ�ͬ���Ե��࣬��Ҫ�Ƚ��ĸ����ԣ���ֻ��Ҫдһ��ʵ���˽ӿڵĵ����ԱȽ���
 * */