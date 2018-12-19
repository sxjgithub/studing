package demo1;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		List<String> all = new ArrayList<String>();
		all.add("a");
		all.add("c");
		all.add("c");
		
		all.forEach(System.out::println);
	}
}
