package demo2;

import util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo1.ThresholdIndex;

public class Test {
	public static void main(String[] args) {
		/*TWD twd = new TWD(���� ����);
		twd.setPos(�������);
		���(��������) = TWD.getPos();*/	
		
		//TWD t = new TWD(new float[]{61,60,57,50,40,18,15,13,12});
		//TWD t = new TWD(new float[]{92,92,91,90,88,87,86,85,83,82});
		//TWD t = new TWD(new float[]{5,6,8,9,12});
		/*float[] optimalDeviation = t.calc_optimalDeviation();
		for(float r : optimalDeviation){
			System.out.print(r + "�� ");
		}
		System.out.println( " ");*/
		
	
		//����a3  ��z3, �ҵĽ����0.4714045207910317�� �����ģ�0.577350269�����г��룬
		/*
		double average3 = ((double)92.0 + (double)92.0 + (double)91.0)/3.0;
		double s3= ((double)92.0 - average3)*((double)92.0 - average3)*2 + ((double)91.0 - average3)*((double)91.0 - average3);
		double result3 = Math.sqrt(s3/3.0);
		System.out.print(result3);*/
		
		//float[] f = new float[]{1,2,3,4,5,6};
		float[] f1 = new float[]{92, 92, 91, 90, 88, 87, 86, 85, 83, 82, 81, 79, 77, 75, 71, 71,
				69, 66, 65, 65, 64, 63, 62, 61, 60, 59, 57, 57, 56, 56};
			
		float[] f = new float[f1.length]; //����
		for(int i =0, j = f1.length-1; i < f1.length; i++,j--){
			f[i] = f1[j];
		}
		DataInfo[] dataInfos = new DataInfo[f.length];
		for(int i = 0; i < f.length; i++){
			dataInfos[i] = new DataInfo(f[i], i);
		}
		
		//��һ�λ���
		TWD t1 = new TWD(dataInfos);
		
		float[] sub_gnnis = t1.get_sub_gnnis(dataInfos);
		for(int i = 0; i < sub_gnnis.length; i++){
			System.out.print(sub_gnnis[i]*100 + "��   ");
		}
		System.out.println();
		
		PosNegBndInfos pnb1 = t1.get_once_result(dataInfos, 10);//һ�η�����
		System.out.print("��һ�λ��ֽ����\n");
		System.out.print(pnb1);
		
		//�ڶ��λ���
		DataInfo[] bndInfo = pnb1.getBndInfo(); //�õ��߽��򣬼����ڶ��λ���
		float[] data2 = new float[]{66,66,66,68,68,69,71,71,73,75,76,78,79,81,84,84,85,85,87,88};
		for(int i = 0; i < data2.length; i++){
			bndInfo[i].setValue(data2[i]);
		}
		//����
		
		
		TWD t2 = new TWD(bndInfo);
		PosNegBndInfos pnb2 = t1.get_once_result(bndInfo, 4);//���η�����
		System.out.print("�ڶ��λ��ֽ����\n");
		System.out.print(pnb2);
		//float[] all_gnni = t.clac_all_gnni();  //gnni����
		//float[] s = t.clac_sub_gnni(all_gnni); //gnni ��ֵ����
		
		DataInfo[] bndInfo1 = pnb2.getBndInfo(); //�õ��߽��򣬼����ڶ��λ���
		TWD t3 = new TWD(bndInfo1);
		PosNegBndInfos pnb3 = t3.get_once_result(bndInfo1, 1);//���η�����
		System.out.print("�����λ��ֽ����\n");
		System.out.print(pnb3);
	}
}

class TWD{
	
	DataInfo[] objArray ; //Ҫ����Ķ����б�  
	int posLength;  //�������
	List<DataInfo> posList; //��������
	List<DataInfo> bndList;  //�߽�������
	List<DataInfo> negList;  //��������
	int updateTimes; //���´���
	

	public TWD(DataInfo[] objArray) {
		this.objArray = Arrays.copyOf(objArray, objArray.length) ;
		
	}
	
	
	
	/**
	 * �趨����ĸ���
	 * @param len �趨����ĸ���
	 */
	public void setPosLength(int len, int times){ 
		this.posLength = len;
		this.updateTimes = times;
	}
	
	/**
	 * ��ȡһ�η������������
	 * @param nums Ҫ���������
	 * @param indexArray �ֵ���������ݵ��±�
	 * @return ��������
	 */
	/*public DataInfo[] getPosObjs(float[] nums){ //��Ʋ�����----����1------------------------------------------------------------
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//�õ���������
		DataInfo[] posArray = new DataInfo[ nums.length - thresholdIndex.getSecondMax()]; //�����������
		System.out.println(posArray.length); //---------------------------------------------
		
		copy(nums, thresholdIndex.getSecondMax(),nums.length - thresholdIndex.getSecondMax(),posArray);
			
		return posArray;
	}*/
	
	public DataInfo[] getPosObjs(DataInfo[] nums, ThresholdIndex thresholdIndex){ //��Ʋ�����----����1------------------------------------------------------------
		
		DataInfo[] posArray = new DataInfo[ nums.length - thresholdIndex.getSecondMax()]; //�����������
		System.out.println(posArray.length); //---------------------------------------------
		
		//copy(nums, thresholdIndex.getSecondMax(),nums.length - thresholdIndex.getSecondMax(),posArray);
		System.arraycopy(nums, thresholdIndex.getSecondMax(), posArray, 0, nums.length - thresholdIndex.getSecondMax());
			
		return posArray;
	}
	/**
	 * ������nums�д�index�±꿪ʼ����length������dataInfos�����У�dataInfo�洢�����������ݺ��±�
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
	 * ��ȡһ�η���ĸ�������
	 * @param nums Ҫ���������
	 * @param indexArray �ֵ���������ݵ��±�
	 * @return ��������
	 */
/*	public DataInfo[] getNegObjs(float[] nums){
		
				
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//�õ���������
		DataInfo[] negArray = new DataInfo[thresholdIndex.getFirstmax()]; //�����������
		System.out.println(negArray.length); //---------------------------------------------
		
		copy(nums, 0, thresholdIndex.getFirstmax(), negArray);
			
		return negArray;
	}*/
	
	public DataInfo[] getNegObjs(DataInfo[] nums,ThresholdIndex thresholdIndex){
		//�õ���������
		DataInfo[] negArray = new DataInfo[thresholdIndex.getFirstmax()]; //�����������
		System.out.println(negArray.length); //---------------------------------------------
		
		//copy(nums, 0, thresholdIndex.getFirstmax(), negArray);
		System.arraycopy(nums, 0, negArray, 0, thresholdIndex.getFirstmax());	
		return negArray;
	}

	/**
	 * �õ�����nums�����ʹδ�gnnis�������������ThresholdIndex������
	 * @param nums
	 * @return
	 */
	public ThresholdIndex getMaxAndNext(DataInfo[] nums){
		TWD t = new TWD(nums);
	
		float[] all_gnni_local = t.clac_all_gnni();  //gnni����
		
		
		//��ȡ���е�gnni�� ����
		float[] sub_gnnis = clac_sub_gnni(all_gnni_local); 
				
		//������ʹδ��gnni��������±�,��Ϊ��ֵ
		return ArrayUtil.find_maxAndNext(sub_gnnis);
	}
	
	/**
	 * ���nums����ĵ�gnni������
	 * @param nums 
	 * @return
	 */
	public float[] get_sub_gnnis(DataInfo[] nums){
		TWD t = new TWD(nums);
	
		float[] all_gnni_local = t.clac_all_gnni();  //gnni����
		
		
		//��ȡ���е�gnni�� ����
		float[] sub_gnnis = clac_sub_gnni(all_gnni_local); 
				
		//������ʹδ��gnni��������±�,��Ϊ��ֵ
		return sub_gnnis;
	}
	/**
	 * ��ȡһ�η���ĸ�������
	 * @param nums Ҫ���������
	 * @param indexArray �ֵ���������ݵ��±�
	 * @return ��������
	 */
	/*public DataInfo[] getBndObjs(float[] nums){ 
		ThresholdIndex thresholdIndex = getMaxAndNext(nums);
		//�õ��߽�������
		DataInfo[] bndArray = new DataInfo[thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax()]; //�߽����������
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
		//�õ��߽�������
		DataInfo[] bndArray = new DataInfo[thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax()]; //�߽����������
		System.out.println(bndArray.length); //---------------------------------------------
		
		//copy(nums, thresholdIndex.getFirstmax(), thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax(), bndArray);
		System.arraycopy(nums, thresholdIndex.getFirstmax(), bndArray, 0, thresholdIndex.getSecondMax()-thresholdIndex.getFirstmax());	
		return bndArray;
	}
	
	/**
	 * �����趨������������ͷ���������õ��������� (��ʱֻ����Ϊ3��5�Σ������õݹ�����������,��Ҫ�ع�����)
	 * @param len �趨���������
	 * @param times �������
	 */
	public PosNegBndInfos  get_once_result(DataInfo[] objArray, int len){
		DataInfo[] arrays = objArray;
		int posLen = len;
			ThresholdIndex thresholdIndex = getMaxAndNext(arrays);
			if(PosNumber(arrays, thresholdIndex) >= posLen){
				thresholdIndex.setSecondMax(arrays.length-1 -(posLen/2));
			}
			DataInfo[] posInfos = getPosObjs(arrays, thresholdIndex);
			
			//�õ��߽�������
			DataInfo[] bndArray = getBndObjs(arrays, thresholdIndex);		
			DataInfo[] negArray = getNegObjs(arrays, thresholdIndex);
			
			return new PosNegBndInfos(posInfos, negArray, bndArray);
	}
	
	public int PosNumber(DataInfo[] nums, ThresholdIndex thresholdIndex){
		return nums.length - thresholdIndex.getSecondMax();
	}
	/**
	 * ���������ƽ��ֵ
	 * @param nums ���������
	 * @param len ������Ҫ�����ƽ��ֵ�Ķ���ĸ���
	 * @return �����ƽ��ֵ
	 */
	
	public float calc_average(float[] nums, int len){
		float sum = 0;
		
		for(int i = 0; i < len; i ++){
			sum +=nums[i];	
		}
		return sum/len;
	}
	/**
	 * ���������ƽ����
	 * @param nums ���������
	 * @param len ������Ҫ�����ƽ�����Ķ���ĸ���
	 * @return �����ƽ����
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
	 * �����������ƫ��
	 * @return �������ƫ�� ����
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
	 * ���㵥��������ռ��Ȩ��
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
	 * �����ۼӵ�Ȩ�أ� ���磺W3=w1+w2+w3;
	 * @param nums
	 * @param len
	 * @return
	 */
	public static float calc_weight(DataInfo nums[],int len,int k){ //k��ʾ���±�0�ӵ��ĸ�λ��
		float sum = 0;
		for(int i = 0; i < k+1; i++){ //i<len-1:��ʾ���һ��Ԫ�ز��������
			sum += calc_precent(nums, len, nums[i].getValue());
		}
		return sum;
	}
	
	/**
	 * ���㵥������Ļ���ϵ��
	 * @param nums
	 * @param len
	 * @return
	 */
	public static float clac_obj_gnni(DataInfo nums[], int len){
		float sum = 0;
		if(len == 1){
			return 0;
		}else{
			for(int i = 0; i < len; i++){//���һ���ۼ�Ȩ�ز��������
				sum += calc_weight(nums, len, i-1); //���һ���ۼ�Ȩ�ز�������㣬����i-1
			}
			return 1- (2*sum+1)/len;
		}
	}
	
	public float[] clac_all_gnni(){
		float result[] = new float[objArray.length];
		result[0] = 0; //ֻ��һ��Ԫ�ص�����gnni��0
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

	
