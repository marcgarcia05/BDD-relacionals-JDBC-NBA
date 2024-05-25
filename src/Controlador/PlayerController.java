package Controlador;

import DAO.TeamDAO;
import DAO.PlayerDAO;
import Model.Player;
import Model.PlayerStats;
import Model.Team;
import Vista.MainView;
import Vista.PlayerView;
import Vista.TeamView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

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
    }

    public void listPlayersByTeam() {
        String teamName = playerView.getTeamName();
        List<Player> players = playerDAO.listPlayersByTeam(teamName);
        playerView.showPlayersFromTeam(players);
    }

    public void showPlayerStats() {
        String playerName = playerView.getPlayerName();
        PlayerStats player = playerDAO.getPlayerStats(playerName);
        playerView.showPlayerStats(player);
    }

    public void addNewPlayer(){
        String playerName = playerView.getNewPlayer();
        boolean exists = playerDAO.checkPlayerExists(playerName);
        if (exists){
            System.out.print("Aquest jugador ja existeix vols traspasar-lo? (si | no): ");
            String traspasar = scanner.nextLine();
            if (Objects.equals(traspasar, "si")) {
                transferPlayer();
            } else if (Objects.equals(traspasar, "No")){
                System.out.println("Jugador no traspasat.");
            }

        }
        String teamName = playerView.getTeamName();
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
            System.out.println("El jugador no existeix.");
            return;
        }

        boolean continueEditing = true;
        while (continueEditing) {
            String statName = playerView.getStatName();
            int newValue = playerView.getNewStatValue();
            playerDAO.updatePlayerStats(playerName, statName, newValue);
            System.out.println("Estadística actualitzada correctament.");

            continueEditing = playerView.wantToUpdateMoreStats();
        }
    }

    public void transferPlayer() {
        String playerName = playerView.getPlayerName();
        boolean exists = playerDAO.checkPlayerExists(playerName);
        if (!exists){
            System.out.println("Aquest jugador no existeix!");
            return;
        }
        String newTeam = TeamView.getNewTeam();
        Player player = playerDAO.getPlayer(playerName);
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
            return;
        }
        Player player = playerDAO.getPlayer(playerName);
        PlayerStats playerstats = playerDAO.getPlayerStats(playerName);
        PlayerDAO.addPlayerToHistorics(player, playerstats);
        PlayerDAO.deletePlayer(player);
        PlayerDAO.deletePlayerStats(playerstats);
        System.out.println("Jugador retirat correctament!");
        MainController.start();
    }
}

