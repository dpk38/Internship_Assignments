package io.ztech.cricketapp;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.ui.Menu;
import io.ztech.cricketapp.ui.UserEntry;

public class Main {
	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format",
                "%5$s%6$s%n");
		
		User user = new User();
		UserEntry userEntry = new UserEntry();
		user = userEntry.login();
		Menu menuManager = new Menu(user);
		menuManager.showMainMenu();
	}
}
