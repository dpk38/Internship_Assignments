package io.ztech.cricketapp.delegate;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.dao.CricketDAO;

public class UserManager {
	CricketDAO dao;
	
	public UserManager() {
		dao = new CricketDAO();
	}
	
	public boolean checkUser(User user) {
		return dao.searchUser(user);
	}
	
	public User verifyUser(User user) {
		return dao.fetchUser(user);
	}
	
	public void createUser(User newUser) {
		dao.insertUser(newUser);
	}
}
