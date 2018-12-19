package strategy1;


	/*如果你想写一个排序类，你会怎么写呢？   先写基本的框架
	 * */
public class Strategy_test {
	public static void main(String[] args) {
		//站在使用者的角度考虑
		int a[] = {2,5,3,8,7,10,6} ;
		MySort.sort(a) ;
		MySort.print(a) ;
	}
	
}

class MySort {
	public static void sort(int a[]){  //冒泡排序
		for(int i = a.length -1 ; i > 0 ; i --){
			for(int j = 0 ; j < i ; j ++){
				if(a[j] < a[j+1]){
					swap(a, j, j+1) ;
				}
			}
		}
	}
	private static void swap(int a[], int x, int y){
		int temp = a[x] ;
		a[x] = a[y] ;
		a[y] = temp ;
	}
	public static void print(int[] a){
		for(int i = 0 ; i < a.length ; i ++){
			System.out.print(a[i] + " ");
		}
	}
}

/*（1）这样确实能够排序，但是写死了，只能排int类型的数据，
（2）现在如果要排float类型的呢？  嗯，可能只需要重载方法，public static void sort(float a[]) 好像也能解决
（3）但是现在要想对  对象排序呢？   
如 排序 Cat[] cats = {cat对象1,cat对象2,cat对象3,cat对象4}，Cat类有很多属性，如体重，体长等，现想根据cat的体重排序？
*/
