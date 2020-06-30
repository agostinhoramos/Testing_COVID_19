package pt.ipg.application.testingcovid_19.object;

import java.util.ArrayList;

public class NewQuestion {
    private String question;
    private int count;
    private ArrayList<QuestionChoices> answer;

    public NewQuestion(){  }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<QuestionChoices> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<QuestionChoices> answer) {
        this.answer = answer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
