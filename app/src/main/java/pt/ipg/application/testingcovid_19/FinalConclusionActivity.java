package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalConclusionActivity extends AppCompatActivity {

    private Button btn_back_to_start;
    private TextView tv_level;
    private TextView tv_message;
    private Intent intent;

    private String message;
    private String conclusion_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_conclusion);

        message = "Level: ";
        conclusion_message = "According to the symptoms you indicated, you were assessed with level:";

        btn_back_to_start = findViewById(R.id.btn_back_to_start);
        tv_level = findViewById(R.id.tv_level);
        tv_message = findViewById(R.id.tv_message);

        btn_back_to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        tv_level.setText(message);
        tv_message.setText(conclusion_message);
    }
}