package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

import pt.ipg.application.testingcovid_19.object.QuestionChoices;

public class DBTableQuestionChoices implements BaseColumns {
    public static final String TABLE_NAME = "questionChoices";

    public static final String COLUMN_CHOICE = "choice";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_FK_QUESTION = "fk_question";

    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;
    public static final String COLUMN_FULL_CHOICE = TABLE_NAME + "." + COLUMN_CHOICE;
    public static final String COLUMN_FULL_WEIGHT = TABLE_NAME + "." + COLUMN_WEIGHT;
    public static final String COLUMN_FULL_FK_QUESTION = TABLE_NAME + "." + COLUMN_FK_QUESTION;

    // QUESTION FIELD's
    public static final String COLUMN_FULL_FK_QUESTION_QUESTION = DBTableQuestion.COLUMN_FULL_QUESTION + " AS " + DBTableQuestion.COLUMN_QUESTION;

    public static final String[] ALL_COLUMN = {
            COLUMN_FULL_ID, COLUMN_FULL_CHOICE, COLUMN_FULL_WEIGHT, COLUMN_FULL_FK_QUESTION
            /*,COLUMN_FULL_FK_QUESTION_QUESTION*/
    };

    private SQLiteDatabase db;

    public DBTableQuestionChoices (SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CHOICE + " TEXT NOT NULL," +
                COLUMN_WEIGHT + " INTEGER NOT NULL,"+
                COLUMN_FK_QUESTION + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + COLUMN_FK_QUESTION + ") REFERENCES " +
                DBTableQuestion.TABLE_NAME + "("+ DBTableQuestion._ID + ")" +
                ")");
    }

    public long insert(ContentValues values) {
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (!Arrays.asList(columns).contains(COLUMN_FULL_FK_QUESTION_QUESTION)) {
            return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
        }

        String campos = TextUtils.join(",", columns);

        String sql = "SELECT " + campos;
        sql += " FROM " + TABLE_NAME;

        sql += " INNER JOIN " + DBTableUser.TABLE_NAME;
        sql += " ON " + COLUMN_FULL_FK_QUESTION + "=" + DBTableQuestion.COLUMN_FULL_ID;

        if (selection != null) {
            sql += " WHERE " + selection;
        }

        if (groupBy != null) {
            sql += " GROUP BY " + groupBy;

            if (having != null) {
                sql += " HAVING " + having;
            }
        }

        if (orderBy != null) {
            sql += " ORDER BY " + orderBy;
        }

        return db.rawQuery(sql, selectionArgs);
    }

    public ArrayList<QuestionChoices> listChoiceById(int id){
        ArrayList<QuestionChoices> list = new ArrayList<>();
        Cursor cursor = query(ALL_COLUMN, COLUMN_FK_QUESTION + " = " + id, null, null, null, null);
        int pos = 0;
        while ( pos < cursor.getCount()){
            cursor.moveToPosition(pos);

            QuestionChoices questionChoices = new QuestionChoices();
            questionChoices.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
            questionChoices.setChoice(cursor.getString(cursor.getColumnIndex(COLUMN_CHOICE)));
            questionChoices.setWeight(cursor.getInt(cursor.getColumnIndex(COLUMN_WEIGHT)));

            list.add( questionChoices );
            pos++;
        }
        return list;
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public long count(){
        Cursor cursor = query(ALL_COLUMN, null, null, null, null, null);
        return cursor.getCount();
    }
}
