package pt.ipg.application.testingcovid_19.data;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableFAQs;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestionChoices;
import pt.ipg.application.testingcovid_19.database.table.DBTableTest;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserQuestionAnswer;
import pt.ipg.application.testingcovid_19.object.FAQs;
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.QuestionChoices;
import pt.ipg.application.testingcovid_19.object.Test;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.object.UserQuestionAnswer;

public class Seed {

    private static SQLiteDatabase Database;

    // Declare all table here
    private DBTableDoctor tb_doctor;
    private DBTableQuestion tb_question;
    private DBTableQuestionChoices tb_questionChoices;
    private DBTableUserQuestionAnswer tb_userQuestionAnswer;
    private DBTableTest tb_test;
    private DBTableUser tb_user;
    private DBTableFAQs tb_faqs;

    // Declare all object here
    private Doctor obj_doctor;
    private Question obj_question;
    private QuestionChoices obj_questionChoices;
    private UserQuestionAnswer obj_userQuestionAnswer;
    private Test obj_test;
    private User obj_user;
    private FAQs obj_faqs;

    // Declare all id's here
    private ArrayList<Integer> id_doctor;
    private ArrayList<Integer> id_question;
    private ArrayList<Integer> id_questionChoices;
    private ArrayList<Integer> id_userQuestionAnswer;
    private ArrayList<Integer> id_test;
    private ArrayList<Integer> id_user;
    private ArrayList<Integer> id_faqs;

    public Seed(SQLiteDatabase Database){
        this.Database = Database;

        // Initialize tables here
        tb_doctor = new DBTableDoctor(Database);
        tb_question = new DBTableQuestion(Database);
        tb_questionChoices = new DBTableQuestionChoices(Database);
        tb_userQuestionAnswer = new DBTableUserQuestionAnswer(Database);
        tb_test = new DBTableTest(Database);
        tb_user = new DBTableUser(Database);
        tb_faqs = new DBTableFAQs(Database);

        // Initialize objects here
        obj_doctor = new Doctor();
        obj_question = new Question();
        obj_questionChoices = new QuestionChoices();
        obj_userQuestionAnswer = new UserQuestionAnswer();
        obj_test = new Test();
        obj_user = new User();
        obj_faqs = new FAQs();

        // Initialize id's here
        id_doctor = new ArrayList<>();
        id_question = new ArrayList<>();
        id_questionChoices = new ArrayList<>();
        id_userQuestionAnswer = new ArrayList<>();
        id_test = new ArrayList<>();
        id_user = new ArrayList<>();
        id_faqs = new ArrayList<>();
    }

    public void load(){

        table_doctor();
        table_question();
        table_questionChoices();
        table_user();
        //table_userQuestionAnswer();
        //table_test();
        table_faqs();

        System.out.println("All data has been successfully loaded into seeData");
    }

    public void table_doctor () {

        //Do not continue if there is something in the database..
        if( tb_doctor.count() > 0 ){
            return;
        }

        obj_doctor.setName("Marcos Paulo");
        obj_doctor.setTIN("230934900");
        obj_doctor.setEmail("marcospaulo@gmail.com");
        obj_doctor.setPhone("+351-965-554-888");
        obj_doctor.setPassword("kpl0vvdm");
        obj_doctor.setConfirmed("1");
        id_doctor.add((int) tb_doctor.insert(Convert.doctorToContentValues(obj_doctor)));

        obj_doctor.setName("Patricia Dias");
        obj_doctor.setTIN("231491662");
        obj_doctor.setEmail("patriciadias@gmail.com");
        obj_doctor.setPhone("+351-935-555-521");
        obj_doctor.setPassword("roda0viva");
        obj_doctor.setConfirmed("0");
        id_doctor.add((int) tb_doctor.insert(Convert.doctorToContentValues(obj_doctor)));

        obj_doctor.setName("Lucas Santos");
        obj_doctor.setTIN("214699986");
        obj_doctor.setEmail("lucassantos@gmail.com");
        obj_doctor.setPhone("+351-921-555-982");
        obj_doctor.setPassword("banana123");
        obj_doctor.setConfirmed("1");
        id_doctor.add((int) tb_doctor.insert(Convert.doctorToContentValues(obj_doctor)));

    }

    public void table_question() {
        //Do not continue if there is something in the database..
        if( tb_question.count() > 0 ){
            return;
        }

        obj_question.setDoctor_id(id_doctor.get(0));
        obj_question.setQuestion("Question number 1?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));

        obj_question.setDoctor_id(id_doctor.get(0));
        obj_question.setQuestion("Question number 2?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));

        obj_question.setDoctor_id(id_doctor.get(1));
        obj_question.setQuestion("Question number 3?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));

        obj_question.setDoctor_id(id_doctor.get(0));
        obj_question.setQuestion("Question number 4?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));

        obj_question.setDoctor_id(id_doctor.get(1));
        obj_question.setQuestion("Question number 5?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));

        obj_question.setDoctor_id(id_doctor.get(2));
        obj_question.setQuestion("Question number 6?");
        id_question.add((int) tb_question.insert(Convert.questionToContentValues(obj_question)));
    }

    public void table_questionChoices() {
        //Do not continue if there is something in the database..
        if( tb_questionChoices.count() > 0 ){
            return;
        }

        obj_questionChoices.setQuestion_id(id_question.get(0));
        obj_questionChoices.setChoice("Choice 1"); // 0..2
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(0));
        obj_questionChoices.setChoice("Choice 2"); // 0..2
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(0));
        obj_questionChoices.setChoice("Choice 3"); // 0..2
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));


        obj_questionChoices.setQuestion_id(id_question.get(1));
        obj_questionChoices.setChoice("Choice 1"); // 0..4
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(1));
        obj_questionChoices.setChoice("Choice 2"); // 0..4
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(1));
        obj_questionChoices.setChoice("Choice 3"); // 0..4
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(1));
        obj_questionChoices.setChoice("Choice 4"); // 0..4
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(1));
        obj_questionChoices.setChoice("Choice 5"); // 0..4
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));


        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 1"); // 0..5
        obj_questionChoices.setWeight(3); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 2"); // 0..5
        obj_questionChoices.setWeight(2); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 3"); // 0..5
        obj_questionChoices.setWeight(4); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 4"); // 0..5
        obj_questionChoices.setWeight(1); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 5"); // 0..5
        obj_questionChoices.setWeight(0); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(2));
        obj_questionChoices.setChoice("Choice 6"); // 0..5
        obj_questionChoices.setWeight(2); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));


        obj_questionChoices.setQuestion_id(id_question.get(3));
        obj_questionChoices.setChoice("Choice 1"); // 0..2
        obj_questionChoices.setWeight(3); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(3));
        obj_questionChoices.setChoice("Choice 2"); // 0..2
        obj_questionChoices.setWeight(5); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(3));
        obj_questionChoices.setChoice("Choice 3"); // 0..2
        obj_questionChoices.setWeight(2); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));


        obj_questionChoices.setQuestion_id(id_question.get(4));
        obj_questionChoices.setChoice("Choice 1"); // 0..1
        obj_questionChoices.setWeight(3); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(4));
        obj_questionChoices.setChoice("Choice 2"); // 0..1
        obj_questionChoices.setWeight(5); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));


        obj_questionChoices.setQuestion_id(id_question.get(5));
        obj_questionChoices.setChoice("Choice 1"); // 0..2
        obj_questionChoices.setWeight(3); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(5));
        obj_questionChoices.setChoice("Choice 2"); // 0..2
        obj_questionChoices.setWeight(2); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));

        obj_questionChoices.setQuestion_id(id_question.get(5));
        obj_questionChoices.setChoice("Choice 3"); // 0..2
        obj_questionChoices.setWeight(1); // 0..5
        id_questionChoices.add((int) tb_questionChoices.insert(Convert.questionChoicesToContentValues(obj_questionChoices)));
    }

    public void table_user() {
        //Do not continue if there is something in the database..
        if( tb_user.count() > 0 ){
            return;
        }

        obj_user.setName("Agostinho Ramos");
        obj_user.setGender("M");
        obj_user.setTIN("287273962");
        obj_user.setEmail("agostinhopina095@gmail.com");
        obj_user.setPhone("+351-934-927-329");
        obj_user.setBirthday("26/03/1995");
        obj_user.setDistrict("Guarda");
        obj_user.setCountry("Portugal");
        id_user.add((int) tb_user.insert(Convert.userToContentValues(obj_user)));
    }

    public void table_userQuestionAnswer() {
        //Do not continue if there is something in the database..
        if( tb_userQuestionAnswer.count() > 0 ){
            return;
        }

        //obj_userQuestionAnswer.set("");
        //obj_userQuestionAnswer.set("");
        //id_userQuestionAnswer.add((int) tb_userQuestionAnswer.insert(Convert.userQuestionAnswerToContentValues(obj_userQuestionAnswer)));
    }

    public void table_test() {
        //Do not continue if there is something in the database..
        if( tb_test.count() > 0 ){
            return;
        }

        //obj_test.set("");
        //obj_test.set("");
        //obj_test.set("");
        //id_test.add((int) tb_test.insert(Convert.testToContentValues(obj_test)));
    }

    public void table_faqs() {
        //Do not continue if there is something in the database..
        if( tb_faqs.count() > 0 ){
            return;
        }

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(0));
        obj_faqs.setQuestion("Faq Question 1");
        obj_faqs.setAnswer("Faq Answer 1");
        obj_faqs.setDate("12/03/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(0));
        obj_faqs.setQuestion("Faq Question 2");
        obj_faqs.setAnswer("Faq Answer 2");
        obj_faqs.setDate("12/02/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(1));
        obj_faqs.setQuestion("Faq Question 3");
        obj_faqs.setAnswer("Faq Answer 3");
        obj_faqs.setDate("03/06/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(0));
        obj_faqs.setQuestion("Faq Question 4");
        obj_faqs.setAnswer("Faq Answer 4");
        obj_faqs.setDate("02/03/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(2));
        obj_faqs.setQuestion("Faq Question 5");
        obj_faqs.setAnswer("Faq Answer 5");
        obj_faqs.setDate("24/03/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));

        obj_faqs.setUser_id(id_user.get(0));
        obj_faqs.setDoctor_id(id_doctor.get(1));
        obj_faqs.setQuestion("Faq Question 6");
        obj_faqs.setAnswer("Faq Answer 6");
        obj_faqs.setDate("13/05/2020");
        id_faqs.add((int) tb_faqs.insert(Convert.faqsToContentValues(obj_faqs)));
    }
}