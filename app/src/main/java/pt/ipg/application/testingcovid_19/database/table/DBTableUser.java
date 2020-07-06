package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import pt.ipg.application.testingcovid_19.database.remote.RemoteDB;

import static pt.ipg.application.testingcovid_19.database.Convert.ParseInsertSQL;
import static pt.ipg.application.testingcovid_19.database.Convert.contentValuesToDoctor;
import static pt.ipg.application.testingcovid_19.database.Convert.contentValuesToUser;

public class DBTableUser implements BaseColumns {

    public static final String TABLE_NAME = "user";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_TIN = "tin";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_DISTRICT = "district";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_CREATED_AT = "created_at";

    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;
    public static final String COLUMN_FULL_NAME = TABLE_NAME + "." + COLUMN_NAME;
    public static final String COLUMN_FULL_GENDER = TABLE_NAME + "." + COLUMN_GENDER;
    public static final String COLUMN_FULL_TIN = TABLE_NAME + "." + COLUMN_TIN;
    public static final String COLUMN_FULL_EMAIL = TABLE_NAME + "." + COLUMN_EMAIL;
    public static final String COLUMN_FULL_PHONE = TABLE_NAME + "." + COLUMN_PHONE;
    public static final String COLUMN_FULL_BIRTHDAY = TABLE_NAME + "." + COLUMN_BIRTHDAY;
    public static final String COLUMN_FULL_DISTRICT = TABLE_NAME + "." + COLUMN_DISTRICT;
    public static final String COLUMN_FULL_COUNTRY = TABLE_NAME + "." + COLUMN_COUNTRY;
    public static final String COLUMN_FULL_CREATED_AT = TABLE_NAME + "." + COLUMN_CREATED_AT;

    public static final String[] ALL_COLUMN = {
            _ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_TIN, COLUMN_EMAIL, COLUMN_PHONE,
            COLUMN_BIRTHDAY, COLUMN_DISTRICT, COLUMN_COUNTRY, COLUMN_CREATED_AT
    };

    public static final boolean[] IS_STRING = {
            false, true, true, true,
            true, true, true, true, true, true
    };

    private SQLiteDatabase db;
    private RemoteDB rDB;
    private Context context;

    public DBTableUser (SQLiteDatabase db) {
        this.db = db;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public static String CREATE_QUERY(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_GENDER + " TEXT NOT NULL,"+
                COLUMN_TIN + " TEXT NOT NULL,"+
                COLUMN_EMAIL + " TEXT NOT NULL,"+
                COLUMN_PHONE + " TEXT NOT NULL,"+
                COLUMN_BIRTHDAY + " TEXT NOT NULL,"+
                COLUMN_DISTRICT + " TEXT NOT NULL,"+
                COLUMN_COUNTRY + " TEXT NOT NULL,"+
                COLUMN_CREATED_AT + " TEXT NOT NULL"+
                ")";
    }

    public void create() {
        db.execSQL(CREATE_QUERY());
    }

    public long insert(ContentValues values) {

        // Save on remote server
        rDB = new RemoteDB(context);
        String QUERY = ParseInsertSQL(TABLE_NAME, ALL_COLUMN, contentValuesToUser(values).Values(), IS_STRING);
        rDB.query(QUERY);
        // Save on remote server

        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query (String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public int delete(){
        return db.delete(TABLE_NAME, null, null);
    }

    public long count(){
        Cursor cursor = query(ALL_COLUMN, null, null, null, null, null);
        return cursor.getCount();
    }
}