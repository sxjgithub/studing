package util;

import demo1.ThresholdIndex;

public class ArrayUtil {
	
	/**
	 * ��ӡ���飬�ԡ��±�|���ݡ� ����ʽ, ÿ5��������Ϊһ��
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
	 * ͬʱ��ӡ�������� ,Ҫ����Ҫ������length���
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
	 * ����һ��ֻ���������ݵ����飬��һ�����������������ֵ���±꣬�ڶ����������еڶ���ֵ���±�
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
