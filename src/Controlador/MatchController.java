package Controlador;

import DAO.MatchDAO;
import DAO.TeamDAO;
import Model.Match;
import Vista.MatchView;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MatchController {
    private MatchView matchView;
    private MatchDAO matchDAO;

    public MatchController() {
        this.matchView = new MatchView();
        this.matchDAO = new MatchDAO();
    }

    public void listMatchesByTeam() {
        String teamName = matchView.getTeamName();
        List<Match> matches = matchDAO.listMatchesByTeam(teamName);
        matchView.showMatches(matches);
    }

    public void updateMatchesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    String gameId = parts[0];
                    String team1 = parts[1];
                    int points1 = Integer.parseInt(parts[2]);
                    String team2 = parts[3];
                    int points2 = Integer.parseInt(parts[4]);

                    matchDAO.updateMatchData(gameId, team1, points1);
                    matchDAO.updateMatchData(gameId, team2, points2);
                }
            }
            System.out.println("Partits afegits correctament");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

