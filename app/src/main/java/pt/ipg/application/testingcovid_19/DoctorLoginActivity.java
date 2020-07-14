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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.other.Validations;


public class DoctorLoginActivity extends Fragment {
    public static final String EXTRA_TEXT_USER_ID = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_USER_ID";
    public static final String EXTRA_TEXT_REMEMBER_ME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_REMEMBER_ME";
    public static final String EXTRA_TEXT_USER_EMAIL = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_USER_EMAIL";

    private EditText TextInputUsername, TextInputPassword;
    private CheckBox RememberMe;
    private TextView ForgotPassword;
    private Cursor cursor;
    private Intent intent;
    private ImageView tv_img_profile;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button login;
        View view = inflater.inflate(R.layout.fragment_doctor_login, container,false);

        Context appContext = getContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        db = openHelper.getWritableDatabase();

        TextInputUsername = (EditText) view.findViewById(R.id.et_email);
        TextInputPassword = (EditText) view.findViewById(R.id.et_password);
        RememberMe = (CheckBox) view.findViewById(R.id.cb_remember_me);
        ForgotPassword = (TextView) view.findViewById(R.id.tv_forgotpassword);
        tv_img_profile = (ImageView) view.findViewById(R.id.img_profile);

        // Define image profile..
        //tv_img_profile.setImageResource(R.drawable.buttons_background);

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = TextInputUsername.getText().toString().trim();
                intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
                intent.putExtra(EXTRA_TEXT_USER_EMAIL, usernameText);
                startActivity(intent);
            }
        });

        login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAuth(view);
            }
        });
        return view;
    }

    private void loginAuth(final View view){
        boolean auth = true;

        String emailText = TextInputUsername.getText().toString().trim();
        String passwordText = TextInputPassword.getText().toString();
        boolean rememberMeBoo = RememberMe.isChecked();

        // VALIDATION
        // EMAIL
        if( emailText.isEmpty() ){
            TextInputUsername.setError("Field Username can't be empty!");
            auth = false;
        }else if(!Validations.matchEMAIL().matcher(emailText).matches()){
            TextInputUsername.setError("Please enter a valid Username!");
            auth = false;
        }

        // PASSWORD
        if( passwordText.isEmpty() ){
            TextInputPassword.setError("Field Password can't be empty!");
            auth = false;
        }else if(!Validations.matchPASSWORD().matcher(passwordText).matches()){
            TextInputPassword.setError("Please enter a valid password");
            auth = false;
        }

        // AUTHENTICATION

        if( auth ){

            DBTableDoctor tb_doctor = new DBTableDoctor(db);
            try{
                String selection = DBTableDoctor.COLUMN_EMAIL+"=? AND "+DBTableDoctor.COLUMN_PASSWORD+"=?";
                String[] args = {emailText, passwordText};
                cursor = tb_doctor.query(DBTableDoctor.ALL_COLUMN, selection, args, null, null, null);
                cursor.moveToPosition(0);
                int id_doctor = (int) cursor.getLong(cursor.getColumnIndex(DBTableDoctor._ID));
                // Redirect
                intent = new Intent(view.getContext(), DoctorDashboardActivity.class);
                intent.putExtra(EXTRA_TEXT_USER_ID, id_doctor);
                intent.putExtra(EXTRA_TEXT_REMEMBER_ME, rememberMeBoo);
                startActivity(intent);
            }catch(Exception e){
                Toast.makeText(getContext(), "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        }
    }
}