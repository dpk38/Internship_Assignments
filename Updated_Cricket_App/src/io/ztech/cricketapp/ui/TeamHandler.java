package io.ztech.cricketapp.ui;

import java.util.Scanner;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.controller.PlayerController;
import io.ztech.cricketapp.controller.TeamController;
import io.ztech.cricketapp.exceptions.InvalidNameException;

public class TeamHandler {
	Scanner scanner;
	PlayerHandler playerHandler;
	PlayerController playerController;
	TeamController teamController;
	
	public TeamHandler() {
		scanner = new Scanner(System.in);
		playerHandler = new PlayerHandler();
		teamController = new TeamController();
		playerController = new PlayerController();
	}
	
	public void createTeam(User user) {
		char retry;
		do {
			retry = 'n';
			try {
				Team newTeam = new Team();
				newTeam.setUser(user);
				System.out.print(UserMessages.ENTER_TEAM_NAME);
				newTeam.setTeamName(scanner.nextLine());
				System.out.print(UserMessages.CREATING_PLAYERS);
				char choice;
				do {
					Player newPlayer = new Player();
					newPlayer.setUser(user);
					playerHandler.getPlayerDetails(newPlayer);
					newTeam.addPlayer(newPlayer);
					System.out.print(UserMessages.ADD_ANOTHER);
					choice = scanner.nextLine().charAt(0);
				} while (Character.toLowerCase(choice) == 'y');
				teamController.createTeam(newTeam, user);
			} catch(InvalidNameException e) {
				System.out.println(e);
				retry = 'y';
			}
		} while (retry == 'y');
	}
	
	public void editTeamName(User user) {
		int teamId = playerHandler.chooseTeam(user);
		Team updateThisTeam = null;
		for (Team team : user.getTeams()) {
			if (team.getTeamId() == teamId) {
				updateThisTeam = team;
				break;
			}
		}
		char retry;
		do {
			retry = 'n';
			System.out.println(UserMessages.ENTER_NEW_NAME);
			String newTeamName = scanner.nextLine();
			try {
				teamController.updateTeamName(updateThisTeam, newTeamName);
			} catch (InvalidNameException e) {
				System.out.println(e);
				retry = 'y';
			}
		} while (retry == 'y');
	}
	
	public void removePlayer(User user) {
		int teamId = playerHandler.chooseTeam(user);
		int playerId = playerHandler.choosePlayer(user);

		// REMOVE FROM USER PLAYER LIST AS WELL
		for (Team team : user.getTeams()) {
			if (team.getTeamId() == teamId) {
				user.getTeams().remove(team);
				break;
			}
		}
		
		for (Player player : user.getPlayers()) {
			if (player.getPlayerId() == playerId) {
				user.getPlayers().remove(player);
				break;
			}
		}
		
		Team team = new Team();
		team.setTeamId(teamId);
		Player player = new Player();
		player.setPlayerId(playerId);
		team.addPlayer(player);
		playerController.removePlayer(team);
	}
}
