package Controlador;

import Model.Match;
import Model.Player;
import Model.PlayerStats;
import Vista.PlayerView;
import DAO.PlayerDAO;
import Vista.MainView;

import java.sql.SQLException;
import java.util.List;

public class PlayerController {
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
        PlayerStats player = playerDAO.showPlayerStats(playerName);
        playerView.showPlayerStats(player);
    }
}

