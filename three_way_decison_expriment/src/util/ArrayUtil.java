package util;

import demo1.ThresholdIndex;

public class ArrayUtil {
	
	/**
	 * 打印数组，以“下标|内容” 的形式, 每5个数据作为一行
	 * @param nums
	 */
	public static void print(float nums[],PrintFormat pf){
		
		for(int i = 0; i < nums.length; i++){
			/*System.out.print(i+1 + "|" + nums[i] + "  ");
			if(0 == i % 5 ){
				System.out.println("");
			}*/
			pf.format(0, 0, nums[i]);
		}
	}
	
	/**
	 * 同时打印两个数组 ,要求需要两数组length相等
	 * @param nums1
	 * @param nums2
	 */
	public static void print(float[] nums1, float[] nums2,PrintFormat pf){
		for(int i = 0; i < nums1.length; i++){
			/*System.out.print(i+1 + "|" + nums[i] + "  ");
			if(0 == i % 5 ){
				System.out.println("");
			}*/
			pf.format(i, nums1[i], nums2[i]);
			if(0 == i % 5 && i != 0){
				System.out.println("");
			}
		}
	}

	/**
	 * 返回一个只有两个数据的数组，第一个数据是数组中最大值的下标，第二个是数组中第二大值的下标
	 * @param nums
	 * @return
	 */
	public static ThresholdIndex find_maxAndNext(float[] nums){
		int max = 0;
		int second = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[max] < nums[i]){
				max = i;
			}
		}
		
		for(int i = 0; i < nums.length; i++){
			if(nums[second]< nums[i] && i != max){
				second = i;
			}
		}
		return new ThresholdIndex(max, second);
	}
	
}
