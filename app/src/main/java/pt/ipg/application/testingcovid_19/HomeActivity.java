package pt.ipg.application.testingcovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_panel);

        Intent intent = getIntent();
        String usernameText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_USERNAME);
        String passwordText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_PASSWORD);
        String rememberMeText = intent.getStringExtra(SigninActivity.EXTRA_TEXT_REMEMBER_ME);

        TextView textView1 = (TextView) findViewById(R.id.myTextView);
        textView1.setText(usernameText);
        // TODO
    }
}