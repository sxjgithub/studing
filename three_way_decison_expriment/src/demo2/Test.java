package demo2;

import util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo1.ThresholdIndex;

public class Test {
	public static void main(String[] args) {
		/*TWD twd = new TWD(数字 数组);
		twd.setPos(正域个数);
		结果(正域数组) = TWD.getPos();*/	
		
		//TWD t = new TWD(new float[]{61,60,57,50,40,18,15,13,12});
		//TWD t = new TWD(new float[]{92,92,91,90,88,87,86,85,83,82});
		//TWD t = new TWD(new float[]{5,6,8,9,12});
		/*float[] optimalDeviation = t.calc_optimalDeviation();
		for(float r : optimalDeviation){
			System.out.print(r + "、 ");
		}
		System.out.println( " ");*/
		
	
		//计算a3  的z3, 我的结果是0.4714045207910317， 和论文（0.577350269）上有出入，
		/*
		double average3 = ((double)92.0 + (double)92.0 + (double)91.0)/3.0;
		double s3= ((double)92.0 - average3)*((double)92.0 - average3)*2 + ((double)91.0 - average3)*((double)91.0 - average3);
		double result3 = Math.sqrt(s3/3.0);
		System.out.print(result3);*/
		
		//float[] f = new float[]{1,2,3,4,5,6};
		float[] f1 = new float[]{92, 92, 91, 90, 88, 87, 86, 85, 83, 82, 81, 79, 77, 75, 71, 71,
				69, 66, 65, 65, 64, 63, 62, 61, 60, 59, 57, 57, 56, 56};
			
		float[] f = new float[f1.length]; //逆序
		for(int i =0, j = f1.length-1; i < f1.length; i++,j--){
			f[i] = f1[j];
		}
		DataInfo[] dataInfos = new DataInfo[f.length];
		for(int i = 0; i < f.length; i++){
			dataInfos[i] = new DataInfo(f[i], i);
		}
		
		//第一次划分
		TWD t1 = new TWD(dataInfos);
		
		float[] sub_gnnis = t1.get_sub_gnnis(dataInfos);
		for(int i = 0; i < sub_gnnis.length; i++){
			System.out.print(sub_gnnis[i]*100 + "、   ");
		}
		System.out.println();
		
		PosNegBndInfos pnb1 = t1.get_once_result(dataInfos, 10);//一次分类结果
		System.out.print("第一次划分结果：\n");
		System.out.print(pnb1);
		
		//第二次划分
		DataInfo[] bndInfo = pnb1.getBndInfo(); //得到边界域，继续第二次划分
		float[] data2 = new float[]{66,66,66,68,68,69,71,71,73,75,76,78,79,81,84,84,85,85,87,88};
		for(int i = 0; i < data2.length; i++){
			bndInfo[i].setValue(data2[i]);
		}
		//排序
		
		
		TWD t2 = new TWD(bndInfo);
		PosNegBndInfos pnb2 = t1.get_once_result(bndInfo, 4);//二次分类结果
		System.out.print("第二次划分结果：\n");
		System.out.print(pnb2);
		//float[] all_gnni = t.clac_all_gnni();  //gnni数组
		//float[] s = t.clac_sub_gnni(all_gnni); //gnni 差值数组
		
		DataInfo[] bndInfo1 = pnb2.getBndInfo(); //得到边界域，继续第二次划分
		TWD t3 = new TWD(bndInfo1);
		PosNegBndInfos pnb3 = t3.get_once_result(bndInfo1, 1);//二次分类结果
		System.out.print("第三次划分结果：\n");
		System.out.print(pnb3);
	}
}

class TWD{
	
	DataInfo[] objArray ; //要分类的对象列表  
	int posLength;  //正域个数
	List<DataInfo> posList; //正域数组
	List<DataInfo> bndList;  //边界域数组
	List<DataInfo> negList;  //负域数组
	int updateTimes; //更新次数
	

	public TWD(DataInfo[] objArray) {
		this.objArray = Arrays.copyOf(objArray, objArray.length) ;
		
	}
	
	
	
	/**
	 * 设定正域的个数
	 * @param len 设定正域的个数
	 */
	public void setPosLength(int len, int times){ 
		this.posLength = len;
		this.updateTimes = times;
	}
	
	/**
	 * 获取一次分类的正域数组
	 * @param nums 要分类的数组
	 * @param indexArray 分到正域的数据的下标
	 * @return 正域数组
	 */
	/*public DataInfo[] getPosObjs(float[] nums){ //设计不合理----改正1------------------------------------------------------------
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//得到正域数组
		DataInfo[] posArray = new DataInfo[ nums.length - thresholdIndex.getSecondMax()]; //正域数组个数
		System.out.println(posArray.length); //---------------------------------------------
		
		copy(nums, thresholdIndex.getSecondMax(),nums.length - thresholdIndex.getSecondMax(),posArray);
			
		return posArray;
	}*/
	
	public DataInfo[] getPosObjs(DataInfo[] nums, ThresholdIndex thresholdIndex){ //设计不合理----改正1------------------------------------------------------------
		
		DataInfo[] posArray = new DataInfo[ nums.length - thresholdIndex.getSecondMax()]; //正域数组个数
		System.out.println(posArray.length); //---------------------------------------------
		
		//copy(nums, thresholdIndex.getSecondMax(),nums.length - thresholdIndex.getSecondMax(),posArray);
		System.arraycopy(nums, thresholdIndex.getSecondMax(), posArray, 0, nums.length - thresholdIndex.getSecondMax());
			
		return posArray;
	}
	/**
	 * 把数组nums中从index下标开始复制length个数到dataInfos数组中，dataInfo存储的是数组内容和下标
	 * @param nums
	 * @param index
	 * @param length
	 * @param dataInfos
	 */
	public void copy(float[] nums, int index,int length,DataInfo[] dataInfos){
		int count = 0;
		for(int i = index; i  < index +length; i++){
			dataInfos[count++] = new DataInfo(nums[i], i); 
		}
	}
	/**
	 * 获取一次分类的负域数组
	 * @param nums 要分类的数组
	 * @param indexArray 分到负域的数据的下标
	 * @return 负域数组
	 */
/*	public DataInfo[] getNegObjs(float[] nums){
		
				
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//得到负域数组
		DataInfo[] negArray = new DataInfo[thresholdIndex.getFirstmax()]; //负域数组个数
		System.out.println(negArray.length); //---------------------------------------------
		
		copy(nums, 0, thresholdIndex.getFirstmax(), negArray);
			
		return negArray;
	}*/
	
	public DataInfo[] getNegObjs(DataInfo[] nums,ThresholdIndex thresholdIndex){
		//得到负域数组
		DataInfo[] negArray = new DataInfo[thresholdIndex.getFirstmax()]; //负域数组个数
		System.out.println(negArray.length); //---------------------------------------------
		
		//copy(nums, 0, thresholdIndex.getFirstmax(), negArray);
		System.arraycopy(nums, 0, negArray, 0, thresholdIndex.getFirstmax());	
		return negArray;
	}

	/**
	 * 得到数组nums的最大和次大gnnis差的索引，放在ThresholdIndex对象中
	 * @param nums
	 * @return
	 */
	public ThresholdIndex getMaxAndNext(DataInfo[] nums){
		TWD t = new TWD(nums);
	
		float[] all_gnni_local = t.clac_all_gnni();  //gnni数组
		
		
		//获取所有的gnni差 数组
		float[] sub_gnnis = clac_sub_gnni(all_gnni_local); 
				
		//获得最大和次大的gnni差数组的下标,作为阈值
		return ArrayUtil.find_maxAndNext(sub_gnnis);
	}
	
	/**
	 * 获得nums数组的的gnni差数组
	 * @param nums 
	 * @return
	 */
	public float[] get_sub_gnnis(DataInfo[] nums){
		TWD t = new TWD(nums);
	
		float[] all_gnni_local = t.clac_all_gnni();  //gnni数组
		
		
		//获取所有的gnni差 数组
		float[] sub_gnnis = clac_sub_gnni(all_gnni_local); 
				
		//获得最大和次大的gnni差数组的下标,作为阈值
		return sub_gnnis;
	}
	/**
	 * 获取一次分类的负域数组
	 * @param nums 要分类的数组
	 * @param indexArray 分到负域的数据的下标
	 * @return 负域数组
	 */
	/*public DataInfo[] getBndObjs(float[] nums){ 
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//得到边界域数组
		DataInfo[] bndArray = new DataInfo[thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax()]; //边界域数组个数
		System.out.println(bndArray.length); //---------------------------------------------
		
		copy(nums, thresholdIndex.getFirstmax(), 
				thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax(), bndArray);
			
		return bndArray;
	}*/
	
	public void dataInfo_to_float(DataInfo[] dataInfos, float[] arrays){
		for(int i = 0; i < dataInfos.length; i++){
			arrays[i] = dataInfos[i].getValue();
		}
	}
	
	public DataInfo[] getBndObjs(DataInfo[] nums, ThresholdIndex thresholdIndex){ 
		//得到边界域数组
		DataInfo[] bndArray = new DataInfo[thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax()]; //边界域数组个数
		System.out.println(bndArray.length); //---------------------------------------------
		
		//copy(nums, thresholdIndex.getFirstmax(), thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax(), bndArray);
		System.arraycopy(nums, thresholdIndex.getFirstmax(), bndArray, 0, thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax());	
		return bndArray;
	}
	
	/**
	 * 根据设定的正域个数，和分类次数，得到正域数组 (暂时只设置为3，5次，后续用递归解决次数问题,需要重构代码)
	 * @param len 设定的正域个数
	 * @param times 分类次数
	 */
	public PosNegBndInfos  get_once_result(DataInfo[] objArray, int len){
		DataInfo[] arrays = objArray;
		int posLen = len;
			ThresholdIndex thresholdIndex = getMaxAndNext(arrays);
			if(PosNumber(arrays, thresholdIndex) >= posLen){
				thresholdIndex.setSecondMax(arrays.length-1 -(posLen/2));
			}
			DataInfo[] posInfos = getPosObjs(arrays, thresholdIndex);
			
			//得到边界域数组
			DataInfo[] bndArray = getBndObjs(arrays, thresholdIndex);		
			DataInfo[] negArray = getNegObjs(arrays, thresholdIndex);
			
			return new PosNegBndInfos(posInfos, negArray, bndArray);
	}
	
	public int PosNumber(DataInfo[] nums, ThresholdIndex thresholdIndex){
		return nums.length - thresholdIndex.getSecondMax();
	}
	/**
	 * 计算数组的平均值
	 * @param nums 计算的数组
	 * @param len 数组中要计算的平均值的对象的个数
	 * @return 数组的平均值
	 */
	
	public float calc_average(float[] nums, int len){
		float sum = 0;
		
		for(int i = 0; i < len; i ++){
			sum +=nums[i];	
		}
		return sum/len;
	}
	/**
	 * 计算数组的平方根
	 * @param nums 计算的数组
	 * @param len 数组中要计算的平方根的对象的个数
	 * @return 数组的平方根
	 */
	public float calc_square(float[] nums,int len){
		float result;
		float sum = 0;
		float average = calc_average(nums, len);
		for(int i = 0; i < len; i ++){
			sum +=(nums[i] - average)*(nums[i] - average);
		}
		result = (float) Math.sqrt(sum/len);
		
		return result;
		
	}
	
	/**
	 * 计算对象的最佳偏差
	 * @return 对象最佳偏差 数组
	 */
	/*public float[] calc_optimalDeviation(){
		
		float[] resultArray = new float[objArray.length];
		resultArray[0] = 0;
		for(int i = 1; i < objArray.length; i++){
			float r1 = calc_square(objArray, i+1);
			float r2 = calc_square(objArray, i);
			resultArray[i] = r1 - r2;
		}
		return resultArray;
	}*/
	
	
	
	/**
	 * 计算单个对象所占的权重
	 * @param nums
	 * @param len
	 * @param x
	 * @return
	 */
	public static float calc_precent(DataInfo nums[],int len, float x){
		float sum = 0;
		for(int i = 0; i < len; i++){
			sum += nums[i].getValue();
		}
		return x/sum;
			
	}
	
	/**
	 * 计算累加的权重， 比如：W3=w1+w2+w3;
	 * @param nums
	 * @param len
	 * @return
	 */
	public static float calc_weight(DataInfo nums[],int len,int k){ //k表示从下标0加到哪个位置
		float sum = 0;
		for(int i = 0; i < k+1; i++){ //i<len-1:表示最后一个元素不参与计算
			sum += calc_precent(nums, len, nums[i].getValue());
		}
		return sum;
	}
	
	/**
	 * 计算单个数组的基尼系数
	 * @param nums
	 * @param len
	 * @return
	 */
	public static float clac_obj_gnni(DataInfo nums[], int len){
		float sum = 0;
		if(len == 1){
			return 0;
		}else{
			for(int i = 0; i < len; i++){//最后一个累计权重不参与计算
				sum += calc_weight(nums, len, i-1); //最后一个累计权重不参与计算，所以i-1
			}
			return 1- (2*sum+1)/len;
		}
	}
	
	public float[] clac_all_gnni(){
		float result[] = new float[objArray.length];
		result[0] = 0; //只有一个元素的数组gnni是0
		for(int i = 1; i < objArray.length; i ++){
			result[i] = clac_obj_gnni(objArray, i+1);
		}
		return result;
	}
	
	public float[] clac_sub_gnni(float[] nums){
		float[] result = new float[nums.length];
		result[0] = 0;
		for(int i = 1; i < nums.length; i++){
			result[i] = nums[i] - nums[i-1];
		}
		return result;
	}
}

	
