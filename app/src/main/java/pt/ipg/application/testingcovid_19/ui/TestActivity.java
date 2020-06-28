package pt.ipg.application.testingcovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.object.User;

public class TestActivity extends AppCompatActivity {

    private Fragment currentFragment = null;

    private TextView textViewNumAsk;
    private TextView textViewAsk;

    private RadioGroup rdGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ArrayList<User> userList;
    private int rows;
    private String uName;
    private String uEmail;

    private SQLiteDatabase Database;
    private Cursor cursor;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(this);
        Database = openHelper.getWritableDatabase();

        //RecyclerView recyclerView = findViewById(R.id.recyclerViewUser);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //long myid = insertUser(tableUser, "Paloma", "F", "287273962", "p@gmail.com", "934927329", "16/11/1997", "Guarda", "Portugal");

        cursor = getALLitems();

        rdGroup = findViewById(R.id.radio_group);
        rb1 = (RadioButton) findViewById(R.id.radio_button1);
        rb2 = (RadioButton) findViewById(R.id.radio_button2);
        rb3 = (RadioButton) findViewById(R.id.radio_button3);
        rb4 = (RadioButton) findViewById(R.id.radio_button4);

        cc = 3;
        User user = new User();
        user.setName("Nome"+cc);
        user.setGender("Genero"+cc);
        user.setTIN("NIF"+cc);
        user.setEmail("Email"+cc);
        user.setPhone("Telemovel"+cc);
        user.setBirthday("Nascimento"+cc);
        user.setDistrict("Distrito"+cc);
        user.setCountry("País"+cc);

        ContentValues cv;
        cv = Convert.userToContentValues(user);
        Database.insert(DBTableUser.TABLE_NAME, null, cv);

        cursor.moveToPosition(position);
        String name = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME));
        String gender = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER));
        rb1.setText(name);
        rb2.setText(gender);
        rb3.setText("33");
        rb4.setText("44");

        //userAdapter = new UserAdapter(TestActivity.this, cursor );
        //recyclerView.setAdapter(userAdapter);

        cursor.close();

        setupUI();
    }


    public int cc = 0;
    private void setupUI() {
        textViewNumAsk = findViewById(R.id.NumAsk);
        textViewAsk = findViewById(R.id.Ask);
        buttonConfirmNext = findViewById(R.id.ConfirmNext);
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                System.out.println("Click...");
                User user = new User();
                user.setName("Nome"+cc);
                user.setGender("Genero"+cc);
                user.setTIN("NIF"+cc);
                user.setEmail("Email"+cc);
                user.setPhone("Telemovel"+cc);
                user.setBirthday("Nascimento"+cc);
                user.setDistrict("Distrito"+cc);
                user.setCountry("País"+cc);
                cc++;
                ContentValues cv;
                cv = Convert.userToContentValues(user);
                Database.insert(DBTableUser.TABLE_NAME, null, cv);
                 */
                position++;
                cursor = getALLitems();
                cursor.moveToPosition(position);
                String name = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME));
                String gender = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER));
                rb1.setText(name);
                rb2.setText(gender);
                rb3.setText("33");
                rb4.setText("44");
            }
        });
    }

    private long insertUser(DBTableUser tableUser, String Name, String Gender, String TIN, String Email,
                            String Phone, String Birthday, String District, String Country) {
        User user = new User();
        user.setName(Name);
        user.setGender(Gender);
        user.setTIN(TIN);
        user.setEmail(Email);
        user.setPhone(Phone);
        user.setBirthday(Birthday);
        user.setDistrict(District);
        user.setCountry(Country);

        return tableUser.insert(Convert.userToContentValues(user));
    }

    public void setCurrentFragment(Fragment currentFragment){
        this.currentFragment = currentFragment;
    }

    private Cursor getALLitems(){
        DBTableUser tableUser = new DBTableUser(Database);
        Cursor cursor = tableUser.query(DBTableUser.ALL_COLUMN, null, null, null, null, null);
        int register = cursor.getCount();
        //cursor.close();
        return cursor;
    }
}