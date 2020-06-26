package pt.ipg.application.testingcovid_19.object;

public class Question {
    private int numAsk;
    private String ask;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public int getNumAsk() {
        return numAsk;
    }

    public void setNumAsk(int numAsk) {
        this.numAsk = numAsk;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Question(int numAsk, String ask, String option1, String option2, String option3, String option4) {
        this.numAsk = numAsk;
        this.ask = ask;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
