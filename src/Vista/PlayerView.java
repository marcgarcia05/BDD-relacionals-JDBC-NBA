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
        System.out.println(String.format("%-10s | %-20s | %-10s", "ID", "Nom", "Equip"));
        System.out.println("============================================");
        for (Player player : players) {
            System.out.println(String.format("%-10d | %-20s | %-10s", player.getId(), player.getName(), player.getTeamName()));
        }
    }

    public void showPlayerStats(PlayerStats player) {
        System.out.println("ESTADISTIQUES");
        System.out.println(String.format("%-12s| %-11d", "Player ID", player.getId()));
        System.out.println(String.format("%-12s| %-11s", "Nom", player.getName()));
        System.out.println(String.format("%-12s| %-11d", "GP", player.getGamesPlayed()));
        System.out.println(String.format("%-12s| %-11d", "MIN", player.getMin()));
        System.out.println(String.format("%-12s| %-11d", "FGM", player.getFgm()));
        System.out.println(String.format("%-12s| %-11d", "FGA", player.getFga()));
        System.out.println(String.format("%-12s| %-11f", "FG_PCT", player.getFgpct()));
        System.out.println(String.format("%-12s| %-11d", "FG3M", player.getFg3m()));
        System.out.println(String.format("%-12s| %-11d", "FG3A", player.getFg3a()));
        System.out.println(String.format("%-12s| %-11f", "FG3_PCT", player.getFg3pct()));
        System.out.println(String.format("%-12s| %-11d", "FTM", player.getFtm()));
        System.out.println(String.format("%-12s| %-11d", "FTA", player.getFta()));
        System.out.println(String.format("%-12s| %-11f", "FT_PCT", player.getFtpct()));
        System.out.println(String.format("%-12s| %-11d", "OREB", player.getOreb()));
        System.out.println(String.format("%-12s| %-11d", "DREB", player.getDreb()));
        System.out.println(String.format("%-12s| %-11d", "REB", player.getReb()));
        System.out.println(String.format("%-12s| %-11d", "AST", player.getAst()));
        System.out.println(String.format("%-12s| %-11d", "STL", player.getStl()));
        System.out.println(String.format("%-12s| %-11d", "BLK", player.getBlk()));
        System.out.println(String.format("%-12s| %-11d", "TOV", player.getTov()));
        System.out.println(String.format("%-12s| %-11d", "PTS", player.getPts()));
        System.out.println(String.format("%-12s| %-11d", "EFF", player.getEff()));
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

