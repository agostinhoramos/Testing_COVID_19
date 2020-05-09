package pt.ipg.application.testingcovid_19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PatientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_panel);

        Intent intent = getIntent();
        String usernameText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_USERNAME);
        String passwordText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_PASSWORD);
        String rememberMeText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_REMEMBER_ME);
        String forgotPasswordText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_FORGOT_PASSWORD);

        TextView textView1 = (TextView) findViewById(R.id.myTextView);
        textView1.setText(usernameText);
    }
}