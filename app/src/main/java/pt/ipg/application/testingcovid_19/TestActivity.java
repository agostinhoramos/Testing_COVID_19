package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.controllers.UIController;
import pt.ipg.application.testingcovid_19.other.ViewElement;

public class TestActivity extends AppCompatActivity {

    private Button btn_next;
    private LinearLayout linearLayout_area;
    private TextView textView_info;
    private TextView textView_question;
    private UIController ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn_next = findViewById(R.id.btn_next);
        linearLayout_area = findViewById(R.id.linearLayout_area);
        textView_info = findViewById(R.id.textView_info);
        textView_question = findViewById(R.id.textView_question);

        ui = new UIController(
                getApplicationContext(),
                btn_next,
                linearLayout_area,
                textView_info,
                textView_question
        );

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ui.on_next_click();
                System.out.println("Next button clicked!");
            }
        });

        String[][] data = {{"A","0"},{"B","2"},{"C","1"}};
        String[] type = {"ToggleButton","ToggleButton","ToggleButton"};
        ui.drawOption(data, type);
    }
}