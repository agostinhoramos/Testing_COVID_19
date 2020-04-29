package pt.ipg.application.testingcovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends AppCompatActivity {
    Button i_am_doctor,i_am_patient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_welcome);
        i_am_doctor=findViewById(R.id.doctor);
        i_am_patient=findViewById(R.id.patient);
        i_am_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,WelcomeDoctorActivity.class);
                startActivity(intent);
            }
        });
        i_am_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
