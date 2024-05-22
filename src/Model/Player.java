package Model;

public class Player {

    int id;
    String name;
    int fromYear;
    int toYear;
    String teamCity;
    String teamName;
    String teamAbbreviation;

    //CONTRUCTOR
    public Player(int id, String name, int fromYear, int toYear, String teamCity, String teamName, String teamAbbreviation) {
        this.id = id;
        this.name = name;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.teamCity = teamCity;
        this.teamName = teamName;
        this.teamAbbreviation = teamAbbreviation;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

}
