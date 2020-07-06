package pt.ipg.application.testingcovid_19.object;

public class History {
    private long id = -1;
    private String date = null;
    private String level = null;

    private long fk_user = -1;

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
