package strategy1;


	/*�������дһ�������࣬�����ôд�أ�   ��д�����Ŀ��
	 * */
public class Strategy_test {
	public static void main(String[] args) {
		//վ��ʹ���ߵĽǶȿ���
		int a[] = {2,5,3,8,7,10,6} ;
		MySort.sort(a) ;
		MySort.print(a) ;
	}
	
}

class MySort {
	public static void sort(int a[]){  //ð������
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

/*��1������ȷʵ�ܹ����򣬵���д���ˣ�ֻ����int���͵����ݣ�
��2���������Ҫ��float���͵��أ�  �ţ�����ֻ��Ҫ���ط�����public static void sort(float a[]) ����Ҳ�ܽ��
��3����������Ҫ���  ���������أ�   
�� ���� Cat[] cats = {cat����1,cat����2,cat����3,cat����4}��Cat���кܶ����ԣ������أ��峤�ȣ��������cat����������
*/
