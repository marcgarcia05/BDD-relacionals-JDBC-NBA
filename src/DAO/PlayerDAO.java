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

    public static PlayerStats getPlayerStats(String playerName) {
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

    public static void addPlayerToHistorics(Player player, PlayerStats playerstats) {
        String query = "INSERT INTO historics (PLAYER_ID, PLAYER, TEAM, GP, MIN, FGM, FGA, FG_PCT, FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, STL, BLK, TOV, PTS, EFF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getTeamName());
            statement.setInt(4, playerstats.getGamesPlayed());
            statement.setInt(5, playerstats.getMin());
            statement.setInt(6, playerstats.getFgm());
            statement.setInt(7, playerstats.getFga());
            statement.setFloat(8, playerstats.getFgpct());
            statement.setInt(9, playerstats.getFg3m());
            statement.setInt(10, playerstats.getFg3a());
            statement.setFloat(11, playerstats.getFg3pct());
            statement.setInt(12, playerstats.getFtm());
            statement.setInt(13, playerstats.getFta());
            statement.setFloat(14, playerstats.getFtpct());
            statement.setInt(15, playerstats.getOreb());
            statement.setInt(16, playerstats.getDreb());
            statement.setInt(17, playerstats.getReb());
            statement.setInt(18, playerstats.getAst());
            statement.setInt(19, playerstats.getStl());
            statement.setInt(20, playerstats.getBlk());
            statement.setInt(21, playerstats.getTov());
            statement.setInt(22, playerstats.getPts());
            statement.setInt(23, playerstats.getEff());
            int rowsAffected = statement.executeUpdate();
            System.out.println("Files insertades correctament: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayer(Player player) {
        String query = "DELETE FROM players WHERE DISPLAY_FIRST_LAST = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayerStats(PlayerStats player) {
        String query = "DELETE FROM player_stats WHERE PLAYER = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean updatePlayerStats(String playerName, int gp, int min, int fgm, int fga, double fgPct, int fg3m, int fg3a, double fg3Pct, int ftm, int fta, double ftPct, int oreb, int dreb, int reb, int ast, int stl, int blk, int tov, int pts, int eff) {
        String query = "SELECT * FROM player_stats WHERE PLAYER = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int existingGp = resultSet.getInt("GP");
                int existingMin = resultSet.getInt("MIN");
                int existingFgm = resultSet.getInt("FGM");
                int existingFga = resultSet.getInt("FGA");
                double existingFgPct = resultSet.getDouble("FG_PCT");
                int existingFg3m = resultSet.getInt("FG3M");
                int existingFg3a = resultSet.getInt("FG3A");
                double existingFg3Pct = resultSet.getDouble("FG3_PCT");
                int existingFtm = resultSet.getInt("FTM");
                int existingFta = resultSet.getInt("FTA");
                double existingFtPct = resultSet.getDouble("FT_PCT");
                int existingOreb = resultSet.getInt("OREB");
                int existingDreb = resultSet.getInt("DREB");
                int existingReb = resultSet.getInt("REB");
                int existingAst = resultSet.getInt("AST");
                int existingStl = resultSet.getInt("STL");
                int existingBlk = resultSet.getInt("BLK");
                int existingTov = resultSet.getInt("TOV");
                int existingPts = resultSet.getInt("PTS");
                int existingEff = resultSet.getInt("EFF");

                int newGp = existingGp + gp;
                int newMin = existingMin + min;
                int newFgm = existingFgm + fgm;
                int newFga = existingFga + fga;
                double newFgPct = ((existingFgm + fgm) / (double) (existingFga + fga));
                int newFg3m = existingFg3m + fg3m;
                int newFg3a = existingFg3a + fg3a;
                double newFg3Pct = ((existingFg3m + fg3m) / (double) (existingFg3a + fg3a));
                int newFtm = existingFtm + ftm;
                int newFta = existingFta + fta;
                double newFtPct = ((existingFtm + ftm) / (double) (existingFta + fta));
                int newOreb = existingOreb + oreb;
                int newDreb = existingDreb + dreb;
                int newReb = existingReb + reb;
                int newAst = existingAst + ast;
                int newStl = existingStl + stl;
                int newBlk = existingBlk + blk;
                int newTov = existingTov + tov;
                int newPts = existingPts + pts;
                int newEff = existingEff + eff;

                String updateQuery = "UPDATE player_stats SET GP = ?, MIN = ?, FGM = ?, FGA = ?, FG_PCT = ?, FG3M = ?, FG3A = ?, FG3_PCT = ?, FTM = ?, FTA = ?, FT_PCT = ?, OREB = ?, DREB = ?, REB = ?, AST = ?, STL = ?, BLK = ?, TOV = ?, PTS = ?, EFF = ? WHERE PLAYER = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, newGp);
                    updateStatement.setInt(2, newMin);
                    updateStatement.setInt(3, newFgm);
                    updateStatement.setInt(4, newFga);
                    updateStatement.setDouble(5, newFgPct);
                    updateStatement.setInt(6, newFg3m);
                    updateStatement.setInt(7, newFg3a);
                    updateStatement.setDouble(8, newFg3Pct);
                    updateStatement.setInt(9, newFtm);
                    updateStatement.setInt(10, newFta);
                    updateStatement.setDouble(11, newFtPct);
                    updateStatement.setInt(12, newOreb);
                    updateStatement.setInt(13, newDreb);
                    updateStatement.setInt(14, newReb);
                    updateStatement.setInt(15, newAst);
                    updateStatement.setInt(16, newStl);
                    updateStatement.setInt(17, newBlk);
                    updateStatement.setInt(18, newTov);
                    updateStatement.setInt(19, newPts);
                    updateStatement.setInt(20, newEff);
                    updateStatement.setString(21, playerName);
                    updateStatement.executeUpdate();
                    return true; // Retornarà true si s'ha fet el l'UPDATE correctament
                }
            } else {
                return false; // Retornarà FALSE si no ha trobat el jugador
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retornarà FALSE si ha hagut un problema mentres es modificaven les estadístiques
        }
    }
}

