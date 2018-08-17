package io.ztech.cricketapp.beans;

import java.util.ArrayList;

public class Team {
	int teamId;
	String teamName;
	ArrayList<Player> players;
	User user;
	
	public Team() {
		players = new ArrayList<Player>();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void addPlayer(Player player) {
		players.add(player);
	}
}
