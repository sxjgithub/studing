package org.leno.mvc1.app.action;

public class LoginAction {
	private String username;

	private String password;
	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String login() throws Exception {
		System.out.println("Login:username:"+username+" password:"+password);
		if("sxj".equals(username)){
			return "success";
		}else{
			return null;
		}
	}
}