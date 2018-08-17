package io.ztech.cricketapp.controller;

import java.util.ArrayList;

import io.ztech.cricketapp.beans.BallStats;
import io.ztech.cricketapp.beans.Match;
import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.delegate.MatchManager;

public class MatchController {
	
	MatchManager matchManager;
	
	public MatchController() {
		matchManager = new MatchManager();
	}
	
	public void setMatch(Match newMatch) {
		matchManager.setMatch(newMatch);
	}
	
	public void displayMatches(User user) {
		matchManager.displayMatches(user);
	}
	
	public boolean searchMatch(User user, int matchId) {
		return matchManager.searchMatch(user, matchId);
	}
	
	public Match fetchMatch(int matchId) {
		return matchManager.fetchMatch(matchId);
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return matchManager.fetchPlayers(players);
	}
	
	public void insertBallStats(BallStats ballStats) {
		matchManager.insertBallStats(ballStats);
	}
}
