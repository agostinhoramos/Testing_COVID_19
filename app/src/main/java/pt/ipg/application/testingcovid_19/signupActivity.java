package pt.ipg.application.testingcovid_19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Fragment {
    EditText full_name,user_name,email,password,confirm_password;
    Button sign_up,continue_with_google;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup,container,false);
        full_name = view.findViewById(R.id.full_name);
        user_name = view.findViewById(R.id.user_name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirm_password = view.findViewById(R.id.confirm_password);
        sign_up = view.findViewById(R.id.sign_up);
        continue_with_google = view.findViewById(R.id.continue_with_google);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        continue_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}