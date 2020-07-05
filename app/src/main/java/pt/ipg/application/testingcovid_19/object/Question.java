package pt.ipg.application.testingcovid_19.object;

public class Question {
    private long id;
    private String question;
    private long fk_doctor;

    public Question(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getFk_doctor() {
        return fk_doctor;
    }

    public void setFk_doctor(long fk_doctor) {
        this.fk_doctor = fk_doctor;
    }

    public String[] Values(){
        return new String[]{
                getId() +"", getQuestion(), getFk_doctor() +""
        };
    }
}