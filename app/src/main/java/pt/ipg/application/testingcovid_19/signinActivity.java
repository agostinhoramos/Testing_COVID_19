package pt.ipg.application.testingcovid_19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class signinActivity  extends Fragment {
    EditText username, password;
    TextView forgotpassword;
    Button signin;
    CheckBox rememberme;
    ImageView image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin,container,false);
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.password);
        signin=view.findViewById(R.id.signin);
        rememberme=view.findViewById(R.id.rememberme);
        forgotpassword=view.findViewById(R.id.forgotpassword);
        image=view.findViewById(R.id.image);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}