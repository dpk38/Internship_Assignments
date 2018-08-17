package io.ztech.cricketapp.delegate;

import java.util.ArrayList;
import java.util.Scanner;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.dao.CricketDAO;

public class PlayerManager {
	
	Scanner scanner;
	CricketDAO dao;
	
	public PlayerManager() {
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
		System.out.println(UserMessages.PLAYER_TABLE);
		for (Player player : playerList) {
			System.out.println(player.getTeamId() + "\t" + player.getPlayerId() + "\t" + player.getFirstName() + "\t\t" + player.getLastName());
		}
	}
	
	public void updateTeamId(int teamId, int playerId) {
		dao.updatePlayerTeam(teamId, playerId);
	}
	
	public void updatePlayerName(int playerId, String newName, String query) {
		dao.updatePlayerName(playerId, newName, query);
	}
}
