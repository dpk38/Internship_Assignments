package io.ztech.cricketapp.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.constants.UserOptions;
import io.ztech.cricketapp.controller.UserController;

public class UserEntry {
	
	Logger logger;
	Scanner scanner;
	UserController userController;
	
	public UserEntry() {
		userController = new UserController();
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
	}
	
	public User login() {
		User user = new User();
		char retry;
		User verifiedUser = null;
		do {
			retry = 'n';
			logger.info(UserMessages.ENTER_USER_NAME);
			user.setUserName(scanner.nextLine());
			if (!userController.checkUser(user)) {
				logger.info(UserMessages.NO_SUCH_USER);
				UserOptions option = UserOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case SIGN_UP:
					return signUp();
				case RETRY:
					retry = 'y';
					break;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
					retry = 'y';
				}
			} else {
				logger.info(UserMessages.ENTER_PASSWORD);
				user.setPassword(scanner.nextLine());
				verifiedUser = userController.verifyUser(user); 
				if (verifiedUser == null) {
					logger.info(UserMessages.INCORRECT_PASSWORD);
					retry = 'y';
				} else {
					retry = 'n';
				}
			}
		} while (retry == 'y');
		return verifiedUser;
	}
	
	public User signUp() {
		User newUser = new User();
		do {
			logger.info(UserMessages.ENTER_FIRST_NAME);
			newUser.setFirstName(scanner.nextLine());
			logger.info(UserMessages.ENTER_LAST_NAME);
			newUser.setLastName(scanner.nextLine());
			logger.info(UserMessages.ENTER_USER_NAME);
			newUser.setUserName(scanner.nextLine());
			logger.info(UserMessages.ENTER_PASSWORD);
			newUser.setPassword(scanner.nextLine());
		} while (!userController.createUser(newUser));
		return newUser;
	}
}
