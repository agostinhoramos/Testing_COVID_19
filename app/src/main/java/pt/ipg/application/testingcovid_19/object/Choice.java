package pt.ipg.application.testingcovid_19.object;

public class Choice {
    private long id = -1;
    private String choice = null;
    private long weight = -1;
    private String type = null;

    private long fk_question = -1;

    public Choice() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getFk_question() {
        return fk_question;
    }

    public void setFk_question(long fk_question) {
        this.fk_question = fk_question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] Values(){
        return new String[]{
                id+"", choice, weight+"", type,
                fk_question+""
        };
    }
}
