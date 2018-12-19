package service;
import DAO.UserDAO;
import Impl.UserDAOImpl;
import model.User ;

public class UserService {
	private UserDAO userDAO = new UserDAOImpl();
	
	public void add(User user){
		userDAO.save(user) ;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public static void main(String[] args) {
		UserService us = new UserService() ;
		User u = new User() ;
		us.add(u);
		
	}
}
