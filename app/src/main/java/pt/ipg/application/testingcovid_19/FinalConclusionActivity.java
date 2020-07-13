package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalConclusionActivity extends AppCompatActivity {

    private Button btn_back_to_start;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_conclusion);

        btn_back_to_start = findViewById(R.id.btn_back_to_start);
        btn_back_to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}