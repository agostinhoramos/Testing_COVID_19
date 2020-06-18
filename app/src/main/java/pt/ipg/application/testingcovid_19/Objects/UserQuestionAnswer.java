package pt.ipg.application.testingcovid_19.objects;

public class UserQuestionAnswer {
    private long user_id = -1;
    private long choice_id = -1;

    private String quest_answer_id;

    public UserQuestionAnswer() {
        ;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(long choice_id) {
        this.choice_id = choice_id;
    }

    public String getQuest_answer_id() {
        return quest_answer_id;
    }

    public void setQuest_answer_id(String quest_answer_id) {
        this.quest_answer_id = quest_answer_id;
    }
}
