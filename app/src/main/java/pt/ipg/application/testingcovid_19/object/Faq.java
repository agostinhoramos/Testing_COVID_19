package pt.ipg.application.testingcovid_19.object;

public class Faq {
    private long id;
    private String question;
    private String answer;
    private String create_at;

    private long fk_user;
    private long fk_doctor;

    public Faq() {}


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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
    }

    public long getFk_doctor() {
        return fk_doctor;
    }

    public void setFk_doctor(long fk_doctor) {
        this.fk_doctor = fk_doctor;
    }

    public String[] Values(){
        return new String[]{
                id+"", question, answer,
                create_at, fk_user+"", fk_doctor+""
        };
    }
}
