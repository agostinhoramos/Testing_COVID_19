package pt.ipg.application.testingcovid_19.ui;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.ui.login.DoctorLoginActivity;
import pt.ipg.application.testingcovid_19.ui.login.DoctorSignUpActivity;

public class HomeActivity extends AppCompatActivity {

    private DatabaseOpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_panel);

        Intent intent = getIntent();
        String DOCTOR_SING_UP = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_SUBMIT_TYPE);

        openHelper = new DatabaseOpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        // TODO
        if( DOCTOR_SING_UP.contains("1") ){

            String Name = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_FULLNAME);
            String Email = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_EMAIL);
            String TIN = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_TIN);
            String Password = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_PASSWORD);

            DBTableDoctor tbDoctor = new DBTableDoctor(db);
            insertDoctor(
                    tbDoctor,
                    Name,
                    TIN,
                    Email,
                    "0",
                    Password,
                    "1"
            );
            db.close();
            String message = "Account successfully created!";
            Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG).show();
        }

        if( true ){ // TODO

        }
    }

    private long insertDoctor(DBTableDoctor tbDoctor, String Name, String TIN, String Email,
                            String Phone, String Password, String Confirmed) {
        Doctor doctor = new Doctor();

        doctor.setName(Name);
        doctor.setTIN(TIN);
        doctor.setEmail(Email);
        doctor.setPhone(Phone);
        doctor.setPassword(Password);
        doctor.setConfirmed(Confirmed);

        return insertDoctor(tbDoctor, doctor);
    }

    private long insertDoctor(DBTableDoctor tbDoctor, Doctor doctor) {
        return tbDoctor.insert(Convert.doctorToContentValues(doctor));
    }
}