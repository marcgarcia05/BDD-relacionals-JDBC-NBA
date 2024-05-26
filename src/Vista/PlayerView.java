package Vista;

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
        System.out.print("Introdueix el nom del equip: ");
        return scanner.nextLine();
    }

    public String getPlayerName() {
        System.out.print("Introdueix el nom complert del jugador: ");
        return scanner.nextLine();
    }

    public void showPlayersFromTeam(List<Player> players) {
        System.out.println("Id        |      Nom            |     Equip");
        for (Player player : players) {
            System.out.println(player.getId() + "   |   " + player.getName() + "    |   " + player.getTeamName());
        }
    }

    public void showPlayerStats(PlayerStats player) {
        System.out.println("ESTADISTIQUES");
        System.out.println("Player ID: " + player.getId() +
                        "\nNom: " + player.getName() +
                        "\nGP: " + player.getGamesPlayed() +
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

    public String getNewPlayer() {
        System.out.print("Introdueix el nom complet del nou jugador: ");
        return scanner.nextLine();
    }


    /* Exercici 7 */


    public String getStatName() {
        System.out.println("Variables disponibles: GP, MIN, FGM, FGA, FG_PCT, FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, STL, BLK, TOV, PTS, EFF");
        System.out.print("Introdueix el nom de la variable a modificar: ");
        return scanner.nextLine();
    }

    public int getNewStatValue() {
        System.out.print("Introdueix un nou número a la estadistica: ");
        return scanner.nextInt();
    }

}

