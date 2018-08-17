package io.ztech.cricketapp.beans;

public class PlayerStats {
	int matchId, playerId, runsScored, ballsBowled, wicketTaker, wicketsTaken, runsGiven;
	float economy;
	
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getRunsScored() {
		return runsScored;
	}
	public void setRunsScored(int runsScored) {
		this.runsScored = runsScored;
	}
	public int getBallsBowled() {
		return ballsBowled;
	}
	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}
	public int getWicketTaker() {
		return wicketTaker;
	}
	public void setWicketTaker(int wicketTaker) {
		this.wicketTaker = wicketTaker;
	}
	public int getWicketsTaken() {
		return wicketsTaken;
	}
	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}
	public int getRunsGiven() {
		return runsGiven;
	}
	public void setRunsGiven(int runsGiven) {
		this.runsGiven = runsGiven;
	}
	public float getEconomy() {
		return economy;
	}
	public void setEconomy(float economy) {
		this.economy = economy;
	}
}
