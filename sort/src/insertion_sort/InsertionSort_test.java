package insertion_sort;
//��������
public class InsertionSort_test {
	public static void main(String[] args) {
		int a[] = {3,7,2,9,4,11,22,35,26,15} ;
		
		for(int i = 1; i < a.length; i++){
			for(int j = 0; j < i ; j++){
				if(a[i] < a[j]){ //�ҵ�Ҫ�����λ����
					int temp = a[i] ;
					for(int k = i; k > j; k--){ //����
						a[k] = a[k-1] ;
					}
					a[j] = temp ;
					break ;
				}
			}
		}
		
		for(int i = 0; i<a.length ; i ++){
			System.out.print(i + "|" + a[i] + " ") ;
		}
		System.out.println("\n" + a.length);
	}
	
}
