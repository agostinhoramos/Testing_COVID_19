package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;

import java.util.Arrays;

import pt.ipg.application.testingcovid_19.database.remote.RemoteDB;

import static pt.ipg.application.testingcovid_19.database.Convert.ParseInsertSQL;
import static pt.ipg.application.testingcovid_19.database.Convert.contentValuesToDoctor;
import static pt.ipg.application.testingcovid_19.database.Convert.contentValuesToQuestion;

public class DBTableQuestion implements BaseColumns {
    public static final String TABLE_NAME = "question";

    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_FK_DOCTOR = "fk_doctor";

    // RELATION TABLE
    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;
    public static final String COLUMN_FULL_QUESTION = TABLE_NAME + "." + COLUMN_QUESTION;
    public static final String COLUMN_FULL_FK_DOCTOR = TABLE_NAME + "." + COLUMN_FK_DOCTOR;

    // DOCTOR FIELD's
    public static final String COLUMN_FULL_FK_DOCTOR_NAME = DBTableDoctor.COLUMN_FULL_NAME + " AS " + DBTableDoctor.COLUMN_NAME;
    public static final String COLUMN_FULL_FK_DOCTOR_TIN = DBTableDoctor.COLUMN_FULL_TIN + " AS " + DBTableDoctor.COLUMN_TIN;
    public static final String COLUMN_FULL_FK_DOCTOR_AVATAR = DBTableDoctor.COLUMN_FULL_AVATAR + " AS " + DBTableDoctor.COLUMN_AVATAR;
    public static final String COLUMN_FULL_FK_DOCTOR_EMAIL = DBTableDoctor.COLUMN_FULL_EMAIL + " AS " + DBTableDoctor.COLUMN_EMAIL;
    public static final String COLUMN_FULL_FK_DOCTOR_PHONE = DBTableDoctor.COLUMN_FULL_PHONE + " AS " + DBTableDoctor.COLUMN_PHONE;
    public static final String COLUMN_FULL_FK_DOCTOR_PASSWORD = DBTableDoctor.COLUMN_FULL_PASSWORD + " AS " + DBTableDoctor.COLUMN_PASSWORD;
    public static final String COLUMN_FULL_FK_DOCTOR_CONFIRMED = DBTableDoctor.COLUMN_FULL_CONFIRMED + " AS " + DBTableDoctor.COLUMN_CONFIRMED;
    public static final String COLUMN_FULL_FK_DOCTOR_CREATED_AT = DBTableDoctor.COLUMN_FULL_CREATED_AT + " AS " + DBTableDoctor.COLUMN_CREATED_AT;

    public static final String[] ALL_COLUMN = {
            COLUMN_FULL_ID, COLUMN_FULL_QUESTION, COLUMN_FULL_FK_DOCTOR
    };

    public static final boolean[] IS_STRING = {
            false, true, false
    };

    private SQLiteDatabase db;
    private RemoteDB rDB;
    private Context context;

    public DBTableQuestion (SQLiteDatabase db) {
        this.db = db;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public static String CREATE_QUERY(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTION + " TEXT NOT NULL," +
                COLUMN_FK_DOCTOR + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + COLUMN_FK_DOCTOR + ") REFERENCES " +
                DBTableDoctor.TABLE_NAME + "("+ DBTableDoctor._ID + ")" +
                ")";
    }

    public void create() {
        db.execSQL(CREATE_QUERY());
    }

    public long insert(ContentValues values) {

        // Save on remote server
        rDB = new RemoteDB(context);
        String QUERY = ParseInsertSQL(TABLE_NAME, ALL_COLUMN, contentValuesToQuestion(values).Values(), IS_STRING);
        rDB.query(QUERY);
        // Save on remote server

        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_NAME) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_TIN) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_AVATAR) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_EMAIL) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_PHONE) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_PASSWORD) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_CONFIRMED) &&
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_DOCTOR_CREATED_AT)
        ) {
            return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
        }

        String campos = TextUtils.join(",", columns);

        String sql = "SELECT " + campos;
        sql += " FROM " + TABLE_NAME + " INNER JOIN " + DBTableDoctor.TABLE_NAME;
        sql += " ON " + COLUMN_FULL_FK_DOCTOR + "=" + DBTableDoctor.COLUMN_FULL_ID;

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
