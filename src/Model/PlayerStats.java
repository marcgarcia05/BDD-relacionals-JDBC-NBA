package Model;

public class PlayerStats {
    int id;
    String name;
    int gamesPlayed;
    int min;
    int fgm;
    int fga;
    float fgpct;
    int fg3m;
    int fg3a;
    float fg3pct;
    int ftm;
    int fta;
    float ftpct;
    int oreb;
    int dreb;
    int reb;
    int ast;
    int stl;
    int blk;
    int tov;
    int pts;
    int eff;

    //CONSTRUCTOR
    public PlayerStats(int id, String name, int gamesPlayed, int min, int fgm, int fga, float fgpct, int fg3m, int fg3a, float fg3pct, int ftm, int fta, float ftpct, int oreb, int dreb, int reb, int ast, int stl, int blk, int tov, int pts, int eff) {
        this.id = id;
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.min = min;
        this.fgm = fgm;
        this.fga = fga;
        this.fgpct = fgpct;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.fg3pct = fg3pct;
        this.ftm = ftm;
        this.fta = fta;
        this.ftpct = ftpct;
        this.oreb = oreb;
        this.dreb = dreb;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pts = pts;
        this.eff = eff;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getMin() {
        return min;
    }

    public int getFgm() {
        return fgm;
    }

    public int getFga() {
        return fga;
    }

    public float getFgpct() {
        return fgpct;
    }

    public int getFg3m() {
        return fg3m;
    }

    public int getFg3a() {
        return fg3a;
    }

    public float getFg3pct() {
        return fg3pct;
    }

    public int getFtm() {
        return ftm;
    }

    public int getFta() {
        return fta;
    }

    public float getFtpct() {
        return ftpct;
    }

    public int getOreb() {
        return oreb;
    }

    public int getDreb() {
        return dreb;
    }

    public int getReb() {
        return reb;
    }

    public int getAst() {
        return ast;
    }

    public int getStl() {
        return stl;
    }

    public int getBlk() {
        return blk;
    }

    public int getTov() {
        return tov;
    }

    public int getPts() {
        return pts;
    }

    public int getEff() {
        return eff;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setFgm(int fgm) {
        this.fgm = fgm;
    }

    public void setFga(int fga) {
        this.fga = fga;
    }

    public void setFgpct(float fgpct) {
        this.fgpct = fgpct;
    }

    public void setFg3m(int fg3m) {
        this.fg3m = fg3m;
    }

    public void setFg3a(int fg3a) {
        this.fg3a = fg3a;
    }

    public void setFg3pct(float fg3pct) {
        this.fg3pct = fg3pct;
    }

    public void setFtm(int ftm) {
        this.ftm = ftm;
    }

    public void setFta(int fta) {
        this.fta = fta;
    }

    public void setFtpct(float ftpct) {
        this.ftpct = ftpct;
    }

    public void setOreb(int oreb) {
        this.oreb = oreb;
    }

    public void setDreb(int dreb) {
        this.dreb = dreb;
    }

    public void setReb(int reb) {
        this.reb = reb;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public void setStl(int stl) {
        this.stl = stl;
    }

    public void setBlk(int blk) {
        this.blk = blk;
    }

    public void setTov(int tov) {
        this.tov = tov;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setEff(int eff) {
        this.eff = eff;
    }
}
