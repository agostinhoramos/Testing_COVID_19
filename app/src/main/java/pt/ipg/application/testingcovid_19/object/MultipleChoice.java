package pt.ipg.application.testingcovid_19.object;

import java.util.ArrayList;

public class MultipleChoice {
    private String question;
    private int count;
    private ArrayList<Choice> answer;

    public MultipleChoice(){}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Choice> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Choice> answer) {
        this.answer = answer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
