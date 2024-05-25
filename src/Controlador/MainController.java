package Controlador;

import Vista.MainView;
import Controlador.TeamController;

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

    public static void start() {
        int option;
        do {
            mainView.displayMenu();
            option = mainView.getOption();
            switch (option) {
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    playerController.listPlayersByTeam();
                    break;
                case 2:
                    playerController.showPlayerStats();
                    break;
                case 3:
                    matchController.listMatchesByTeam(); // Opció 3
                    break;
                case 4:
                    playerController.addNewPlayer();
                    break;
                case 5:
                    playerController.transferPlayer();
                    break;
                case 6:
                    matchController.updateMatchesFromFile("games.txt");
                    break;
                case 7:
                    playerController.updatePlayerStats();
                    break;
                case 8:
                    playerController.retirePlayer();
                case 9:
                    teamController.changeFranchiseName();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (option != 0);
    }
}
