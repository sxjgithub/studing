package qiuck_sort;

public class QuickSort_test {
	public static void main(String[] args) {
		int a[] = {12,56,34,78,22,5,36,9,12,7,45,243,34,67,24,89,54} ;
		quick_sort(a, 0, a.length-1);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void quick_sort(int a[], int x, int y){
		if(x>=y){
			return ;
		}
		int i = one_sort(a, x, y);
		quick_sort(a, x, i-1);
		quick_sort(a, i+1, y);
		
	}
	
	public static int one_sort(int a[], int x,int y){
		boolean flag = false ;
		int temp = a[x] ;
		while(x<y){
			if(flag == false && a[y] < temp){
				a[x] = a[y] ;
				flag = true;
			}
			if(flag == true && a[x] > temp){
				a[y] = a[x] ;
				flag = false;
			}
			if(flag == true){
				x++ ;
			}else{
				y-- ;
			}
		}
		a[x] = temp ;
		return x ;
	}
}


