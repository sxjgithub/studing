package Impl;

import model.User;
import DAO.UserDAO;

public class UserDAOImpl implements UserDAO{

	@Override
	public void save(User user) {
		System.out.println("a have saved!!");	
	}
	
}
