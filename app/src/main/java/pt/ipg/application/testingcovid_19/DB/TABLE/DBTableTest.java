package pt.ipg.application.testingcovid_19.DB.TABLE;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;
import java.util.Arrays;

public class DBTableTest implements BaseColumns {
    public static final String TABLE_NAME = "test";
    public static final String COLUMN_FULL_ID = TABLE_NAME + "." + _ID;

    public static final String COLUMN_FK_USER = "id_test";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LEVEL = "level";

    public static final String NAME_FK_USER = "user";

    public static final String COLUMN_ALL_USER = DBTableUser.ALL_COLUMN + " AS " + NAME_FK_USER;

    public static final String[] ALL_COLUMN = {
            COLUMN_DATE, COLUMN_LEVEL, COLUMN_ALL_USER
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
                DBTableUser.TABLE_NAME + "("+ _ID + ")" +
                ")");
    }

    public long insert(ContentValues values) {
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (!Arrays.asList(columns).contains(COLUMN_ALL_USER)) {
            return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
        }

        String campos = TextUtils.join(",", columns);

        String sql = "SELECT " + campos;
        sql += " FROM " + TABLE_NAME + " INNER JOIN " + DBTableUser.TABLE_NAME;
        sql += " ON " + COLUMN_FULL_ID + "=" + DBTableUser.COLUMN_FULL_ID;

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
}