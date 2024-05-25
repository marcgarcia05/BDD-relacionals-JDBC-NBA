package DAO;

// Exercicis 9

import Model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDAO {
    private static Connection connection;

    {
        try {
            connection = MainDAO.DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team listPlayersByTeam(String teamName) {
        String query = "SELECT TEAM_ID, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION, TEAM_CONFERENCE, W, L, MIN_YEAR, TEAMNAME FROM team_info;";
        StringBuilder playerData = new StringBuilder();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Team(resultSet.getInt("TEAM_ID"), resultSet.getString("TEAM_CITY"), resultSet.getString("TEAM_NAME"), resultSet.getString("TEAM_ABBREVIATION"),
                        resultSet.getString("TEAM_CONFERENCE"), resultSet.getInt("W"),resultSet.getInt("L") ,resultSet.getInt("MIN_YEAR"), resultSet.getString("TEAMNAME"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Team getTeamInfo(String teamName) {
        String query = "SELECT TEAM_ID, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION, TEAM_CONFERENCE, W, L, MIN_YEAR, TEAMNAME FROM team_info WHERE TEAM_NAME = ?;";
        StringBuilder playerData = new StringBuilder();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Team(resultSet.getInt("TEAM_ID"), resultSet.getString("TEAM_CITY"), resultSet.getString("TEAM_NAME"), resultSet.getString("TEAM_ABBREVIATION"),
                        resultSet.getString("TEAM_CONFERENCE"), resultSet.getInt("W"),resultSet.getInt("L") ,resultSet.getInt("MIN_YEAR"), resultSet.getString("TEAMNAME"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateCityName(String teamName, String newCityName) {
        String query = "UPDATE team_info SET TEAM_CITY = ? WHERE TEAM_CITY = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newCityName);
            statement.setString(2, teamName);
            statement.executeUpdate();
            System.out.println("Nom de la franquicia actualitzat correctament.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkTeamExists(String teamName) {
        String query = "SELECT COUNT(*) AS \"EXISTS\" FROM team_info WHERE TEAM_NAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getBoolean("EXISTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
