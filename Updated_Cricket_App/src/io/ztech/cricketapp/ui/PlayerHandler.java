package io.ztech.cricketapp.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Queries;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.controller.PlayerController;
import io.ztech.cricketapp.controller.TeamController;
import io.ztech.cricketapp.exceptions.InvalidNameException;

public class PlayerHandler {
	Logger logger;
	Scanner scanner;
	PlayerController playerController;
	TeamController teamController;
	
	public PlayerHandler() {
		logger = Logger.getLogger(UserEntry.class.getName());
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
				logger.info("" + e);
				retry = 'y';
			}
		} while (retry == 'y');
	}
	
	public void getPlayerDetails(Player newPlayer) {
		logger.info(UserMessages.ENTER_FIRST_NAME);
		newPlayer.setFirstName(scanner.nextLine());
		logger.info(UserMessages.ENTER_LAST_NAME);
		newPlayer.setLastName(scanner.nextLine());
	}
	
	public int choosePlayer(User user) {
		int playerId;
		do {
			logger.info(UserMessages.CHOOSE_PLAYER);
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
				logger.info(UserMessages.NO_SUCH_PLAYER);
			}
		} while (true);
		return playerId;
	}
	
	
	public void editPlayer(User user, String field) {
		if (field.equals("team")) {
			logger.info(UserMessages.SELECT_NEW_TEAM_ID);
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
			logger.info(UserMessages.ENTER_NEW_NAME);
			String newName = scanner.nextLine();
			Player playerToUpdate = user.getPlayers().stream().filter(player -> player.getPlayerId() == playerId).findAny().get();
			if (field.equals("fname")) {
				playerToUpdate.setFirstName(newName);
				playerController.updatePlayerName(playerToUpdate, Queries.UPDATE_PLAYER_FIRST_NAME);
			} else {
				playerToUpdate.setLastName(newName);
				playerController.updatePlayerName(playerToUpdate, Queries.UPDATE_PLAYER_LAST_NAME);
			}
		}
	}
	
	
	public int chooseTeam(User user) {
		int teamId;
		do {
			teamController.displayTeams(user);
			logger.info(UserMessages.CHOOSE_TEAM);
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
				logger.info(UserMessages.NO_SUCH_TEAM);
			}
		} while (true);
		return teamId;
	}
}
