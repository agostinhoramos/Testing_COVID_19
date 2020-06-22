package pt.ipg.application.testingcovid_19.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipg.application.testingcovid_19.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Intent intent = getIntent();
        String usernameText = intent.getStringExtra(PatientLoginActivity.EXTRA_TEXT_USERNAME);
        // TODO
    }
}