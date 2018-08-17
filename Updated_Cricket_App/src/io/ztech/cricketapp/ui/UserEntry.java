package io.ztech.cricketapp.ui;

import java.util.Scanner;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.constants.UserOptions;
import io.ztech.cricketapp.controller.UserController;

public class UserEntry {
	
	Scanner scanner;
	UserController userController;
	
	public UserEntry() {
		userController = new UserController();
		scanner = new Scanner(System.in);
	}
	
	public User login() {
		User user = new User();
		char retry;
		User verifiedUser = null;
		do {
			retry = 'n';
			System.out.print(UserMessages.ENTER_USER_NAME);
			user.setUserName(scanner.nextLine());
			if (!userController.checkUser(user)) {
				System.out.println(UserMessages.NO_SUCH_USER);
				UserOptions option = UserOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case SIGN_UP:
					return signUp();
				case RETRY:
					retry = 'y';
					break;
				default:
					System.out.println(UserMessages.INVALID_CHOICE);
					retry = 'y';
				}
			} else {
				System.out.print(UserMessages.ENTER_PASSWORD);
				user.setPassword(scanner.nextLine());
				verifiedUser = userController.verifyUser(user); 
				if (verifiedUser == null) {
					System.out.println(UserMessages.INCORRECT_PASSWORD);
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
			System.out.print(UserMessages.ENTER_FIRST_NAME);
			newUser.setFirstName(scanner.nextLine());
			System.out.print(UserMessages.ENTER_LAST_NAME);
			newUser.setLastName(scanner.nextLine());
			System.out.print(UserMessages.ENTER_USER_NAME);
			newUser.setUserName(scanner.nextLine());
			System.out.print(UserMessages.ENTER_PASSWORD);
			newUser.setPassword(scanner.nextLine());
		} while (!userController.createUser(newUser));
		return newUser;
	}
}
