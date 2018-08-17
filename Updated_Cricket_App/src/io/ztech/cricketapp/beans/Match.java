package io.ztech.cricketapp.beans;

import java.sql.Date;

import io.ztech.cricketapp.constants.MatchResult;

public class Match {
	int matchId;
	User user;
	Date matchDate;
	Team teamA, teamB;
	LineUp teamALineUp, teamBLineUp;
	String status;
	int tossWonBy;
	MatchResult matchResult;
	
	public LineUp getTeamALineUp() {
		return teamALineUp;
	}
	public void setTeamALineUp(LineUp teamALineUp) {
		this.teamALineUp = teamALineUp;
	}
	public LineUp getTeamBLineUp() {
		return teamBLineUp;
	}
	public void setTeamBLineUp(LineUp teamBLineUp) {
		this.teamBLineUp = teamBLineUp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MatchResult getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public Team getTeamA() {
		return teamA;
	}
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}
	public Team getTeamB() {
		return teamB;
	}
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	public int getTossWonBy() {
		return tossWonBy;
	}
	public void setTossWonBy(int tossWonBy) {
		this.tossWonBy = tossWonBy;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
