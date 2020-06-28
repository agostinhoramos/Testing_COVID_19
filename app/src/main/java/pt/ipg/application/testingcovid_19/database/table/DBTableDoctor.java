package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBTableDoctor implements BaseColumns {
    public static final String TABLE_NAME = "doctor";
    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIN = "TIN";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CONFIRMED = "confirmed";
    public static final String[] ALL_COLUMN = {
            _ID, COLUMN_NAME, COLUMN_TIN, COLUMN_EMAIL,
            COLUMN_PHONE, COLUMN_PASSWORD, COLUMN_CONFIRMED
    };

    private SQLiteDatabase db;

    public DBTableDoctor (SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT NOT NULL," +
                    COLUMN_TIN + " TEXT NOT NULL,"+
                    COLUMN_EMAIL + " TEXT NOT NULL,"+
                    COLUMN_PHONE + " TEXT NOT NULL,"+
                    COLUMN_PASSWORD + " TEXT NOT NULL,"+
                    COLUMN_CONFIRMED + " TEXT NOT NULL "+
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
}
