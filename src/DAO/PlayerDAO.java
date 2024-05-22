package DAO;

// Exercicis: 1, 2, 4, 5, 6, 7, 8

import Model.Match;
import Model.Player;
import Model.PlayerStats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;

    {
        try {
            connection = MainDAO.DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Player> listPlayersByTeam(String teamName) {
        String query = "SELECT PERSON_ID, DISPLAY_FIRST_LAST, FROM_YEAR, TO_YEAR, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION FROM players WHERE TEAM_NAME = ?";
        List<Player> players = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                players.add(new Player(resultSet.getInt("PERSON_ID"),resultSet.getString("DISPLAY_FIRST_LAST"),resultSet.getInt("FROM_YEAR"),resultSet.getInt("TO_YEAR"),resultSet.getString("TEAM_CITY"),resultSet.getString("TEAM_NAME"),resultSet.getString("TEAM_ABBREVIATION")));
            }
        } catch (SQLException e) {
            e.printStackTrace();


        }
        return players;
    }

    public PlayerStats showPlayerStats(String playerName) {
        String query = "SELECT PLAYER_ID, PLAYER, GP, MIN, FGM, FGA, FG_PCT, FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, STL, BLK, TOV, PTS, EFF FROM player_stats WHERE PLAYER = ?";
        PlayerStats p = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                p = new PlayerStats(resultSet.getInt("PLAYER_ID"),resultSet.getString("PLAYER"),resultSet.getInt("GP"),
                        resultSet.getInt("MIN"), resultSet.getInt("FGM"),resultSet.getInt("FGA"),resultSet.getInt("FG_PCT"),
                        resultSet.getInt("FG3M"),resultSet.getInt("FG3A"),resultSet.getInt("FG3_PCT"), resultSet.getInt("FTM"),
                        resultSet.getInt("FTA"),resultSet.getInt("FT_PCT"),resultSet.getInt("OREB"),resultSet.getInt("DREB"),
                        resultSet.getInt("REB"),resultSet.getInt("AST"),resultSet.getInt("STL"),resultSet.getInt("BLK"),
                        resultSet.getInt("TOV"),resultSet.getInt("PTS"),resultSet.getInt("EFF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}

