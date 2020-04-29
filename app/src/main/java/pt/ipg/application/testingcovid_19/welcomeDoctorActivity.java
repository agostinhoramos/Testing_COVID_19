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
    Button sign_up,log_in;
    ImageView doctor_image;
    Toolbar toolbar;
    DrawerLayout drawerLayout ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_doctor);
        setTitle("");
        sign_up=findViewById(R.id.sign_up);
        log_in=findViewById(R.id.log_in);
        doctor_image=findViewById(R.id.image_doctor);
        toolbar=(Toolbar) findViewById(R.id.toolbar2);
        drawerLayout=findViewById(R.id.drawer_layout2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* Intent intent = new Intent(DoctorPage.this,Terms.class);
                startActivity(intent);*/
            }
        });
        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}