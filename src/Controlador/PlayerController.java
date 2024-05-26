package Controlador;

import DAO.TeamDAO;
import DAO.PlayerDAO;
import Model.Player;
import Model.PlayerStats;
import Model.Team;
import Vista.MainView;
import Vista.PlayerView;
import Vista.TeamView;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlayerController {
    private Scanner scanner;

    public PlayerController() {
        this.scanner = new Scanner(System.in);
    }
    private PlayerView playerView;
    private PlayerDAO playerDAO;
    private MainView mainView;


    public PlayerController(MainView mainView) throws SQLException {
        this.playerView = new PlayerView();
        this.playerDAO = new PlayerDAO();
        this.mainView = mainView;
        this.scanner = new Scanner(System.in);
    }

    public void listPlayersByTeam() {
        String teamName = playerView.getTeamName();
        boolean exists = TeamDAO.checkTeamExists(teamName);
        if (!exists){
            System.out.println("Aquest equip no existeix!");
            MainController.start();
        }
        List<Player> players = playerDAO.listPlayersByTeam(teamName);
        playerView.showPlayersFromTeam(players);
        MainController.start();
    }

    public void showPlayerStats() {
        String playerName = playerView.getPlayerName();
        boolean exists = playerDAO.checkPlayerExists(playerName);
        if (!exists){
            System.out.println("Aquest jugador no existeix!");
            MainController.start();
        }
        PlayerStats player = playerDAO.getPlayerStats(playerName);
        playerView.showPlayerStats(player);
        MainController.start();
    }

    public void addNewPlayer() {
        String playerName = playerView.getNewPlayer();
        boolean playerExists = playerDAO.checkPlayerExists(playerName);
        if (playerExists) {
            System.out.print("Aquest jugador ja existeix vols traspasar-lo? (si | no): ");
            if (scanner == null) {
                scanner = new Scanner(System.in);
            }
            String traspasar = scanner.nextLine();
            if (Objects.equals(traspasar, "si")) {
                transferPlayer();
                return;
            } else if (Objects.equals(traspasar, "no")) {
                System.out.println("Jugador no traspasat.");
                return;
            }


        }
        String teamName = playerView.getTeamName();
        boolean teamExists = TeamDAO.checkTeamExists(teamName);
        if (!teamExists){
            System.out.println("Aquest equip no existeix!");
            MainController.start();
        }
        Team playerTeam = TeamDAO.getTeamInfo(teamName);
        Random random = new Random();
        int min = 1000000;
        int max = 9999999;
        int id = random.nextInt((max - min) + 1) + min;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        Player player = new Player(id,playerName,currentYear,currentYear,playerTeam.getCity(),playerTeam.getName(),playerTeam.getAbbreviation());
        PlayerDAO.addNewPlayer(player, playerTeam);
        PlayerDAO.addNewPlayerStats(player, playerTeam);
        MainController.start();
    }


    public void updatePlayerStats() {
        String playerName = playerView.getPlayerName();
        if (!playerDAO.checkPlayerExists(playerName)) {
            System.out.println("Aquest jugador no existeix!");
            return;
        }
        PlayerStats playerStats = PlayerDAO.getPlayerStats(playerName);
        String statName = playerView.getStatName();
        if(checkStatName(statName)) {
            int newValue = playerView.getNewStatValue();
            playerDAO.updatePlayerStats(playerStats.getName(), statName, newValue);
            System.out.println("Estadística actualitzada correctament.");
        } else {
            System.out.println("Aquesta estadística no existeix!");
        }
    }

    public void transferPlayer() {
        String playerName = playerView.getPlayerName();
        boolean playerExists = playerDAO.checkPlayerExists(playerName);
        if (!playerExists){
            System.out.println("Aquest jugador no existeix!");
            return;
        }
        String newTeam = TeamView.getNewTeam();
        Player player = playerDAO.getPlayer(playerName);
        boolean teamExists = TeamDAO.checkTeamExists(newTeam);
        if (!teamExists){
            System.out.println("Aquest equip no existeix!");
            MainController.start();
        }
        Team team = TeamDAO.getTeamInfo(newTeam);
        playerDAO.changePlayerTeam(player, team);
        playerDAO.changePlayerStatsTeam(player, team);
        System.out.println("Equip actualitzat correctament!");
        MainController.start();
    }

    public void retirePlayer() {
        String playerName = playerView.getPlayerName();
        boolean exists = playerDAO.checkPlayerExists(playerName);
        if (!exists){
            System.out.println("Aquest jugador no existeix!");
            MainController.start();
        }
        Player player = playerDAO.getPlayer(playerName);
        PlayerStats playerstats = playerDAO.getPlayerStats(playerName);
        PlayerDAO.addPlayerToHistorics(player, playerstats);
        PlayerDAO.deletePlayer(player);
        PlayerDAO.deletePlayerStats(playerstats);
        System.out.println("Jugador retirat correctament!");
        MainController.start();
    }

    public void updatePlayersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 21) {
                    String playerName = parts[0];
                    try {
                        int gp = Integer.parseInt(parts[1]);
                        int min = Integer.parseInt(parts[2]);
                        int fgm = Integer.parseInt(parts[3]);
                        int fga = Integer.parseInt(parts[4]);
                        double fgPct = Double.parseDouble(parts[5]);
                        int fg3m = Integer.parseInt(parts[6]);
                        int fg3a = Integer.parseInt(parts[7]);
                        double fg3Pct = Double.parseDouble(parts[8]);
                        int ftm = Integer.parseInt(parts[9]);
                        int fta = Integer.parseInt(parts[10]);
                        double ftPct = Double.parseDouble(parts[11]);
                        int oreb = Integer.parseInt(parts[12]);
                        int dreb = Integer.parseInt(parts[13]);
                        int reb = Integer.parseInt(parts[14]);
                        int ast = Integer.parseInt(parts[15]);
                        int stl = Integer.parseInt(parts[16]);
                        int blk = Integer.parseInt(parts[17]);
                        int tov = Integer.parseInt(parts[18]);
                        int pts = Integer.parseInt(parts[19]);
                        int eff = Integer.parseInt(parts[20]);

                        boolean updated = playerDAO.updatePlayerStats(playerName, gp, min, fgm, fga, fgPct, fg3m, fg3a, fg3Pct, ftm, fta, ftPct, oreb, dreb, reb, ast, stl, blk, tov, pts, eff);
                        if (updated) {
                            System.out.println("Les estadistiques de " + playerName + " han sigut actualitzades correctament.");
                        } else {
                            System.out.println("No s'ha trobat al jugador " + playerName);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error en les estadistiques de " + playerName + "; les estadistiques no son correctes.");
                    }
                } else {
                    System.out.println("Format de linia incorrecte: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkStatName(String statName){
        ArrayList<String> stats = new ArrayList<>();
        stats.add("GP");
        stats.add("MIN");
        stats.add("FGM");
        stats.add("FGA");
        stats.add("FG_PCT");
        stats.add("FG3M");
        stats.add("FG3A");
        stats.add("FG3_PCT");
        stats.add("FTM");
        stats.add("FTA");
        stats.add("FT_PCT");
        stats.add("OREB");
        stats.add("DREB");
        stats.add("AST");
        stats.add("STL");
        stats.add("BLK");
        stats.add("TOV");
        stats.add("PTS");
        stats.add("EFF");

        return stats.contains(statName);
    }
}




