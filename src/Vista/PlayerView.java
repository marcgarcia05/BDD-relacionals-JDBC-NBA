package Vista;

import Model.Match;
import Model.Player;
import Model.PlayerStats;

import java.util.List;
import java.util.Scanner;

public class PlayerView {
    private Scanner scanner;

    public PlayerView() {
        this.scanner = new Scanner(System.in);
    }

    public String getTeamName() {
        System.out.print("Introduce el nombre del equipo: ");
        return scanner.nextLine();
    }

    public String getPlayerName() {
        System.out.print("Introduce el nombre completo del jugador: ");
        return scanner.nextLine();
    }

    public void showPlayersFromTeam(List<Player> players) {
        System.out.println("Id        |      Nom            |     Team");
        for (Player player : players) {
            System.out.println(player.getId() + "   |   " + player.getName() + "    |   " + player.getTeamName());
        }
    }

    public void showPlayerStats(PlayerStats player) {
        System.out.println("PLAYER STATS");
        System.out.println("Player ID: " + player.getId() +
                        "\nName: " + player.getName() +
                        "\nGames Played: " + player.getGamesPlayed() +
                        "\nMIN: " + player.getMin() +
                        "\nFGM: " + player.getFgm() +
                        "\nFGA: " + player.getFga() +
                        "\nFG_PCT: " + player.getFgpct() +
                        "\nFG3M: " + player.getFg3m() +
                        "\nFG3A: " + player.getFg3a() +
                        "\nFG3_PCT: " + player.getFg3pct() +
                        "\nFTM: " + player.getFtm() +
                        "\nFTA: " + player.getFta() +
                        "\nFT_PCT: " + player.getFtpct() +
                        "\nOREB: " + player.getOreb() +
                        "\nDREB: " + player.getDreb() +
                        "\nREB: " + player.getReb() +
                        "\nAST: " + player.getAst() +
                        "\nSTL: " + player.getStl() +
                        "\nBLK: " + player.getBlk() +
                        "\nTOV: " + player.getTov() +
                        "\nPTS: " + player.getPts() +
                        "\nEFF: " + player.getEff()

        );
    }
}

