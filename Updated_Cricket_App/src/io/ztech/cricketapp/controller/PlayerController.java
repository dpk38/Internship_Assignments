package io.ztech.cricketapp.controller;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.delegate.PlayerManager;

public class PlayerController {

	PlayerManager playerManager;
	
	public PlayerController() {
		playerManager = new PlayerManager();
	}
	
	/*public boolean searchPlayer(int playerId, User user) {
		return playerManager.searchPlayer(playerId, user);
	}*/
	
	public void removePlayer(Team team) {
		playerManager.removePlayer(team);
	}
	
	public void displayPlayer(User user) {
		playerManager.displayPlayer(user);
	}
	
	public void updateTeamId(Team team) {
		playerManager.updateTeamId(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		playerManager.updatePlayerName(player, query);
	}
}
