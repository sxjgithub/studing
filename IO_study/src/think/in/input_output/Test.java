package think.in.input_output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * 现在思考怎么把12.45(double类型的数)转换成byte数组、
 * 	以及byte数组转化成12.45（double类型）。
 * 
 * 方法：用DataInputStream/DataOutputStream 
 * 	结合ByteArrayInputStream/ByteArrayOutputStream	,
 * 
 * 体会其中的输入、输出的使用----------------------。
 * @author sxj
 *
 */
public class Test {
	
	public static void main (String[] args) throws Exception{
		double num =12.45;
		byte[] data = Test.double2ByteArray(num);
		System.out.println(new String(data, "utf-8"));
		
		double testNum = Test.byteArray2Double(data);
		System.out.println(testNum);
	}
	
	public static byte[] double2ByteArray(double num) throws Exception{
		/**
		 * num----> byte[]
		 * byteOutputStream
		 * 为什么使用byteOutputStream流？-->站在程序的角度，是要把num(代表程序)参数输出到内存的byte[]数组的容器中，
		 */
		byte[] data = null;  
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		dout.writeDouble(num);
		
		data = bout.toByteArray();
		
		dout.close();
		return data;
	}
	
	public static double byteArray2Double(byte[] data) throws Exception{
		/**
		 * byte[]  --> num
		 * ByteArrayInputStream
		 * 把byte[] 用内存输入流，输入到程序（num）中
		 */
		ByteArrayInputStream bin = new ByteArrayInputStream(data);
		DataInputStream din = new DataInputStream(bin);
		double num = din.readDouble();
		return num;
	}
}
