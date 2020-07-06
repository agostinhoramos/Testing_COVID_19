package pt.ipg.application.testingcovid_19.object;

public class Faq {
    private long id = -1;
    private String question = null;
    private String answer = null;
    private String create_at = null;

    private long fk_user = -1;
    private long fk_doctor = -1;

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
