package Controlador;

import Vista.TeamView;
import DAO.TeamDAO;

public class TeamController {
    private TeamView teamView;
    private TeamDAO teamDAO;

    public TeamController() {
        this.teamView = new TeamView();
        this.teamDAO = new TeamDAO();
    }

    public void changeFranchiseName() {
        String teamName = teamView.getTeamName();
        String newCityName = teamView.getNewCityName();
        teamDAO.updateCityName(teamName, newCityName);
    }
}

