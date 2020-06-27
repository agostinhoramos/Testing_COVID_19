package pt.ipg.application.testingcovid_19.ui.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.object.User;

public class Data extends AppCompatActivity {
    EditText name, gender, tin, email, phone, birthday, district, country;
    DatabaseOpenHelper databaseOpenHelper;
    User user;
    DBTableUser dbTableUser ;
    ArrayList<User> arrayList = new ArrayList<>();
    String user_name, user_gender, user_tin, user_email, user_phone, user_birthday, user_district, user_country;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        tin = findViewById(R.id.tin);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        birthday = findViewById(R.id.birthday);
        district = findViewById(R.id.district);
        country = findViewById(R.id.country);
        databaseOpenHelper = new DatabaseOpenHelper(this);
    }

    private void showData(){
        SQLiteDatabase sqLiteDatabase = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = dbTableUser.query(DBTableUser.ALL_COLUMN,null,null,null,null,null);
        if (cursor.moveToNext())
        {
            user_name = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME));
            user_gender = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER));
            user_tin = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_TIN));
            user_email = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_EMAIL));
            user_phone = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_PHONE));
            user_birthday = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_BIRTHDAY));
            user_district = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_DISTRICT));
            user_country = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_COUNTRY));
            user = new User(user_name, user_gender, user_tin, user_email, user_phone, user_birthday, user_district, user_country);
            arrayList.add(user);
        }
    }
}