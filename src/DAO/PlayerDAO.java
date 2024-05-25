package DAO;

// Exercicis: 1, 2, 4, 5, 6, 7, 8

import Model.Player;
import Model.PlayerStats;
import Model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private static Connection connection;

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
                        resultSet.getInt("MIN"), resultSet.getInt("FGM"),resultSet.getInt("FGA"),resultSet.getFloat("FG_PCT"),
                        resultSet.getInt("FG3M"),resultSet.getInt("FG3A"),resultSet.getFloat("FG3_PCT"), resultSet.getInt("FTM"),
                        resultSet.getInt("FTA"),resultSet.getFloat("FT_PCT"),resultSet.getInt("OREB"),resultSet.getInt("DREB"),
                        resultSet.getInt("REB"),resultSet.getInt("AST"),resultSet.getInt("STL"),resultSet.getInt("BLK"),
                        resultSet.getInt("TOV"),resultSet.getInt("PTS"),resultSet.getInt("EFF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public boolean checkPlayerExists(String playerName) {
        String query = "SELECT COUNT(*) AS \"EXISTS\" FROM players WHERE DISPLAY_FIRST_LAST=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getBoolean("EXISTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addNewPlayer(Player player, Team team) {
        String query = "INSERT INTO players (PERSON_ID, DISPLAY_LAST_COMMA_FIRST, DISPLAY_FIRST_LAST, ROSTERSTATUS, FROM_YEAR, TO_YEAR, PLAYERCODE, TEAM_ID, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION, TEAM_CODE, GAMES_PLAYED_FLAG, OTHERLEAGUE_EXPERIENCE_CH) VALUES (?, ?, ?, 1, ?, ?, ?, ?, ?, ?, ?, ?, 'Y', '00');\n";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getName());
            statement.setInt(4, player.getFromYear());
            statement.setInt(5, player.getToYear());
            statement.setString(6, player.getName());
            statement.setInt(7, team.getId());
            statement.setString(8, team.getCity());
            statement.setString(9, team.getName());
            statement.setString(10, team.getAbbreviation());
            statement.setString(11, team.getName());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Files insertades correctament: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewPlayerStats(Player player, Team team) {
        String query = "INSERT INTO player_stats (PLAYER_ID, RANK, PLAYER, TEAM_ID, TEAM, GP, MIN, FGM, FGA, FG_PCT, FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, STL, BLK, TOV, PTS, EFF) VALUES (?, 0, ?, ?, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);\n";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setInt(3, team.getId());
            statement.setString(4, team.getAbbreviation());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Files insertades correctament: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayerStats(String playerName, String statName, int newValue) {
        String query = "UPDATE player_stats SET " + statName + " = ? WHERE PLAYER = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newValue);
            statement.setString(2, playerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer(String playerName) {
        String query = "SELECT PERSON_ID, DISPLAY_FIRST_LAST, FROM_YEAR, TO_YEAR, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION FROM players WHERE DISPLAY_FIRST_LAST = ?";
        Player player = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                player = new Player(resultSet.getInt("PERSON_ID"),resultSet.getString("DISPLAY_FIRST_LAST"),resultSet.getInt("FROM_YEAR"),resultSet.getInt("TO_YEAR"),resultSet.getString("TEAM_CITY"),resultSet.getString("TEAM_NAME"),resultSet.getString("TEAM_ABBREVIATION"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public void changePlayerTeam(Player player, Team team){
        String query = "UPDATE players SET TEAM_ID = ?, TEAM_CITY = ?, TEAM_NAME = ?, TEAM_ABBREVIATION = ?, TEAM_CODE = ? WHERE DISPLAY_FIRST_LAST = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, team.getId());
            statement.setString(2, team.getCity());
            statement.setString(3, team.getName());
            statement.setString(4, team.getAbbreviation());
            statement.setString(5, team.getName());
            statement.setString(6, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePlayerStatsTeam(Player player, Team team){
        String query = "UPDATE player_stats SET TEAM_ID = ?, TEAM = ? WHERE PLAYER = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, team.getId());
            statement.setString(2, team.getAbbreviation());
            statement.setString(3, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

