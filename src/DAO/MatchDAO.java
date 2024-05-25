package DAO;

import Model.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    private Connection connection;

    {
        try {
            connection = MainDAO.DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Match> listMatchesByTeam(String teamName) {
        String query = "SELECT t1.GAME_ID, t1.TEAM_NAME AS TEAM, t1.PTS AS TEAM_POINTS, t2.TEAM_NAME AS OPPONENT, t2.PTS AS OPPONENT_POINTS FROM team_game_logs t1 JOIN team_game_logs t2 ON t1.GAME_ID = t2.GAME_ID AND t1.TEAM_ID != t2.TEAM_ID WHERE t1.TEAM_NAME = ?";
        List<Match> matches = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                matches.add(new Match(resultSet.getInt("GAME_ID"), resultSet.getString("TEAM"), resultSet.getInt("TEAM_POINTS"), resultSet.getString("OPPONENT"), resultSet.getInt("OPPONENT_POINTS")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public void updateMatchData(String gameId, String teamName, int points) {
        String query = "INSERT INTO team_game_logs (GAME_ID, TEAM_NAME, PTS) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, gameId);
            statement.setString(2, teamName);
            statement.setInt(3, points);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
