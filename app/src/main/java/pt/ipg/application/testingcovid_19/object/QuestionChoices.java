package pt.ipg.application.testingcovid_19.object;

public class QuestionChoices {
    private long question_id = -1;

    private long id = -1;
    private long choice = -1;
    private long weight = -1;

    public QuestionChoices() {
        ;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChoice() {
        return choice;
    }

    public void setChoice(long choice) {
        this.choice = choice;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
