package pt.ipg.application.testingcovid_19.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.DashboardUserActivity;
import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.controllers.QuestionController;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.object.NewQuestion;
import pt.ipg.application.testingcovid_19.object.QuestionChoices;

public class TestActivity extends AppCompatActivity {

    private Fragment currentFragment = null;
    private TextView textViewNumAsk;
    private TextView textViewAsk;

    private Button buttonConfirmNext;

    private int rows;
    private String uName;
    private String uEmail;

    private SQLiteDatabase Database;

    private ArrayList<RadioButton> rb = new ArrayList<>();

    private LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textViewNumAsk = findViewById(R.id.NumAsk);
        textViewAsk = findViewById(R.id.Ask);
        buttonConfirmNext = findViewById(R.id.ConfirmNext);

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(this);
        Database = openHelper.getWritableDatabase();

        linearLayout = (LinearLayout)findViewById(R.id.layout_autoAsk);

        draw(this);
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                draw(v.getContext());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void draw(Context context){
        linearLayout.removeAllViews();
        QuestionController questionController = new QuestionController(Database);
        int id_user = 0;
        NewQuestion nq = questionController.Next(id_user);
        if( nq != null ){
            textViewAsk.setText( nq.getQuestion() );
            for(int i=0; i<nq.getAnswer().size(); i++){
                view_plainText(context, nq.getAnswer().get(i).getChoice());
            }
        }else{
            Intent intent = new Intent(context, DashboardUserActivity.class);
            startActivity(intent);
            questionController.clear();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void view_plainText(Context context, String text){

        LinearLayout nLl = new LinearLayout(context);

        Button button = new Button(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT // Height of TextView
        );
        params.setMargins(0,0,0,10);
        button.setLayoutParams(params);
        button.setText(text);
        button.setPadding(12, 12, 12, 12);
        button.setTextAlignment(LinearLayout.TEXT_ALIGNMENT_CENTER);
        button.setBackground(getResources().getDrawable(R.drawable.buttons_background));
        button.setTextSize(0, 40);
        button.setTextColor(getResources().getColor(R.color.colorWhite));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nLl.addView(button);
        linearLayout.addView(nLl);
    }

}