package io.ztech.cricketapp.beans;

public class MatchStats {
	int matchId, teamId, runsScored, target, ballsLeft;
	float overNo;
	
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getRunsScored() {
		return runsScored;
	}
	public void setRunsScored(int runsScored) {
		this.runsScored = runsScored;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getBallsLeft() {
		return ballsLeft;
	}
	public void setBallsLeft(int ballsLeft) {
		this.ballsLeft = ballsLeft;
	}
	public float getOverNo() {
		return overNo;
	}
	public void setOverNo(float overNo) {
		this.overNo = overNo;
	}
}
