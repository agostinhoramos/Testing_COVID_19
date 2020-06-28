package pt.ipg.application.testingcovid_19.ui.login;

import android.content.Intent;
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
import android.widget.TextView;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.ui.DashboardActivity;
import pt.ipg.application.testingcovid_19.ui.TestActivity;
import pt.ipg.application.testingcovid_19.other.Validations;


public class DoctorLoginActivity extends Fragment {
    public static final String EXTRA_TEXT_USERNAME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_USERNAME";
    public static final String EXTRA_TEXT_REMEMBER_ME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_REMEMBER_ME";

    private EditText TextInputUsername, TextInputPassword;
    private CheckBox RememberMe;
    private TextView ForgotPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button login;
        View view = inflater.inflate(R.layout.activity_signin, container,false);

        TextInputUsername = (EditText) view.findViewById(R.id.username);
        TextInputPassword = (EditText) view.findViewById(R.id.password);
        RememberMe = (CheckBox) view.findViewById(R.id.rememberme);
        ForgotPassword = (TextView) view.findViewById(R.id.forgotpassword);

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = TextInputUsername.getText().toString().trim();
                Intent intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
                intent.putExtra(EXTRA_TEXT_USERNAME, usernameText);
                startActivity(intent);
            }
        });

        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAuth(view);
            }
        });
        return view;
    }

    private void loginAuth(View view){
        boolean auth = true;

        String usernameText = TextInputUsername.getText().toString().trim();
        String passwordText = TextInputPassword.getText().toString();
        String rememberMeText = RememberMe.getText().toString();

        // VALIDATION
        if( usernameText.isEmpty() ){
            TextInputUsername.setError("Field Username can't be empty!");
            auth = false;
        }else if(!Validations.matchUSERNAME().matcher(usernameText).matches()){
            TextInputUsername.setError("Please enter a valid Username!");
            auth = false;
        }

        if( passwordText.isEmpty() ){
            TextInputPassword.setError("Field Password can't be empty!");
            auth = false;
        }else if(!Validations.matchPASSWORD().matcher(passwordText).matches()){
            TextInputPassword.setError("Please enter a valid password");
            auth = false;
        }

        if( !auth(usernameText, passwordText) ){
            TextInputPassword.setError("Username or Password not exist!");
            auth = false;
        }

        // AUTHENTICATION
        if( auth ){ // TODO
            Intent intent = new Intent(view.getContext(), DashboardActivity.class);
            intent.putExtra(EXTRA_TEXT_USERNAME, usernameText);
            intent.putExtra(EXTRA_TEXT_REMEMBER_ME, rememberMeText);
            startActivity(intent);
        }

    }

    private boolean auth(String username, String password){
        /*DBTableUser tableUser = new DBTableUser(Database);
        Cursor cursor = tableUser.query(DBTableUser.ALL_COLUMN, null, null, null, null, null);
        int register = cursor.getCount();

        cursor.moveToPosition(position);
        String name = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME));
        String gender = cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER));*/
        return true;
    }
}