package io.ztech.cricketapp.delegate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.dao.CricketDAO;
import io.ztech.cricketapp.ui.UserEntry;

public class PlayerManager {
	Logger logger;
	Scanner scanner;
	CricketDAO dao;
	
	public PlayerManager() {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}
	
	/*public boolean searchPlayer(int playerId, User user) {
		return dao.searchPlayer(user, playerId);
	}*/
	
	public void removePlayer(Team team) {
		dao.deletePlayer(team);
	}
	
	public void displayPlayer(User user) {
		ArrayList<Team> teamList = dao.fetchTeams(user);
		for (Team team : teamList) {
			printPlayerDetails(team);
		}
	}
	
	public void printPlayerDetails(Team team) {
		ArrayList<Player> playerList = dao.fetchTeamPlayers(team);
		logger.info(UserMessages.PLAYER_TABLE);
		for (Player player : playerList) {
			logger.info(player.getTeamId() + "\t" + player.getPlayerId() + "\t" + player.getFirstName() + "\t\t" + player.getLastName());
		}
	}
	
	public void updateTeamId(Team team) {
		dao.updatePlayerTeam(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		dao.updatePlayerName(player, query);
	}
}
