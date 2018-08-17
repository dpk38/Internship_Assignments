package io.ztech.cricketapp.beans;

public class BallStats {
	int matchId, teamBatting, teamBowling, bowlerId, batmsanId, runsGiven;
	boolean wicketTaken;

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getTeamBatting() {
		return teamBatting;
	}

	public void setTeamBatting(int teamBatting) {
		this.teamBatting = teamBatting;
	}

	public int getTeamBowling() {
		return teamBowling;
	}

	public void setTeamBowling(int teamBowling) {
		this.teamBowling = teamBowling;
	}

	public int getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(int bowlerId) {
		this.bowlerId = bowlerId;
	}

	public int getBatmsanId() {
		return batmsanId;
	}

	public void setBatmsanId(int batmsanId) {
		this.batmsanId = batmsanId;
	}

	public int getRunsGiven() {
		return runsGiven;
	}

	public void setRunsGiven(int runsGiven) {
		this.runsGiven = runsGiven;
	}

	public boolean getWicketTaken() {
		return wicketTaken;
	}

	public void setWicketTaken(boolean wicketTaken) {
		this.wicketTaken = wicketTaken;
	}

	public BallStats(int matchId, int teamBatting, int teamBowling, int bowlerId, int batmsanId, int runsGiven,
			boolean wicketTaken) {
		super();
		this.matchId = matchId;
		this.teamBatting = teamBatting;
		this.teamBowling = teamBowling;
		this.bowlerId = bowlerId;
		this.batmsanId = batmsanId;
		this.runsGiven = runsGiven;
		this.wicketTaken = wicketTaken;
	}
}
