package Controlador;

import Vista.MainView;

import java.sql.SQLException;

public class MainController {
    private static MainView mainView;
    private static PlayerController playerController;
    private static MatchController matchController;
    private static TeamController teamController;

    public MainController() throws SQLException {
        this.mainView = new MainView();
        this.playerController = new PlayerController(mainView);
        this.matchController = new MatchController();
        this.teamController = new TeamController();
    }

    //Menu Principal
    public static void start() {
        int option;
        do {
            mainView.displayMenu();
            option = mainView.getOption();
            switch (option) {
                case 0:
                    System.out.println("Sortint...");
                    break;
                case 1:
                    playerController.listPlayersByTeam();
                    break;
                case 2:
                    playerController.showPlayerStats();
                    break;
                case 3:
                    matchController.listMatchesByTeam();
                    break;
                case 4:
                    playerController.addNewPlayer();
                    break;
                case 5:
                    playerController.transferPlayer();
                    break;
                case 6:
                    handleUpdateOption();
                    break;
                case 7:
                    playerController.updatePlayerStats();
                    break;
                case 8:
                    playerController.retirePlayer();
                    break;
                case 9:
                    teamController.changeFranchiseName();
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        } while (option != 0);
    }

    private static void handleUpdateOption() {
        int subOption = mainView.getUpdateOption();
        switch (subOption) {
            case 1:
                playerController.updatePlayersFromFile("players.txt");
                break;
            case 2:
                matchController.updateMatchesFromFile("games.txt");
                break;
            default:
                System.out.println("Opció no vàlida.");
                break;
        }
    }
}
