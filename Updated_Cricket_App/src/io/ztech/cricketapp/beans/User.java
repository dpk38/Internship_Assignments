package io.ztech.cricketapp.beans;

import java.util.ArrayList;

public class User {
	int userId;
	String userName, firstName, lastName, password;
	ArrayList<Player> players;
	ArrayList<Team> teams;
	ArrayList<Match> matches;
	
	public User() {
		players = new ArrayList<>();
		teams = new ArrayList<>();
		matches = new ArrayList<>();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	public void addPlayer(ArrayList<Player> players) {
		players.addAll(players);
	}
	public void addTeam(Team team) {
		teams.add(team);
	}
	public void addMatch(Match match) {
		matches.add(match);
	}
}
