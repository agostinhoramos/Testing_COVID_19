package pt.ipg.application.testingcovid_19;

import android.content.Intent;
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
import android.widget.Toast;


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
        View view = inflater.inflate(R.layout.fragment_doctor_login, container,false);

        TextInputUsername = (EditText) view.findViewById(R.id.username);
        TextInputPassword = (EditText) view.findViewById(R.id.password);
        RememberMe = (CheckBox) view.findViewById(R.id.rememberme);
        ForgotPassword = (TextView) view.findViewById(R.id.forgotpassword);

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = TextInputUsername.getText().toString().trim();
                //Intent intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
                //intent.putExtra(EXTRA_TEXT_USERNAME, usernameText);
                //startActivity(intent);
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

    private void loginAuth(final View view){
        boolean auth = true;

        String usernameText = TextInputUsername.getText().toString().trim();
        String passwordText = TextInputPassword.getText().toString();
        String rememberMeText = RememberMe.getText().toString();

        // VALIDATION
        // EMAIL
        if( usernameText.isEmpty() ){
            TextInputUsername.setError("Field Username can't be empty!");
            auth = false;
        }else if(!Validations.matchEMAIL().matcher(usernameText).matches()){
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
        Intent intent = new Intent(view.getContext(), DoctorDashboardActivity.class);
        startActivity(intent);

        /*if( auth ){ // TODO Firebase authentication...
            mAuth.signInWithEmailAndPassword(usernameText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Intent intent = new Intent(view.getContext(), DashboardActivity.class);
                        //startActivity(intent);
                    }else{
                        Toast.makeText(view.getContext(), "Username or Password not exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }*/
    }
}