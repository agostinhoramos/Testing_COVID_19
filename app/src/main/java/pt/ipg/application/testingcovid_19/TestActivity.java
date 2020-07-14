package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.controllers.QuestionController;
import pt.ipg.application.testingcovid_19.controllers.UIController;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.object.MultipleChoice;

public class TestActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT_FINAL_WEIGHT = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_TEXT_FINAL_WEIGHT";

    private Button btn_next;
    private LinearLayout linearLayout_area;
    private TextView textView_info;
    private TextView textView_question;
    private UIController ui;
    private QuestionController qc;
    private MultipleChoice mc;
    private Intent intent;
    private ArrayList<String> allresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Context appContext = getApplicationContext();

        btn_next = findViewById(R.id.btn_next);
        linearLayout_area = findViewById(R.id.linearLayout_area);
        textView_info = findViewById(R.id.textView_info);
        textView_question = findViewById(R.id.textView_question);
        allresult = new ArrayList<>();

        ui = new UIController(
                appContext,
                btn_next,
                linearLayout_area,
                textView_info,
                textView_question
        );

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        qc = new QuestionController(db);

        // initial question
        mc = qc.Next(1);
        textView_question.setText(mc.getQuestion());
        ui.drawOption(mc.getAnswer());

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allresult.addAll(ui.getResult());
                ui.on_next_click();
                mc = qc.Next(1);
                if( mc != null ){
                    textView_question.setText(mc.getQuestion());
                    ui.drawOption(mc.getAnswer());
                }else{
                    int total_weight = 0;
                    int size = allresult.size();
                    for(int i=0; i<size; i++){
                        total_weight += Integer.parseInt(allresult.get(i));
                    }
                    int final_weight = total_weight/5;
                    intent = new Intent(getApplicationContext(), FinalConclusionActivity.class);
                    intent.putExtra(EXTRA_TEXT_FINAL_WEIGHT, String.valueOf(final_weight));
                    startActivity(intent);
                    qc.clear();
                }
            }
        });
    }
}