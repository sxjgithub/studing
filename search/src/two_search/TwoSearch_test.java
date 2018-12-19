package two_search;
//二分查找
public class TwoSearch_test {
	public static void main(String[] args) {
		int a[]={1,3,6,8,15,34,68,90,222,1233} ;
		int x = 6 ;
		int i = TwoSearch.search(a,x) ;
		System.out.println(i) ;
	}
}

class TwoSearch{
	public static int search(int[] a, int x){
		int i = 0;
		int j=a.length-1 ;
		int k = TwoSearch.two_search_norecursion(a, i, j, x) ;
		return k ;
	}
	private static int two_search_recursion (int a[], int i, int j, int x){
		if(i>j){
			return -1 ;
		}
		int mid =(i+j)/2 + i ;
		if(a[mid] == x){
			return mid ;
		}else if (a[mid] > x) {
			return two_search_recursion(a, i, mid-1, x) ;
		}else {
			return two_search_recursion(a, mid+1, j, x) ;
		}
		//return -1 ;  //为什么会出错？我看其他人的算法里有这个
	}
	private static int two_search_norecursion(int a[], int i, int j, int x){
		while(i<=j){
			int mid = (i+j)/2 ;
			if(a[mid] == x){
				return mid ;
			}else if (a[mid]>x) {
				j = mid - 1 ;
			}else {
				i = mid + 1 ;
			}
		}
		return -1 ;
	}
}
