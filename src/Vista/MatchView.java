package Vista;

import java.util.List;
import java.util.Scanner;
import Model.Match;

public class MatchView {
    private Scanner scanner;

    public MatchView() {
        this.scanner = new Scanner(System.in);
    }

    public String getTeamName() {
        System.out.print("Introdueix el nom del equip complet (ex. Boston Celtics): ");
        return scanner.nextLine();
    }

    public String getSeasonYear() {
        System.out.print("Introdueix l'any de la temporada (2018-19, 2019-20, 2020-21, 2021-22, 2022-23, 2023-24): ");
        return scanner.nextLine();
    }

    public void showMatches(List<Match> matches) {
        for (Match match : matches) {
            String team = match.getTeam();
            String opponent = match.getOpponent();
            int teamPoints = match.getTeamPoints();
            int opponentPoints = match.getOpponentPoints();

            String formattedMatch = String.format("%-25s  %-25s| %3d – %3d", team, opponent, teamPoints, opponentPoints);
            System.out.println(formattedMatch);
        }
    }

}
