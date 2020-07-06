package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pt.ipg.application.testingcovid_19.data.Seed;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.remote.SyncDB;

public class WelcomeActivity extends AppCompatActivity {
    Button i_am_doctor, i_am_patient;
    SyncDB syncDB;
    Seed seed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_welcome);
        i_am_doctor = findViewById(R.id.doctor);
        i_am_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect_welcome_doctor();
            }
        });
        i_am_patient = findViewById(R.id.patient);
        i_am_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect_patient();
            }
        });

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(WelcomeActivity.this);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        syncDB = new SyncDB(db, WelcomeActivity.this);
        seed = new Seed(db, WelcomeActivity.this);
    }

    private void redirect_welcome_doctor(){
        Intent intent = new Intent(WelcomeActivity.this, DoctorAuthActivity.class);
        startActivity(intent);
    }

    private void redirect_patient() {
        //Intent intent = new Intent(WelcomeActivity.this, TestActivity.class);
        //startActivity(intent);

        //syncDB.createAllTables();
        seed.load();
    }
}