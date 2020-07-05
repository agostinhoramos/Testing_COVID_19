package pt.ipg.application.testingcovid_19.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.object.MultipleChoice;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.UserChoice;

public class QuestionController {

    // Declare
    private static int position = 0;

    private UserChoice obj_userChoice;
    private History obj_history;

    private DBTableUserChoice tb_userQuestionAnswer;
    private DBTableHistory tb_test;

    private static SQLiteDatabase Database;
    private static Cursor cursor;

    public QuestionController(SQLiteDatabase Database){
        this.Database = Database;

        obj_userChoice = new UserChoice();
        obj_history = new History();

        tb_userQuestionAnswer = new DBTableUserChoice(Database);
        tb_test = new DBTableHistory(Database);
    }

    public void clear(){
        position = 0;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return this.position;
    }

    public void nextPosition(){
        position++;
    }

    public MultipleChoice Next(int id_user){

        MultipleChoice nQuestion = new MultipleChoice();

        DBTableQuestion questionTable = new DBTableQuestion(Database);
        cursor = questionTable.query(DBTableQuestion.ALL_COLUMN, null, null, null, null, null);

        if( position >= cursor.getCount() ){
            return null;
        }

        cursor.moveToPosition(getPosition());
        String question = cursor.getString(cursor.getColumnIndex(DBTableQuestion.COLUMN_QUESTION));
        long id_question = cursor.getLong(cursor.getColumnIndex(DBTableQuestion._ID));

        DBTableChoice questionChoices = new DBTableChoice(Database);
        ArrayList<Choice> list = questionChoices.listChoiceById((int)id_question);

        // Register..
        for(int i=0; i<list.size(); i++){
            obj_userChoice.setUser_id(id_user);
            obj_userChoice.setChoice_id(list.get(i).getId());
            tb_userQuestionAnswer.insert(Convert.userQuestionAnswerToContentValues(obj_userChoice));
        }

        nQuestion.setQuestion(question);
        nQuestion.setAnswer(list);
        nQuestion.setCount(position+1);

        nextPosition();
        return nQuestion;
    }

}
