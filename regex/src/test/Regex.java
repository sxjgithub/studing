package test;
/*正则类的使用方法：
		1. 编译正则表达式： Pattern pat = Pattern.compile(“正则表达式”);
		2. 正则匹配 ： Matcher mat = pat.matcher("要正则的字符串");
		3.然后就可以匹配或者替换了： mat.matches() 、 mat.replaceAll("")
	注：分割稍微不同：1.编译正则表达式： Pattern pat = Pattern.compile(“正则表达式”); 
				2.直接分割：String result[] = pat.split("\\d+") 
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*知识点：
 *  1.为什么java里正则匹配 “小数点” 要写成 "\\." ？
 *  	正则表达式里表示.用\.。java中，\要转义为\\，所以java中表示就是\\.。  如果用\转义一个不需要转义的字符会出错的。
 *  2.\d 为什么要写成\\d ?
 *  	正则表达式中是纯粹"\d"，在字符串中 \ 表示转义，所以需要在其前面加上 \ 转义成反斜杠
 *
 * 
 * */


/*
 * 主要有三个方法:
 * 	1. matches(正则表达式)   匹配
 * 	2. replaceAll(正则表达式) 	replaceFirst(正则表达式)	替换
 *  3. split(正则表达式)  分割
 * 
 * */
public class Regex {
	public static void main(String args[]){
		/*
		 * String 自带的正则表达式
		 * */
		String str = "a@1b$%#b22ccc3*&33d ddd";
		
		boolean result1 = str.matches(".+"); //匹配
		System.out.println(result1);
		
		String[] result2 =str.split("\\d+"); //分割
		for(int i = 0 ; i < result2.length ; i++){
			System.out.println(result2[i]);
		}
		
		String result3 = str.replaceAll("\\W+", ""); //替换
		System.out.println(result3);
		
		/*
		 * 正则表达式类:
		 * 	优点：不仅能匹配，还能把匹配到的内容取出
		 * */
		
		String str_re = "INSERT INTO dept(deptno,dname) VALUES(#{deptno}, #{dname})";
		
		String regex1 = "#\\{\\w+\\}"; //表示 "#{单词+}"
		Pattern pat = Pattern.compile(regex1);
		Matcher mat = pat.matcher(str_re);
		while(mat.find()){
			System.out.println(mat.group(0));   // #{deptno}   #{dname}
		} 

	}
}
