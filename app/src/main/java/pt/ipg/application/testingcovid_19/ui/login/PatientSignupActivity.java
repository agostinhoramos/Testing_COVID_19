package pt.ipg.application.testingcovid_19.ui.login;

import android.content.Intent;
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

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.other.Validations;
import pt.ipg.application.testingcovid_19.ui.HomeActivity;
import pt.ipg.application.testingcovid_19.ui.TermsServicesActivity;
import pt.ipg.application.testingcovid_19.ui.WelcomeActivity;

public class PatientSignupActivity extends Fragment {
    public static final String EXTRA_TEXT_SUBMIT_TYPE = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_SUBMIT_TYPE";
    public static final String EXTRA_TEXT_FULLNAME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_FULLNAME";
    public static final String EXTRA_TEXT_USERNAME = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_USERNAME";
    public static final String EXTRA_TEXT_TIN = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_TIM";
    public static final String EXTRA_TEXT_EMAIL = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_EMAIL";
    public static final String EXTRA_TEXT_PASSWORD = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_PASSWORD";

    EditText TextInputFull_name, TextInputUser_name, TextInputTIN, TextInputEmail,
            TextInputPassword, TextInputConfirm_password;
    Button sign_up,continue_with_google;
    TextView termsandservices;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup, container,false);
        TextInputFull_name = view.findViewById(R.id.full_name);
        TextInputUser_name = view.findViewById(R.id.username);
        TextInputTIN = view.findViewById(R.id.TIN);
        TextInputEmail = view.findViewById(R.id.email);
        TextInputPassword = view.findViewById(R.id.password);
        TextInputConfirm_password = view.findViewById(R.id.confirm_password);

        sign_up = view.findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });

        continue_with_google = view.findViewById(R.id.continue_with_google);
        continue_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleAuth(v);
            }
        });

        termsandservices = view.findViewById(R.id.termsandservices);
        termsandservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terms_and_servicesOpen(v);
            }
        });

        return view;
    }

    private void submit(View view){
        boolean submit = true;
        String Full_name = TextInputFull_name.getText().toString().trim();;
        String User_name = TextInputUser_name.getText().toString().trim();
        String Email = TextInputEmail.getText().toString().trim();
        String TIN = TextInputTIN.getText().toString().trim();
        String Password = TextInputPassword.getText().toString().trim();
        String Confirm_password = TextInputConfirm_password.getText().toString().trim();

        // VALIDATION
        if(Full_name.isEmpty()){
            TextInputFull_name.setError("Field Full Name can't be empty!");
            submit = false;
        }else if(!Validations.matchFULLNAME().matcher(Full_name).matches()){
            TextInputFull_name.setError("Please enter a valid Full Name!");
            submit = false;
        }

        if(User_name.isEmpty()){
            TextInputUser_name.setError("Field User Name can't be empty!");
            submit = false;
        }else if(!Validations.matchUSERNAME().matcher(User_name).matches()){
            TextInputUser_name.setError("Please enter a valid User Name!");
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

        if( Password != Confirm_password ){
            TextInputConfirm_password.setError("Password don't match!");
            submit = false;
        }

        // SUBMIT
        if( submit ){ // It's ready to submit all information..
            Intent intent = new Intent(view.getContext(), HomeActivity.class);
            intent.putExtra(EXTRA_TEXT_SUBMIT_TYPE, "PATIENT-SIGNUP");
            intent.putExtra(EXTRA_TEXT_FULLNAME, Full_name);
            intent.putExtra(EXTRA_TEXT_USERNAME, User_name);
            intent.putExtra(EXTRA_TEXT_TIN, TIN);
            intent.putExtra(EXTRA_TEXT_EMAIL, Email);
            intent.putExtra(EXTRA_TEXT_PASSWORD, Password);
            startActivity(intent);
        }
    }

    private void googleAuth(View view){
        // TODO Google authentication sun as possible..
        Intent intent = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    private void terms_and_servicesOpen(View view){
        Intent intent = new Intent(view.getContext(), TermsServicesActivity.class);
        startActivity(intent);
    }
}