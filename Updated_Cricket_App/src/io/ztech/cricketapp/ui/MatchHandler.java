package io.ztech.cricketapp.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.BallStats;
import io.ztech.cricketapp.beans.LineUp;
import io.ztech.cricketapp.beans.Match;
import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Regex;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.controller.MatchController;
import io.ztech.cricketapp.controller.TeamController;
import io.ztech.cricketapp.controller.Validator;

public class MatchHandler {
	Logger logger;
	Scanner scanner;
	Validator validator;
	PlayerHandler playerHandler;
	TeamController teamController;
	MatchController matchController;
	
	public MatchHandler() {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		playerHandler = new PlayerHandler();
		teamController = new TeamController();
		matchController = new MatchController();
		validator = new Validator();
	}
	
	public void createMatch(User user) {
		Match newMatch = new Match();
		logger.info(UserMessages.SELECT_TWO_TEAMS);
		int teamAid = playerHandler.chooseTeam(user);
		int teamBid = playerHandler.chooseTeam(user);
		Team teamA = teamController.fetchTeam(teamAid);
		Team teamB = teamController.fetchTeam(teamBid);
		
		newMatch.setUser(user);
		newMatch.setTeamA(teamA);
		newMatch.setTeamB(teamB);
		newMatch.setTeamALineUp(getLineUp(teamA));
		newMatch.setTeamBLineUp(getLineUp(teamB));
		scanner.nextLine();	
		newMatch.setStatus("scheduled");
		String date;
		char retry;
		do {
			retry = 'n';
			logger.info(UserMessages.ENTER_MATCH_DATE);
			date = scanner.nextLine();
			if (!(validator.validateInput(Regex.dateRegex, date, UserMessages.INVALID_DATE))) {
				retry = 'y';
			}
		} while (retry == 'y');
		Date matchDate = Date.valueOf(date);
		newMatch.setMatchDate(matchDate);
		matchController.setMatch(newMatch);
	}
	
	public LineUp getLineUp(Team team) {
		LineUp lineUp = new LineUp();
		lineUp.setTeamId(team.getTeamId());
		logger.info(UserMessages.NUMBER_OF_PLAYERS + team.getTeamName());
		int numberOfPlayers = scanner.nextInt();
		logger.info(UserMessages.ENTER_LINE_UP + team.getTeamName());
		ArrayList<Integer> order = new ArrayList<Integer>();
		for (int i = 0; i < numberOfPlayers; i++) {
			order.add(scanner.nextInt());
		}
		lineUp.setPlayerId(order);
		return lineUp;
	}

	public void playMatch(User user) {
		Team battingTeam, bowlingTeam;
		ArrayList<Integer> batsmen, bowlers;
		int onStrike, offStrike, bowler, totalScore = 0;
		float overCount = 0;
		
		int matchId = chooseMatch(user);
		Match match = matchController.fetchMatch(matchId);
		
		match.setStatus("ongoing");
		match.setTossWonBy(tossCoin(match));
		
		logger.info(UserMessages.CHOSE_TO_BAT);
		int battingTeamId = scanner.nextInt();
		scanner.nextLine();
		if (battingTeamId == match.getTeamA().getTeamId()) {
			battingTeam = match.getTeamA();
			batsmen = match.getTeamALineUp().getPlayerId();
			bowlingTeam = match.getTeamB();
			bowlers = match.getTeamBLineUp().getPlayerId();
		} else {
			battingTeam = match.getTeamB();
			batsmen = match.getTeamBLineUp().getPlayerId();
			bowlingTeam = match.getTeamA();
			bowlers = match.getTeamALineUp().getPlayerId();
		}
		showPlayers(batsmen);
		logger.info(UserMessages.CHOOSE_ONSTRIKE);
		onStrike = choosePlayer(match, battingTeam);
		batsmen.remove((Integer) onStrike);
		logger.info(UserMessages.CHOOSE_OFFSTRIKE);
		offStrike = choosePlayer(match, battingTeam);
		batsmen.remove((Integer) offStrike);
		showPlayers(bowlers);
		logger.info(UserMessages.CHOOSE_BOWLER);
		bowler = choosePlayer(match, bowlingTeam);
		
		logger.info(UserMessages.INPUT_FORMAT);
		while (overCount < 20.0) {
			int score = 0;
			boolean wicketTaken = false;
			logger.info(UserMessages.CURRENT_SCORE + totalScore + UserMessages.ON_STRIKE + onStrike + UserMessages.OFF_STRIKE + offStrike + UserMessages.BOWLER + bowler);
			String ballPlayed, extra = "";
			do {
				logger.info(UserMessages.ENTER_BALL);
				ballPlayed = scanner.nextLine();
				if (validator.validateInput(Regex.ballRegex, ballPlayed, UserMessages.INVALID_BALL)) {
					break;
				} else {
					logger.info(UserMessages.INPUT_FORMAT);
					continue;
				}
			} while (true);
			
			if (!ballPlayed.contains("+")) {
				score = Integer.parseInt(ballPlayed);
			} else {
				extra = ballPlayed.split("\\+")[0];
				score = Integer.parseInt(ballPlayed.split("\\+")[1]);
			}
			
			if (extra.equals("out")) {
				wicketTaken = true;
			} else if (extra.equals("no") || extra.equals("w")) {
				totalScore += 1;
			}
			
			totalScore += score;
			BallStats ballStats = new BallStats(matchId, battingTeam.getTeamId(), bowlingTeam.getTeamId(), bowler, onStrike, score, wicketTaken);
			matchController.insertBallStats(ballStats);
			if (score % 2 != 0) {
				int tempPlayer = onStrike;
				onStrike = offStrike;
				offStrike = tempPlayer;
			}
			if (wicketTaken) {
				if (batsmen.isEmpty()) {
					logger.info(UserMessages.ALL_PLAYERS_OUT + totalScore);
					break;
				}
				showPlayers(batsmen);
				logger.info(UserMessages.CHOOSE_NEXT_BATSMAN);
				onStrike = choosePlayer(match, battingTeam);
				batsmen.remove((Integer) onStrike);
			}
			overCount += 0.1;
			if (((int) (overCount * 10)) % 10 == 6) {
				overCount += 0.4;
				int tempPlayer = onStrike;
				onStrike = offStrike;
				offStrike = tempPlayer;
				int previousBowler = bowler;
				showPlayers(bowlers);
				do {
					logger.info(UserMessages.CHOOSE_BOWLER);
					bowler = choosePlayer(match, bowlingTeam);
					if (bowler == previousBowler) {
						logger.info(UserMessages.SAME_BOWLER);
						char decision = scanner.nextLine().charAt(0);
						if (decision == 'y') {
							break;
						}
					} else {
						break;
					}
				} while (true);
				bowlers.add(bowler);
			}
		}
	}
	
	public int chooseMatch(User user) {
		int matchId;
		do {
			matchController.displayMatches(user);
			logger.info(UserMessages.CHOOSE_MATCH);
			matchId = scanner.nextInt();
			scanner.nextLine();
			if (matchController.searchMatch(user, matchId)) {
				break;
			} else {
				logger.info(UserMessages.NO_SUCH_MATCH);
			}
		} while (true);
		return matchId;
	}
	
	public int tossCoin(Match match) {
		Random random = new Random();
		if (random.nextInt(2) == 0) {
			logger.info(" ");
			logger.info(UserMessages.TOSS_RESULT + match.getTeamA().getTeamId());
			return match.getTeamA().getTeamId();
		} else {
			logger.info(UserMessages.TOSS_RESULT + match.getTeamB().getTeamId());
			return match.getTeamB().getTeamId();
		}
	}
	
	public int choosePlayer(Match match, Team team) {
		int retry, id;
		do {
			retry = 'n';
			id = scanner.nextInt();
			scanner.nextLine();
			if (team.getTeamId() == match.getTeamA().getTeamId()) {
				if (!(match.getTeamALineUp().getPlayerId().contains(id))) {
					logger.info(UserMessages.INVALID_PLAYER);
					retry = 'y';
				}
			} else {
				if (!(match.getTeamBLineUp().getPlayerId().contains(id))) {
					logger.info(UserMessages.INVALID_PLAYER);
					retry = 'y';
				}
			}
		} while (retry == 'y');
		return id;
	}

	public void showPlayers(ArrayList<Integer> players) {
		ArrayList<Player> playerList = matchController.fetchPlayers(players);
		logger.info(UserMessages.PLAYER_NAME_TABLE);
		for (Player player : playerList) {
			logger.info(player.getPlayerId() + "\t" + player.getFirstName() + "\t" + player.getLastName());
		}
	}
}