package pt.ipg.application.testingcovid_19.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestionChoices;
import pt.ipg.application.testingcovid_19.database.table.DBTableTest;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserQuestionAnswer;
import pt.ipg.application.testingcovid_19.object.NewQuestion;
import pt.ipg.application.testingcovid_19.object.QuestionChoices;
import pt.ipg.application.testingcovid_19.object.Test;
import pt.ipg.application.testingcovid_19.object.UserQuestionAnswer;

public class QuestionController {

    // Declare
    private static int position = 0;

    private UserQuestionAnswer obj_userQuestionAnswer;
    private Test obj_test;

    private DBTableUserQuestionAnswer tb_userQuestionAnswer;
    private DBTableTest tb_test;

    private static SQLiteDatabase Database;
    private static Cursor cursor;

    public QuestionController(SQLiteDatabase Database){
        this.Database = Database;

        obj_userQuestionAnswer = new UserQuestionAnswer();
        obj_test = new Test();

        tb_userQuestionAnswer = new DBTableUserQuestionAnswer(Database);
        tb_test = new DBTableTest(Database);
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

    public NewQuestion Next(int id_user){

        NewQuestion nQuestion = new NewQuestion();

        DBTableQuestion questionTable = new DBTableQuestion(Database);
        cursor = questionTable.query(DBTableQuestion.ALL_COLUMN, null, null, null, null, null);

        if( position >= cursor.getCount() ){
            return null;
        }

        cursor.moveToPosition(getPosition());
        String question = cursor.getString(cursor.getColumnIndex(DBTableQuestion.COLUMN_QUESTION));
        long id_question = cursor.getLong(cursor.getColumnIndex(DBTableQuestion._ID));

        DBTableQuestionChoices questionChoices = new DBTableQuestionChoices(Database);
        ArrayList<QuestionChoices> list = questionChoices.listChoiceById((int)id_question);

        // Register..
        for(int i=0; i<list.size(); i++){
            obj_userQuestionAnswer.setUser_id(id_user);
            obj_userQuestionAnswer.setChoice_id(list.get(i).getId());
            tb_userQuestionAnswer.insert(Convert.userQuestionAnswerToContentValues(obj_userQuestionAnswer));
        }

        nQuestion.setQuestion(question);
        nQuestion.setAnswer(list);
        nQuestion.setCount(position+1);

        nextPosition();
        return nQuestion;
    }

}
