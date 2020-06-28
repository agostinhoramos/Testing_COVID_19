package pt.ipg.application.testingcovid_19.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import pt.ipg.application.testingcovid_19.R;

public class WelcomePatientActivity extends AppCompatActivity {
    Button log_in;
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

        log_in = findViewById(R.id.log_in);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePatientActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}