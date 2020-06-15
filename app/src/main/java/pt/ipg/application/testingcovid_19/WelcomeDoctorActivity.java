package pt.ipg.application.testingcovid_19;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class WelcomeDoctorActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT_IS_DOCTOR = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_IS_DOCTOR";
    Button sign_up,log_in;
    ImageView doctor_image;
    Toolbar toolbar;
    DrawerLayout drawerLayout ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_doctor);
        setTitle("Welcome");

        doctor_image = findViewById(R.id.image_doctor);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        drawerLayout = findViewById(R.id.drawer_layout2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToSignUp();
            }
        });

        log_in = findViewById(R.id.log_in);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin();
            }
        });

        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void ToLogin(){
        Intent intent = new Intent(this, SigninActivity.class);
        intent.putExtra(EXTRA_TEXT_IS_DOCTOR, true);
        startActivity(intent);
    }

    private void ToSignUp(){
        Intent intent = new Intent(this, SignupActivity.class);
        intent.putExtra(EXTRA_TEXT_IS_DOCTOR, true);
        startActivity(intent);
        ToSignUp();
    }
}