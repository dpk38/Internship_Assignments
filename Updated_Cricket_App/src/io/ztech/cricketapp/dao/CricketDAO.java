package io.ztech.cricketapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.cricketapp.beans.BallStats;
import io.ztech.cricketapp.beans.LineUp;
import io.ztech.cricketapp.beans.Match;
import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.MatchResult;
import io.ztech.cricketapp.constants.Queries;
import io.ztech.cricketapp.dbutils.Connector;

public class CricketDAO {
	Connector connector;

	public CricketDAO() {
		connector = new Connector();
	}
	
	public void insertUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_USER);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at insertUser(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertPlayer(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ArrayList<Player> playerList = user.getPlayers();
			Player player = playerList.get(playerList.size() - 1);
			ps = con.prepareStatement(Queries.INSERT_PLAYER);
			ps.setInt(1, player.getTeamId());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setInt(4, player.getUser().getUserId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at insertPlayer(User): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertPlayer(Player player) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_PLAYER);
			ps.setInt(1, player.getTeamId());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setInt(4, player.getUser().getUserId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at insertPlayer(Player): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertTeam(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ArrayList<Team> teamList = user.getTeams();
			Team team = teamList.get(teamList.size() - 1);
			
			ps = con.prepareStatement(Queries.INSERT_TEAM);
			ps.setString(1, team.getTeamName());
			ps.setInt(2, team.getUser().getUserId());
			ps.execute();
			for (Player player : team.getPlayers()) {
				player.setTeamId(getRecentTeamId());
				insertPlayer(player);
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at insertTeam(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertMatch(Match match) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_MATCH);
			ps.setDate(1, match.getMatchDate());
			ps.setInt(2, match.getTeamA().getTeamId());
			ps.setInt(3, match.getTeamB().getTeamId());
			ps.setString(4, match.getStatus());
			ps.setInt(5, match.getUser().getUserId());
			ps.execute();
			int recentMatchId = getRecentMatchId();
			match.getTeamALineUp().setMatchId(recentMatchId);
			match.getTeamALineUp().setTeamId(match.getTeamA().getTeamId());
			match.getTeamBLineUp().setMatchId(recentMatchId);
			match.getTeamBLineUp().setTeamId(match.getTeamB().getTeamId());
			insertLineUp(match.getTeamALineUp());
			insertLineUp(match.getTeamBLineUp());
		} catch (SQLException e) {
			System.out.println("Exception caught at insertMatch(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertBallStats(BallStats ballStats) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_BALL_STATS);
			ps.setInt(1, ballStats.getMatchId());
			ps.setInt(2, ballStats.getTeamBatting());
			ps.setInt(3, ballStats.getTeamBowling());
			ps.setInt(4, ballStats.getBowlerId());
			ps.setInt(5, ballStats.getBatmsanId());
			ps.setInt(6, ballStats.getRunsGiven());
			ps.setBoolean(7, ballStats.getWicketTaken());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at insertBallStats(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertLineUp(LineUp lineUp) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			for (Integer id : lineUp.getPlayerId()) {
				ps = con.prepareStatement(Queries.INSERT_LINE_UP);
				ps.setInt(1, lineUp.getMatchId());
				ps.setInt(2, lineUp.getTeamId());
				ps.setInt(3, id);
				ps.execute();
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at insertLineUp(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public int getRecentTeamId() {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		int teamId = 0;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_TEAM_ID);
			rs = ps.executeQuery();
			rs.next();
			teamId = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Exception caught at getRecentTeamId(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
		return teamId;
	}
	
	public int getRecentMatchId() {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		int matchId = 0;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_MATCH_ID);
			rs = ps.executeQuery();
			rs.next();
			matchId = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Exception caught at getRecentMatchId(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
		return matchId;
	}
	
	public void updateTeamName(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_TEAM_NAME);
			ps.setString(1, team.getTeamName());
			ps.setInt(2, team.getTeamId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at updateTeamName(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void updatePlayerTeam(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_PLAYER_TEAM);
			ps.setInt(1, team.getTeamId());
			Player player = team.getPlayers().get(0);
			ps.setInt(2, player.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at updatePlayerTeam(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void updatePlayerName(int playerId, String newName, String query) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, newName);
			ps.setInt(2, playerId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at updatePlayerName(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public ArrayList<Player> fetchTeamPlayers(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_TEAM_PLAYERS);
			ps.setInt(1, team.getTeamId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Player newPlayer = new Player();
				newPlayer.setTeamId(rs.getInt("team_id"));
				newPlayer.setPlayerId(rs.getInt("player_id"));
				newPlayer.setFirstName(rs.getString("first_name"));
				newPlayer.setLastName(rs.getString("last_name"));
				playerList.add(newPlayer);
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchTeamPlayers(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}
	
	public User fetchUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_USER);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			while (rs.next()) {
				String password = rs.getString("password"); 
				if (user.getPassword().equals(password)) {
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setUserId(rs.getInt("user_id"));
					user.setPlayers(fetchPlayers(user));
					user.setTeams(fetchTeams(user));
					user.setMatches(fetchMatches(user));
				} else {
					user = null;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchUser(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return user;
	}

	public ArrayList<Player> fetchPlayers(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_USER_PLAYERS);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Player player = new Player();
				player.setPlayerId(rs.getInt(1));
				player.setFirstName(rs.getString(2));
				player.setLastName(rs.getString(3));
				playerList.add(player);
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchPlayers(User): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			for (Integer playerId : players) {
				Player player = new Player();
				ps = con.prepareStatement(Queries.FETCH_PLAYERS);
				ps.setInt(1, playerId);
				rs = ps.executeQuery();
				while (rs.next()) {
					player.setPlayerId(rs.getInt(1));
					player.setFirstName(rs.getString(2));
					player.setLastName(rs.getString(3));
					playerList.add(player);
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchPlayers(ArrayList): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}

	public Team fetchTeam(int teamId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		Team team = new Team();
		try {
			ps = con.prepareStatement(Queries.FETCH_TEAM);
			ps.setInt(1, teamId);
			rs = ps.executeQuery();
			while (rs.next()) {
				team.setTeamId(rs.getInt("team_id"));
				team.setTeamName(rs.getString("team_name"));
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return team;
	}
	
	public ArrayList<Team> fetchTeams(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Team> teamList = new ArrayList<>();

		try {
			ps = con.prepareStatement(Queries.FETCH_TEAMS);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Team newTeam = new Team();
				newTeam.setTeamId(rs.getInt("team_id"));
				newTeam.setTeamName(rs.getString("team_name"));
				teamList.add(newTeam);
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchTeams(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return teamList;
	}
	
	public Match fetchMatch(int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		Match match = null;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCH);
			ps.setInt(1, matchId);
			rs = ps.executeQuery();
			match = new Match();
			while (rs.next()) {
				match.setMatchId(rs.getInt(1));
				match.setMatchDate(rs.getDate(2));
				Team teamA = new Team();
				teamA.setTeamId(rs.getInt(3));
				match.setTeamA(teamA);
				
				Team teamB = new Team();
				teamB.setTeamId(rs.getInt(4));
				match.setTeamB(teamB);
				match.setStatus(rs.getString(5));
				match.setTossWonBy(rs.getInt(6));
				if (rs.getInt(7) == 0) {
					match.setMatchResult(null);
				} else {
					match.setMatchResult(MatchResult.values()[rs.getInt(6)-1]);
				}
				match.setTeamALineUp( fetchLineUp(match.getMatchId(), match.getTeamA().getTeamId()) );
				match.setTeamBLineUp( fetchLineUp(match.getMatchId(), match.getTeamB().getTeamId()) );
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchMatch(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return match;
	}
	
	public ArrayList<Match> fetchMatches(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Match> matchList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCHES);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Match match = new Match();
				match.setMatchId(rs.getInt(1));
				
				match.setMatchDate(rs.getDate(2));
				
				Team teamA = new Team();
				teamA.setTeamId(rs.getInt(3));
				match.setTeamA(teamA);
				
				Team teamB = new Team();
				teamB.setTeamId(rs.getInt(4));
				match.setTeamB(teamB);
				
				match.setStatus(rs.getString(5));
				
				match.setTossWonBy(rs.getInt(6));
				if (rs.getInt(7) == 0) {
					match.setMatchResult(null);
				} else {
					match.setMatchResult(MatchResult.values()[rs.getInt(6)-1]);
				}
				matchList.add(match);
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchMatches(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return matchList;
	}
	
	public LineUp fetchLineUp(int matchId, int teamId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		LineUp lineUp = new LineUp();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_LINE_UP);
			ArrayList<Integer> playerId = new ArrayList<>();
			ps.setInt(1, matchId);
			ps.setInt(2, teamId);
			rs = ps.executeQuery();
			while (rs.next()) {
				playerId.add(rs.getInt(1));
			}
			lineUp.setPlayerId(playerId);
			lineUp.setMatchId(matchId);
			lineUp.setTeamId(teamId);
		} catch (SQLException e) {
			System.out.println("Exception caught at fetchLineUp(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return lineUp;
	}
	
	public boolean searchUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_USER);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at searchUser(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}
	
	/*public boolean searchTeam(User user, int team_id) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_TEAM);
			ps.setInt(1, team_id);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at searchTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}*/
	
	/*public boolean searchPlayer(User user, int player_id) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_PLAYER);
			ps.setInt(1, player_id);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at searchPlayer(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}*/
	
	public boolean searchMatch(User user, int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_MATCH);
			ps.setInt(1, matchId);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception caught at searchMatch(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}
	
	public void deletePlayer(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.DELETE_PLAYER);
			ps.setInt(1, team.getTeamId());
			Player player = team.getPlayers().get(0);
			ps.setInt(2, player.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Exception caught at deletePlayer(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
}
