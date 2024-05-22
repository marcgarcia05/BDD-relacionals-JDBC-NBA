package Model;

public class Team {
    int id;
    String city;
    String name;
    String abbreviation;
    String conference;
    int w;
    int l;
    int fundation;
    String fullname;

    //CONSTRUCTOR
    public Team(int id, String city, String name, String abbreviation, String conference, int w, int l, int fundation, String fullname) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.abbreviation = abbreviation;
        this.conference = conference;
        this.w = w;
        this.l = l;
        this.fundation = fundation;
        this.fullname = fullname;
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getConference() {
        return conference;
    }

    public int getW() {
        return w;
    }

    public int getL() {
        return l;
    }

    public int getFundation() {
        return fundation;
    }

    public String getFullname() {
        return fullname;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setL(int l) {
        this.l = l;
    }

    public void setFundation(int fundation) {
        this.fundation = fundation;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
