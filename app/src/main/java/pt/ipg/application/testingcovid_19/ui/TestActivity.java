package pt.ipg.application.testingcovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;

public class TestActivity extends AppCompatActivity {

    private Fragment currentFragment = null;
    private TextView textViewNumAsk;
    private TextView textViewAsk;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

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

        textViewNumAsk = findViewById(R.id.NumAsk);
        textViewAsk = findViewById(R.id.Ask);
        buttonConfirmNext = findViewById(R.id.ConfirmNext);

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(this);
        Database = openHelper.getWritableDatabase();

        rb1 = (RadioButton) findViewById(R.id.radio_button1);
        rb2 = (RadioButton) findViewById(R.id.radio_button2);
        rb3 = (RadioButton) findViewById(R.id.radio_button3);
        rb4 = (RadioButton) findViewById(R.id.radio_button4);

        setupUI();
    }

    private void setupUI() {
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                position++;
                cursor = getALLitems();
                cursor.moveToPosition(position);

                String ask = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ASK));
                String r1 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS1));
                String r2 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS2));
                String r3 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS3));
                String r4 = cursor.getString(cursor.getColumnIndex(DBTableSequenceQuestions.COLUMN_ANSWERS4));

                rb1.setText(r1);
                rb2.setText(r2);
                rb3.setText(r3);
                rb4.setText(r4);
                textViewAsk.setText(ask);*/

                //String responce = "{\"name\":\"Agostinho Ramos\"}";
                //System.out.println( parseString( parseJSON(responce), "name") );
            }
        });
    }

}