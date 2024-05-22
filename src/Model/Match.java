package Model;

public class Match {
    int game_id;
    String team;
    int teamPoints;
    String opponent;
    int opponentPoints;

    //CONSTRUCTOR
    public Match(int game_id, String team, int teamPoints, String opponent, int opponentPoints) {
        this.game_id = game_id;
        this.team = team;
        this.teamPoints = teamPoints;
        this.opponent = opponent;
        this.opponentPoints = opponentPoints;
    }

    //GETTERS
    public int getGame_id() {
        return game_id;
    }

    public String getTeam() {
        return team;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

    public String getOpponent() {
        return opponent;
    }

    public int getOpponentPoints() {
        return opponentPoints;
    }

    //SETTERS
    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setTeamPoints(int teamPoints) {
        this.teamPoints = teamPoints;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public void setOpponentPoints(int opponentPoints) {
        this.opponentPoints = opponentPoints;
    }
}
