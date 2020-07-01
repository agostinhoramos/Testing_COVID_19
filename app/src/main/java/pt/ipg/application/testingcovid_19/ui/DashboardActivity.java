package pt.ipg.application.testingcovid_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.R;

public class DashboardActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private final static int MIN_WEIGHT = 0;
    private final static int MAX_WEIGHT = 5;

    String[] inputType = {"Plain Text", "Input numeric"};
    private Button btn_add_option, btn_save;

    //ArrayList<Button> btn_add_option = new ArrayList<>();
    //ArrayList<Button> btn_save = new ArrayList<>();

    ArrayList<Button> btns = new ArrayList<>();
    ArrayList<TextView> textViews = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();



    private LinearLayout linearLayout;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Spinner spin = (Spinner) findViewById(R.id.spinner_option);
        spin.setOnItemSelectedListener(this);

        linearLayout = (LinearLayout)findViewById(R.id.layout_optionPlace);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, inputType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        btn_add_option = findViewById(R.id.addOption);
        btn_add_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOption();
            }
        });

        btn_save = findViewById(R.id.save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clear all data
                linearLayout.removeAllViews();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), inputType[position] , Toast.LENGTH_LONG).show();
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TextView textView;

    private void addOption(){

        if( position == 0 ){ // TODO add others views type...
            view_plainText(this);
        }
    }

    private int num_option_plainText = 0;
    private void view_plainText(Context context){
        // TODO save fragment status..
        // Create a LayoutParams for TextView
        LayoutParams ll = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, // Width of TextView
                LayoutParams.WRAP_CONTENT // Height of TextView
        );

        LinearLayout nLl = new LinearLayout(context);

        EditText editText = new EditText(context);
        editTexts.add(editText);

        int pos = textViews.size();

        Button button = new Button(context);
        btns.add(button);

        // New button here
        textView = new TextView(context);
        textViews.add(textView);

        button.setLayoutParams(new LinearLayout.LayoutParams(
                80, // Width of TextView
                LayoutParams.WRAP_CONTENT // Height of TextView
        ));

        button.setText("-");
        button.setTag(pos);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int) v.getTag();
                TextView textView = textViews.get(pos);

                int num = Integer.parseInt(textView.getText().toString());
                if( num > MIN_WEIGHT ){
                    textView.setText("" + (num-1));
                }
            }
        });

        nLl.addView(button);

        textView.setText("0");
        nLl.addView(textView);

        button = new Button(context);
        button.setTag(pos);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                80, // Width of TextView
                LayoutParams.WRAP_CONTENT // Height of TextView
        ));

        button.setText("+");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                TextView textView = textViews.get(pos);

                int num = Integer.parseInt(textView.getText().toString());
                if( num < MAX_WEIGHT ){
                    textView.setText("" + (num+1));
                }
            }
        });

        nLl.addView(button);
        btns.add(button);

        EditText INPUT = new EditText(context);
        editTexts.add(INPUT);
        INPUT.setHint("Option " + (num_option_plainText+1));

        // Apply the layout parameters to TextView widget
        INPUT.setLayoutParams(ll);
        nLl.addView(INPUT);

        // ADD View to Layout...
        linearLayout.addView(nLl);
        num_option_plainText++;
    }
}