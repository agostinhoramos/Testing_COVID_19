package pt.ipg.application.testingcovid_19.object;

public class Avatar {
    private long user_fk;

    private long id;
    private String url;
    private String created_at;
    private String updated_at;

    public Avatar(){}

    public long getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(long user_fk) {
        this.user_fk = user_fk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
