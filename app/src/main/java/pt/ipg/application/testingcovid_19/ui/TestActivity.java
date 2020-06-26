package pt.ipg.application.testingcovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.object.User;

public class TestActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setupUI();
    }

    private void setupUI() {
        textViewNumAsk = findViewById(R.id.NumAsk);
        textViewAsk = findViewById(R.id.Ask);
        rdGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);

        buttonConfirmNext = findViewById(R.id.ConfirmNext);
    }
}