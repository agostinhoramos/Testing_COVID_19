package pt.ipg.application.testingcovid_19.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class QuestionController {

    private int position = 0;
    private SQLiteDatabase Database;
    private Cursor cursor;

    public QuestionController(SQLiteDatabase Database){
        this.Database = Database;
    }

    public ArrayList Next(){

        ArrayList List = new ArrayList<String>();

/*
        DBTableSequenceQuestions table = new DBTableSequenceQuestions(Database);
        cursor = table.query(DBTableSequenceQuestions.ALL_COLUMN, null, null, null, null, null);
        int register = cursor.getCount();

        cursor.moveToPosition(position);
        String ask = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ASK));
        String r1 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS1));
        String r2 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS2));
        String r3 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS3));
        String r4 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS4));
*/
        return List;
    }

}
