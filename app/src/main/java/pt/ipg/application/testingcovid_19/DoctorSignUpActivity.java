package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.other.Function;
import pt.ipg.application.testingcovid_19.other.Validations;

public class DoctorSignUpActivity extends Fragment {

    public static final String EXTRA_TEXT_EMAIL = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_EMAIL";
    public static final String EXTRA_TEXT_PASSWORD = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_PASSWORD";

    private EditText TextInputFull_name, TextInputTIN, TextInputEmail, TextInputPassword, TextInputConfirm_password;
    private Button sign_up,continue_with_google;
    private TextView termsandservices;
    private Function fn;
    private Intent intent;
    private Cursor cursor;

    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context appContext = getContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        db = openHelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_doctor_signup, container,false);
        TextInputFull_name = view.findViewById(R.id.et_full_name);
        TextInputTIN = view.findViewById(R.id.et_TIN);
        TextInputEmail = view.findViewById(R.id.et_email);
        TextInputPassword = view.findViewById(R.id.et_password);
        TextInputConfirm_password = view.findViewById(R.id.et_confirm_password);

        fn = new Function();

        sign_up = view.findViewById(R.id.btn_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });

        continue_with_google = view.findViewById(R.id.btn_continue_with_google);
        continue_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleAuth(v);
            }
        });

        termsandservices = view.findViewById(R.id.tv_termsandservices);
        termsandservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terms_and_servicesOpen(v);
            }
        });

        return view;
    }

    private void submit(final View view){
        boolean submit = true;
        String Full_name = TextInputFull_name.getText().toString().trim();
        String Email = TextInputEmail.getText().toString().trim();
        String TIN = TextInputTIN.getText().toString().trim();
        String Password = TextInputPassword.getText().toString();
        String Confirm_password = TextInputConfirm_password.getText().toString();

        // VALIDATION
        if(Full_name.isEmpty()){
            TextInputFull_name.setError("Field Full Name can't be empty!");
            submit = false;
        }else if(!Validations.matchFULLNAME(Full_name)){
            TextInputFull_name.setError("Please enter a valid Full Name!");
            submit = false;
        }

        if(Email.isEmpty()){
            TextInputEmail.setError("Field Email can't be empty!");
            submit = false;
        }else if(!Validations.matchEMAIL().matcher(Email).matches()){
            TextInputEmail.setError("Please enter a valid Email!");
            submit = false;
        }

        if(TIN.isEmpty()){
            TextInputTIN.setError("Field TIN can't be empty!");
            submit = false;
        }else if(!Validations.matchTIN().matcher(TIN).matches()){
            TextInputTIN.setError("Please enter a valid TIN!");
            submit = false;
        }

        if(Password.isEmpty()){
            TextInputPassword.setError("Field Password can't be empty!");
            submit = false;
        }else if(!Validations.matchPASSWORD().matcher(Password).matches()){
            TextInputPassword.setError("Please enter a valid Password!");
            submit = false;
        }

        if( !Password.equals(Confirm_password) ){
            TextInputConfirm_password.setError("Password don't match!");
            submit = false;
        }

        try{
            DBTableDoctor tb_doctor = new DBTableDoctor(db);
            String selection = DBTableDoctor.COLUMN_EMAIL+"=? OR "+DBTableDoctor.COLUMN_TIN+"=? ";
            String[] args = {Email, TIN};
            cursor = tb_doctor.query(DBTableDoctor.ALL_COLUMN, selection, args, null, null, null);
            cursor.moveToPosition(0);
            int id_doctor = (int) cursor.getLong(cursor.getColumnIndex(DBTableDoctor._ID));
            if(id_doctor > 0){
                submit = false;
                TextInputEmail.setError("This Email or TIN already exist");
                TextInputTIN.setError("This Email or TIN already exist");
            }
        }catch (Exception e){}

        // SUBMIT
        if( submit ){ // It's ready to submit all information..
            DBTableDoctor tb_doctor = new DBTableDoctor(db);
            Doctor obj_doctor = new Doctor();
            obj_doctor.setName(Full_name);
            obj_doctor.setEmail(Email);
            obj_doctor.setTin(TIN);
            obj_doctor.setPassword(Password);
            obj_doctor.setConfirmed("0");
            obj_doctor.setCreated_at(fn.DateToString(new Date()));
            int id_doctor = (int) tb_doctor.insert(Convert.doctorToContentValues(obj_doctor));
            Toast.makeText(getContext(), "Account successfully created", Toast.LENGTH_SHORT).show();

            // Redirect
            intent = new Intent(view.getContext(), DoctorAuthActivity.class);
            startActivity(intent);
        }
    }

    private void googleAuth(View view){
        // TODO Google authentication sun as possible..
        Intent intent = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    private void terms_and_servicesOpen(View view){
        Intent intent = new Intent(view.getContext(), TermsConditionsActivity.class);
        startActivity(intent);
    }
}