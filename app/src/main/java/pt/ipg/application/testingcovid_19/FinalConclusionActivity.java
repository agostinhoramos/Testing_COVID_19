package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import pt.ipg.application.testingcovid_19.database.ContentProvider;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.other.Function;

public class FinalConclusionActivity extends AppCompatActivity {

    private Button btn_back_to_start;
    private TextView tv_level;
    private TextView tv_message;
    private Intent intent;

    private String message;
    private String conclusion_message;

    private SQLiteDatabase db;
    private Function fn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_conclusion);

        fn = new Function();

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

        Intent intent = new Intent();
        String newLevel = "";

        String get_data = intent.getStringExtra(TestActivity.EXTRA_TEXT_FINAL_WEIGHT);
        if(get_data == null){
            get_data = "1";
        }
        int final_weight = Integer.parseInt(get_data);
        if( final_weight < 1 ){
            newLevel = "Mild";
        }else if(final_weight >= 1 && final_weight < 4){
            newLevel = "Severe";
        }else{
            newLevel = "Very severe";
        }
        tv_level.setText(message+newLevel);
        tv_message.setText(conclusion_message);

        int id_user = 1;
        History obj_history = new History();
        obj_history.setDate(fn.DateToString(new Date()));
        obj_history.setLevel(String.valueOf(final_weight));
        obj_history.setFk_user(id_user);

        try {
            getApplicationContext().getContentResolver().insert(ContentProvider.HISTORY_ADDRESS, Convert.historyToContentValues(obj_history));
            Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error add history", Toast.LENGTH_SHORT).show();
        }

    }
}