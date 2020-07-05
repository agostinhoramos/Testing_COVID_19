package pt.ipg.application.testingcovid_19.object;

public class History {

    public History() {
        ;
    }

    private long user_fk = -1;
    private long id = -1;
    private String date;
    private String level;

    private String name;
    private String country;
    private String district;
    private String email;
    private String phone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
