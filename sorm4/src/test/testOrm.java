package test;

import java.util.List;

import po.User;
import sorm.core.Query;
import Factory.QueryFactory;

public class testOrm {
	public static void main(String[] args) {
		Query q = QueryFactory.getQuery();
		
		long start = System.currentTimeMillis();
		for(int i =0; i <3000; i++){
			List<User> users = q.queryRows("select * from user where age>?", User.class, new Object[]{10});
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		//for(User user: users){
			//System.out.println(user);
	}
}
