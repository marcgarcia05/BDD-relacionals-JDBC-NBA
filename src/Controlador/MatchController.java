package Controlador;

import DAO.MatchDAO;
import Model.Match;
import Vista.MatchView;

import java.util.List;

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
}

