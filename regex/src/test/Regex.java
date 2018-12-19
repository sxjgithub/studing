package test;
/*�������ʹ�÷�����
		1. ����������ʽ�� Pattern pat = Pattern.compile(��������ʽ��);
		2. ����ƥ�� �� Matcher mat = pat.matcher("Ҫ������ַ���");
		3.Ȼ��Ϳ���ƥ������滻�ˣ� mat.matches() �� mat.replaceAll("")
	ע���ָ���΢��ͬ��1.����������ʽ�� Pattern pat = Pattern.compile(��������ʽ��); 
				2.ֱ�ӷָString result[] = pat.split("\\d+") 
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*֪ʶ�㣺
 *  1.Ϊʲôjava������ƥ�� ��С���㡱 Ҫд�� "\\." ��
 *  	������ʽ���ʾ.��\.��java�У�\Ҫת��Ϊ\\������java�б�ʾ����\\.��  �����\ת��һ������Ҫת����ַ������ġ�
 *  2.\d ΪʲôҪд��\\d ?
 *  	������ʽ���Ǵ���"\d"�����ַ����� \ ��ʾת�壬������Ҫ����ǰ����� \ ת��ɷ�б��
 *
 * 
 * */


/*
 * ��Ҫ����������:
 * 	1. matches(������ʽ)   ƥ��
 * 	2. replaceAll(������ʽ) 	replaceFirst(������ʽ)	�滻
 *  3. split(������ʽ)  �ָ�
 * 
 * */
public class Regex {
	public static void main(String args[]){
		/*
		 * String �Դ���������ʽ
		 * */
		String str = "a@1b$%#b22ccc3*&33d ddd";
		
		boolean result1 = str.matches(".+"); //ƥ��
		System.out.println(result1);
		
		String[] result2 =str.split("\\d+"); //�ָ�
		for(int i = 0 ; i < result2.length ; i++){
			System.out.println(result2[i]);
		}
		
		String result3 = str.replaceAll("\\W+", ""); //�滻
		System.out.println(result3);
		
		/*
		 * ������ʽ��:
		 * 	�ŵ㣺������ƥ�䣬���ܰ�ƥ�䵽������ȡ��
		 * */
		
		String str_re = "INSERT INTO dept(deptno,dname) VALUES(#{deptno}, #{dname})";
		
		String regex1 = "#\\{\\w+\\}"; //��ʾ "#{����+}"
		Pattern pat = Pattern.compile(regex1);
		Matcher mat = pat.matcher(str_re);
		while(mat.find()){
			System.out.println(mat.group(0));   // #{deptno}   #{dname}
		} 

	}
}
