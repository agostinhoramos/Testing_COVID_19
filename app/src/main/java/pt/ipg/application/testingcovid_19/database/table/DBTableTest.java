package pt.ipg.application.testingcovid_19.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;
import java.util.Arrays;

public class DBTableTest implements BaseColumns {

    public static final String TABLE_NAME = "test";

    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_FK_USER = "fk_user";

    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;
    public static final String COLUMN_FULL_DATE = TABLE_NAME + "." + COLUMN_DATE;
    public static final String COLUMN_FULL_LEVEL = TABLE_NAME + "." + COLUMN_LEVEL;
    public static final String COLUMN_FULL_FK_USER = TABLE_NAME + "." + COLUMN_FK_USER;

    // USER FIELD's
    public static final String COLUMN_FULL_FK_USER_NAME = DBTableUser.COLUMN_FULL_NAME + " AS " + DBTableUser.COLUMN_NAME;
    public static final String COLUMN_FULL_FK_USER_GENDER = DBTableUser.COLUMN_FULL_GENDER + " AS " + DBTableUser.COLUMN_GENDER;
    public static final String COLUMN_FULL_FK_USER_TIN = DBTableUser.COLUMN_FULL_TIN + " AS " + DBTableUser.COLUMN_TIN;
    public static final String COLUMN_FULL_FK_USER_EMAIL = DBTableUser.COLUMN_FULL_EMAIL + " AS " + DBTableUser.COLUMN_EMAIL;
    public static final String COLUMN_FULL_FK_USER_PHONE = DBTableUser.COLUMN_FULL_PHONE + " AS " + DBTableUser.COLUMN_PHONE;
    public static final String COLUMN_FULL_FK_USER_BIRTHDAY = DBTableUser.COLUMN_FULL_BIRTHDAY + " AS " + DBTableUser.COLUMN_BIRTHDAY;
    public static final String COLUMN_FULL_FK_USER_DISTRICT = DBTableUser.COLUMN_FULL_DISTRICT + " AS " + DBTableUser.COLUMN_DISTRICT;
    public static final String COLUMN_FULL_FK_USER_COUNTRY = DBTableUser.COLUMN_FULL_COUNTRY + " AS " + DBTableUser.COLUMN_COUNTRY;

    public static final String[] ALL_COLUMN = {
            COLUMN_FULL_ID, COLUMN_FULL_DATE, COLUMN_FULL_LEVEL, COLUMN_FULL_FK_USER
            /*,COLUMN_FULL_FK_USER_NAME, COLUMN_FULL_FK_USER_GENDER, COLUMN_FULL_FK_USER_TIN, COLUMN_FULL_FK_USER_EMAIL, COLUMN_FULL_FK_USER_PHONE, COLUMN_FULL_FK_USER_BIRTHDAY, COLUMN_FULL_FK_USER_DISTRICT, COLUMN_FULL_FK_USER_COUNTRY*/
    };

    private SQLiteDatabase db;

    public DBTableTest (SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE + " TEXT NOT NULL," +
                COLUMN_LEVEL + " TEXT NOT NULL,"+
                COLUMN_FK_USER + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + COLUMN_FK_USER + ") REFERENCES " +
                DBTableUser.TABLE_NAME + "("+ DBTableUser._ID + ")" +
                ")");
    }

    public long insert(ContentValues values) {
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_NAME) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_GENDER) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_TIN) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_EMAIL) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_PHONE) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_BIRTHDAY) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_DISTRICT) ||
                !Arrays.asList(columns).contains(COLUMN_FULL_FK_USER_COUNTRY)
        ) {
            return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
        }

        String campos = TextUtils.join(",", columns);

        String sql = "SELECT " + campos;
        sql += " FROM " + TABLE_NAME + " INNER JOIN " + DBTableUser.TABLE_NAME;
        sql += " ON " + COLUMN_FULL_FK_USER + "=" + DBTableUser.COLUMN_FULL_ID;

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