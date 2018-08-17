package io.ztech.cricketapp.ui;

import java.util.Scanner;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Queries;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.controller.PlayerController;
import io.ztech.cricketapp.controller.TeamController;
import io.ztech.cricketapp.exceptions.InvalidNameException;

public class PlayerHandler {
	Scanner scanner;
	PlayerController playerController;
	TeamController teamController;
	
	public PlayerHandler() {
		scanner = new Scanner(System.in);
		playerController = new PlayerController();
		teamController = new TeamController();
	}
	
	public void addNewPlayer(User user) {
		int teamId = chooseTeam(user);
		char retry;
		do {
			retry = 'n';
			Player newPlayer = new Player();
			newPlayer.setTeamId(teamId);
			newPlayer.setUser(user);
			try {
				getPlayerDetails(newPlayer);
				teamController.addNewPlayer(newPlayer, user);
			} catch (InvalidNameException e) {
				System.out.println(e);
				retry = 'y';
			}
		} while (retry == 'y');
	}
	
	public void getPlayerDetails(Player newPlayer) {
		System.out.print(UserMessages.ENTER_FIRST_NAME);
		newPlayer.setFirstName(scanner.nextLine());
		System.out.print(UserMessages.ENTER_LAST_NAME);
		newPlayer.setLastName(scanner.nextLine());
	}
	
	public int choosePlayer(User user) {
		int playerId;
		do {
			System.out.print(UserMessages.CHOOSE_PLAYER);
			playerId = scanner.nextInt();
			scanner.nextLine();
			boolean flag = false;
			for (Player player : user.getPlayers()) {		//if (playerController.searchPlayer(playerId, user)) {
				if (player.getPlayerId() == playerId) {
					flag = true;
					break;
				}
			}
			if (flag == true) {
				break;
			} else {
				System.out.println(UserMessages.NO_SUCH_PLAYER);
			}
		} while (true);
		return playerId;
	}
	
	
	public void editPlayer(User user, String field) {
		if (field.equals("team")) {
			System.out.println(UserMessages.SELECT_NEW_TEAM_ID);
			int newTeamId = chooseTeam(user);
			int playerId = choosePlayer(user);
			
			Player playerToUpdate = user.getPlayers().stream().filter(player -> player.getPlayerId() == playerId).findAny().get();
			int oldTeamId = playerToUpdate.getTeamId();
			playerToUpdate.setTeamId(newTeamId);
			
//			Team oldTeam = user.getTeams().stream().filter(team -> team.getTeamId() == oldTeamId).findAny().get();
			for (Team team : user.getTeams()) {
				if (team.getTeamId() == oldTeamId) {
					team.getPlayers().remove(playerToUpdate);
				}
				if (team.getTeamId() == newTeamId) {
					team.getPlayers().add(playerToUpdate);
				}
			}
			
			Team team = new Team();
			team.setTeamId(newTeamId);
			Player player = new Player();
			player.setPlayerId(playerId);
			team.addPlayer(player);
			playerController.updateTeamId(team);
		} else {
			teamController.displayTeams(user);
			int playerId = choosePlayer(user);
			System.out.print(UserMessages.ENTER_NEW_NAME);
			String newName = scanner.nextLine();
			if (field.equals("fname")) {
				playerController.updatePlayerName(playerId, newName, Queries.UPDATE_PLAYER_FIRST_NAME);
			} else {
				playerController.updatePlayerName(playerId, newName, Queries.UPDATE_PLAYER_LAST_NAME);
			}
		}
	}
	
	
	public int chooseTeam(User user) {
		int teamId;
		do {
			teamController.displayTeams(user);
			System.out.print(UserMessages.CHOOSE_TEAM);
			teamId = scanner.nextInt();
			scanner.nextLine();
			boolean flag = false;
			for (Team team : user.getTeams()) {		//if (teamController.searchTeam(teamId, user)) {
				if (team.getTeamId() == teamId) {
					flag = true;
					break;
				}
			} 
			if (flag == true) {
				break;
			} else {
				System.out.println(UserMessages.NO_SUCH_TEAM);
			}
		} while (true);
		return teamId;
	}
}
