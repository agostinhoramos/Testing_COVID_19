package pt.ipg.application.testingcovid_19.Objects;

public class CovidQuestion {
    private long doctor_id = -1;

    private long question_id = -1;
    private String question;

    public CovidQuestion() {
        ;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
