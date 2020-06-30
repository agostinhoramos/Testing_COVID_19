package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

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

    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;
    public static final String COLUMN_FULL_NAME = TABLE_NAME + "." + COLUMN_NAME;
    public static final String COLUMN_FULL_GENDER = TABLE_NAME + "." + COLUMN_GENDER;
    public static final String COLUMN_FULL_TIN = TABLE_NAME + "." + COLUMN_TIN;
    public static final String COLUMN_FULL_EMAIL = TABLE_NAME + "." + COLUMN_EMAIL;
    public static final String COLUMN_FULL_PHONE = TABLE_NAME + "." + COLUMN_PHONE;
    public static final String COLUMN_FULL_BIRTHDAY = TABLE_NAME + "." + COLUMN_BIRTHDAY;
    public static final String COLUMN_FULL_DISTRICT = TABLE_NAME + "." + COLUMN_DISTRICT;
    public static final String COLUMN_FULL_COUNTRY = TABLE_NAME + "." + COLUMN_COUNTRY;

    public static final String[] ALL_COLUMN = {
            _ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_TIN, COLUMN_EMAIL, COLUMN_PHONE,
            COLUMN_BIRTHDAY, COLUMN_DISTRICT, COLUMN_COUNTRY
    };

    private SQLiteDatabase db;

    public DBTableUser (SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_GENDER + " TEXT NOT NULL,"+
                COLUMN_TIN + " TEXT NOT NULL,"+
                COLUMN_EMAIL + " TEXT NOT NULL,"+
                COLUMN_PHONE + " TEXT NOT NULL,"+
                COLUMN_BIRTHDAY + " TEXT NOT NULL,"+
                COLUMN_DISTRICT + " TEXT NOT NULL,"+
                COLUMN_COUNTRY + " TEXT NOT NULL"+
                ")");
    }

    public long insert(ContentValues values) {
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