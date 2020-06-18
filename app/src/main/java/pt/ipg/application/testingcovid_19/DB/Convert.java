package pt.ipg.application.testingcovid_19.db;

import android.content.ContentValues;
import android.database.Cursor;

import pt.ipg.application.testingcovid_19.db.table.DBTableTest;
import pt.ipg.application.testingcovid_19.db.table.DBTableUser;
import pt.ipg.application.testingcovid_19.objects.Test;
import pt.ipg.application.testingcovid_19.objects.User;

public class Convert {
    public static ContentValues userToContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(DBTableUser.COLUMN_NAME, user.getName());
        return values;
    }
    public static ContentValues testToContentValues(Test test){
        ContentValues values = new ContentValues();
        values.put(DBTableTest.COLUMN_LEVEL, test.getLevel());
        return values;
    }

    public static User ContentValuesToUser(ContentValues values){
        User user = new User();
        user.setId(values.getAsLong(DBTableUser._ID));
        user.setName(values.getAsString(DBTableUser.COLUMN_NAME));
        return user;
    }

    public static Test ContentValuesToTest(ContentValues values){
        Test test = new Test();
        test.setId(values.getAsLong(DBTableUser._ID));
        test.setLevel(values.getAsString(DBTableTest.COLUMN_LEVEL));
        return test;
    }

    // WHEN WE USER CURSOR? TODO
    // CAN WE ALSO USE THIS WITH FOREIGN KEY?
    public static Test cursorToTest(Cursor cursor){
        Test test = new Test();
        test.setId(cursor.getLong(cursor.getColumnIndex(DBTableTest._ID)));
        test.setDate(cursor.getString(cursor.getColumnIndex(DBTableTest.COLUMN_DATE)));
        test.setLevel(cursor.getString(cursor.getColumnIndex(DBTableTest.COLUMN_LEVEL)));
        return test;
    }

}
