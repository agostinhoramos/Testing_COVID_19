package pt.ipg.application.testingcovid_19.object;

public class History {
    private long id;
    private String date;
    private String level;

    private long fk_user;

    public History() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
    }

    public String[] Values(){
        return new String[]{
                id+"", date,
                level, fk_user+""
        };
    }
}
