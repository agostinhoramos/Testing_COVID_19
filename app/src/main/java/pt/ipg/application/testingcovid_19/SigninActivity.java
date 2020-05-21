package pt.ipg.application.testingcovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.AppComponentFactory;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class SigninActivity extends Fragment {
    public static final String EXTRA_TEXT_USERNAME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_USERNAME";
    public static final String EXTRA_TEXT_PASSWORD = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_PASSWORD";
    public static final String EXTRA_TEXT_REMEMBER_ME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_REMEMBER_ME";
    public static final String EXTRA_TEXT_FORGOT_PASSWORD = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_FORGOT_PASSWORD";

    private EditText username, password;
    private CheckBox rememberMe;
    private TextView forgotPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button login;
        View view = inflater.inflate(R.layout.activity_signin, container,false);

        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        rememberMe = (CheckBox) view.findViewById(R.id.rememberme);
        forgotPassword = (TextView) view.findViewById(R.id.forgotpassword);

        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAuth(view);
            }
        });
        return view;
    }

    public void loginAuth(View view){
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        String rememberMeText = rememberMe.getText().toString();
        String forgotPasswordText = forgotPassword.getText().toString();

        Intent intent = new Intent(view.getContext(), PatientHomeActivity.class);
        intent.putExtra(EXTRA_TEXT_USERNAME, usernameText);
        intent.putExtra(EXTRA_TEXT_PASSWORD, passwordText);
        intent.putExtra(EXTRA_TEXT_REMEMBER_ME, rememberMeText);
        intent.putExtra(EXTRA_TEXT_FORGOT_PASSWORD, forgotPasswordText);
        startActivity(intent);
    }
}