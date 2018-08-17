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

public class TeamManager {
	Scanner scanner;
	Logger logger;
	PlayerManager playerManager;
	CricketDAO dao;
	
	public TeamManager() {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		playerManager = new PlayerManager();
		dao = new CricketDAO();
	}
	
	public void displayTeams(User user) {
		ArrayList<Team> teamList = dao.fetchTeams(user);
		for (Team team : teamList) {
			logger.info(UserMessages.TEAM_TABLE);
			logger.info(team.getTeamId() + "\t" + team.getTeamName());
			playerManager.printPlayerDetails(team);
		}
	}
	
	public void createTeam(User user) {
		dao.insertTeam(user);
	}
	
	public void updateTeamName(Team team) {
		dao.updateTeamName(team);
	}
	
	/*public boolean searchTeam(int teamId, User user) {
		return dao.searchTeam(user, teamId);
	}*/
	
	public void addNewPlayer(User user) {
		dao.insertPlayer(user);
	}
	
	public Team fetchTeam(int teamId) {
		return dao.fetchTeam(teamId);
	}
}
