package pt.ipg.application.testingcovid_19.ui.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.db.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.db.table.DBTableUser;
import pt.ipg.application.testingcovid_19.objects.User;

public class Data extends AppCompatActivity {
    EditText name,gender,tin,email,phone,birthday,district,country;
    DatabaseOpenHelper databaseOpenHelper;
    User user;
    DBTableUser dbTableUser ;
    ArrayList<User> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        name=findViewById(R.id.name);
        gender=findViewById(R.id.gender);
        tin=findViewById(R.id.tin);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        birthday=findViewById(R.id.birthday);
        district=findViewById(R.id.district);
        country=findViewById(R.id.country);
        databaseOpenHelper = new DatabaseOpenHelper(this);


    }
    void showData(){
        SQLiteDatabase sqLiteDatabase = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = dbTableUser.query(null,null,null,null,null,null);
        if (cursor.moveToNext())
        {

        }

    }
}
