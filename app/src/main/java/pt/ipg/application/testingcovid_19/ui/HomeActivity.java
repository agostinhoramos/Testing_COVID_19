package pt.ipg.application.testingcovid_19.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.ui.login.PatientLoginActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_panel);

        Intent intent = getIntent();
        String usernameText = intent.getStringExtra(PatientLoginActivity.EXTRA_TEXT_USERNAME);
        String passwordText = intent.getStringExtra(PatientLoginActivity.EXTRA_TEXT_PASSWORD);
        String rememberMeText = intent.getStringExtra(PatientLoginActivity.EXTRA_TEXT_REMEMBER_ME);

        TextView textView1 = (TextView) findViewById(R.id.myTextView);
        textView1.setText(usernameText);
        // TODO
    }
}