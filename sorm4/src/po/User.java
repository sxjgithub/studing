package po;

import java.sql.*;
import java.util.*;

public class User{

	private String password;

	private Integer id;

	private Integer age;

	private String username;


	public String getPassword(){
		return password;
	}
	public Integer getId(){
		return id;
	}
	public Integer getAge(){
		return age;
	}
	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public void setAge(Integer age){
		this.age = age;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "id=" + id + "|" + "username=" + username + "|"
				+ "password=" + password + "|" + "age=" + age ;
	}

}