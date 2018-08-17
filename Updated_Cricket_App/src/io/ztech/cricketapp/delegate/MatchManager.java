package io.ztech.cricketapp.delegate;

import java.util.ArrayList;
import java.util.Scanner;

import io.ztech.cricketapp.beans.BallStats;
import io.ztech.cricketapp.beans.Match;
import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.MatchResult;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.dao.CricketDAO;

public class MatchManager {

	Scanner scanner;
	CricketDAO dao;

	public MatchManager() {
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}

	public void setMatch(Match newMatch) {
		dao.insertMatch(newMatch);
	}

	public void displayMatches(User user) {
		ArrayList<Match> matchList = dao.fetchMatches(user);
		System.out.println(UserMessages.MATCH_TABLE);
		for (Match match : matchList) {
			Team team = match.getTeamA();
			team = dao.fetchTeam(team.getTeamId());
			match.setTeamA(team);
			team = match.getTeamB();
			team = dao.fetchTeam(team.getTeamId());
			match.setTeamB(team);
			String toss;
			if (match.getTossWonBy() == match.getTeamA().getTeamId()) {
				toss = match.getTeamA().getTeamName();
			} else if (match.getTossWonBy() == match.getTeamB().getTeamId()) {
				toss = match.getTeamB().getTeamName();
			} else {
				toss = "NA";
			}
			
			if (match.getMatchResult() == null) {
				match.setMatchResult(MatchResult.NA);
			}

			System.out.println(match.getMatchId() + "\t" + match.getMatchDate() + "\t" + match.getTeamA().getTeamName()
					+ "\t" + match.getTeamB().getTeamName() + "\t" + match.getStatus() + "\t" + toss + "\t"
					+ match.getMatchResult());
		}
	}

	public boolean searchMatch(User user, int matchId) {
		return dao.searchMatch(user, matchId);
	}

	public Match fetchMatch(int matchId) {
		return dao.fetchMatch(matchId);
	}

	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return dao.fetchPlayers(players);
	}

	public void insertBallStats(BallStats ballStats) {
		dao.insertBallStats(ballStats);
	}
}
