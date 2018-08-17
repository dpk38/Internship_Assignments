package io.ztech.cricketapp.controller;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Regex;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.delegate.UserManager;

public class UserController {
	UserManager userManager;
	Validator validator;

	public UserController() {
		userManager = new UserManager();
		validator = new Validator();
	}

	public boolean checkUser(User user) {
		return userManager.checkUser(user);
	}

	public User verifyUser(User user) {
		return userManager.verifyUser(user);
	}

	public boolean createUser(User newUser) {
		if (validator.validateInput(Regex.nameRegex, newUser.getFirstName(), UserMessages.INVALID_FIRST_NAME)
				&& validator.validateInput(Regex.nameRegex, newUser.getFirstName(), UserMessages.INVALID_FIRST_NAME)) {
			if (userManager.checkUser(newUser)) {
				System.out.println(UserMessages.USER_ALREADY_EXISTS);
				return false;
			}
			userManager.createUser(newUser);
			return true;
		} else {
			return false;
		}
	}
}
