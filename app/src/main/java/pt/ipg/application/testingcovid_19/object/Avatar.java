package pt.ipg.application.testingcovid_19.object;

public class Avatar {

    private long id;
    private String url;
    private String created_at;
    private String updated_at;

    private long fk_user;

    public Avatar(){}


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

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
    }

    public String[] Values(){
        return new String[]{
                id+"", url, created_at,
                updated_at, fk_user+""
        };
    }
}
