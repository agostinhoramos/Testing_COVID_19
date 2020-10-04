package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ApplicationStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseActivityOnStart();
    }

    private void chooseActivityOnStart(){
        Intent intent = null;
        int page_id = -1;

        switch(page_id) {
            case 0:
                intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(getApplicationContext(), TermsConditionsActivity.class);
                startActivity(intent);
        }
    }
}