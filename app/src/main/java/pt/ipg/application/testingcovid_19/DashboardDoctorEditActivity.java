package pt.ipg.application.testingcovid_19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import pt.ipg.application.testingcovid_19.database.ContentProvider;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.Question;

public class DashboardDoctorEditActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private String[] optionType;
    private int position = -1;
    private int id_question;
    private Button btn_add_option, btn_update;
    private EditText editTextQuestion;

    // it's depend
    private boolean update_condition = true;

    // local variable
    private String local_question;
    private String[] local_option;
    private Integer[] local_weight;
    private String[] local_type;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_doctor_edit);
        optionType = DoctorDashboardCreateFragment.optionType; // call from another class
        setSpinnerContent();
        Intent intent = getIntent();
        String extra_id_question = intent.getStringExtra(DoctorQuestionsAdapter.EXTRA_ID_QUESTION);
        linearLayoutRoot = findViewById(R.id.layout_optionPlace);
        id_question = Integer.parseInt(extra_id_question);
        btn_add_option = findViewById(R.id.addOption);
        btn_update = findViewById(R.id.save);
        editTextQuestion = findViewById(R.id.question);
        editTextQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("Changed..");
                btn_update.setEnabled(true);
            }
        });
        setQuestion();
        btn_add_option = findViewById(R.id.addOption);
        btn_add_option.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                if( position == 0 ){
                    make_ToggleButton(getApplicationContext(), null);
                }
                // TODO conditions...
                btn_update.setEnabled(true);
            }
        });
        btn_update = findViewById(R.id.save);
        btn_update.setText("Update");
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verify if is valid information
                verifyCondition(position);

                if( update_condition ){
                    if( position == 0 ){
                        ToggleButtonToVariable();
                    }

                    // Make change..
                    updateQuestion(local_question, local_option, local_weight, local_type);

                    // clear all views from layout
                    if( num_option > 0 ){
                        editTextQuestion.setText("");
                        linearLayoutRoot.removeAllViews();
                        num_option = 0;
                    }

                    // turn back to another page...
                    Intent intent = new Intent(getApplicationContext(), DoctorDashboardActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Error in the fields please check again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateQuestion(String question, String[] option, Integer[] weight, String[] type){
        int id_doctor = 1;

        Question obj_question = new Question();
        obj_question.setId(id_question);
        obj_question.setQuestion(question);
        obj_question.setFk_doctor(id_doctor);

        try {
            ContentResolver resolver = getContentResolver();
            Uri questionAddress = Uri.withAppendedPath(ContentProvider.QUESTION_ADDRESS, String.valueOf(id_question));
            int result = resolver.update(questionAddress, Convert.questionToContentValues(obj_question), null, null);
            if( result == 1 ){
                // Delete first
                String[] arg = {String.valueOf(id_question)};
                Context appContext = getApplicationContext();
                DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
                SQLiteDatabase db = openHelper.getWritableDatabase();
                DBTableChoice tableChoice = new DBTableChoice(db);
                tableChoice.delete(DBTableChoice.COLUMN_FK_QUESTION+"=?", arg);

                // insert after
                for(int i=0; i<option.length; i++){
                    Choice obj_choice = new Choice();
                    obj_choice.setChoice(option[i]);
                    obj_choice.setWeight(weight[i]);
                    obj_choice.setType(type[i]);
                    obj_choice.setFk_question(id_question);
                    resolver.insert(ContentProvider.CHOICES_ADDRESS, Convert.choicesToContentValues(obj_choice));
                }

                Toast.makeText(getApplicationContext(), "Successfully modified!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) { System.out.println("Something went wrong..."); }

    }

    private void ToggleButtonToVariable(){
        local_question = editTextQuestion.getText().toString();
        int len = editTextOption.size(); // This array list must be updated..
        local_option = new String[len];
        local_type = new String[len];
        for(int i=0; i<len; i++){
            local_option[i] = editTextOption.get(i).getText().toString();
            local_type[i] = "0"; //TODO define type of option..
        }
        len = textViewWeight.size();
        local_weight = new Integer[len];
        for(int i=0; i<len; i++){
            local_weight[i] = Integer.parseInt(textViewWeight.get(i).getText().toString());
        }
    }

    private void verifyCondition(int pos){
        update_condition = true;
        String question = editTextQuestion.getText().toString().trim();
        if(question.isEmpty()|| question.length() < 3){
            update_condition = false;
        }
        if(pos == 0){
            int len = editTextOption.size();
            if(len < 2){
                update_condition = false;
            }
            for(int i=0; i<len; i++){
                String opt = editTextOption.get(i).getText().toString().trim();
                if(opt.isEmpty()||opt.length()<1){
                    update_condition = false;
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(this.position > -1){
            Toast.makeText(getApplicationContext(), optionType[position] , Toast.LENGTH_LONG).show();
        }
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void setSpinnerContent() {
        Spinner spin = (Spinner) findViewById(R.id.spinner_option);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, optionType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setQuestion(){
        Context appContext = getApplicationContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DBTableQuestion tableQuestion = new DBTableQuestion(db);
        String[] arg = {String.valueOf(id_question)};
        Cursor cursor = tableQuestion.query(DBTableQuestion.ALL_COLUMN, DBTableQuestion._ID+"=?", arg, null, null, null);
        int register = cursor.getCount();
        for(int i=0; i<register; i++){
            cursor.moveToPosition(i);
            String question = cursor.getString(cursor.getColumnIndex(DBTableQuestion.COLUMN_QUESTION));
            editTextQuestion.setText(question);
        }
        cursor.close();
        DBTableChoice tableChoice = new DBTableChoice(db);
        cursor = tableChoice.query(DBTableChoice.ALL_COLUMN, DBTableChoice.COLUMN_FK_QUESTION+"=?", arg, null, null, null);
        register = cursor.getCount();
        for(int i=0; i<register; i++){
            cursor.moveToPosition(i);
            make_ToggleButton(getApplicationContext(), cursor); // TODO dynamically
        }
        cursor.close();
        btn_update.setEnabled(false);
    }

    private ArrayList<TextView> textViewWeight = new ArrayList<>();
    private ArrayList<Button> btnMoreAndLess = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private ArrayList<EditText> editTextOption = new ArrayList<>();
    private ArrayList<LinearLayout> ListOptionGroupLayout = new ArrayList<>();
    private LinearLayout linearLayoutRoot;
    private int num_option = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void make_ToggleButton(Context context, Cursor cursor){

        String choice = "";
        //String type = "";
        int weight = 0;

        if( cursor != null ){
            choice = cursor.getString(cursor.getColumnIndex(DBTableChoice.COLUMN_CHOICE));
            //type = cursor.getString(cursor.getColumnIndex(DBTableChoice.COLUMN_TYPE)); //TODO
            weight = (int) cursor.getLong(cursor.getColumnIndex(DBTableChoice.COLUMN_WEIGHT));
        }

        System.out.println(""+choice);
        System.out.println(""+weight);


        // TODO save fragment status..
        // Create a LayoutParams for TextView
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT // Height of TextView
        );

        LinearLayout horizontalGroup = new LinearLayout(context);

        // Save BUTTON on btnMoreAndLess
        Button button = new Button(context);
        btnMoreAndLess.add(button);

        // New textView for weight...
        int pos = textViews.size();
        TextView textViewWeight = new TextView(context);
        textViews.add(textViewWeight);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                80, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT // Height of TextView
        ));
        button.setText("-");
        button.setTag(pos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                TextView textView = textViews.get(pos);
                int num = Integer.parseInt(textView.getText().toString());
                if( num > DoctorDashboardCreateFragment.MIN_WEIGHT ){
                    textView.setText("" + (num-1));
                }
                if( num < 1 ){
                    // Delete linear layout by id
                    LinearLayout layout =  ListOptionGroupLayout.get(pos);
                    layout.removeAllViews();
                    editTextOption.remove(pos); // delete the previews array position
                }
                btn_update.setEnabled(true);
            }
        });
        horizontalGroup.addView(button);
        textViewWeight.setText(String.valueOf(weight));
        horizontalGroup.addView(textViewWeight);
        this.textViewWeight.add(textViewWeight);
        button = new Button(context);
        button.setTag(pos);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                80, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT // Height of TextView
        ));
        button.setText("+");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                TextView textView = textViews.get(pos);
                int num = Integer.parseInt(textView.getText().toString());
                if( num < DoctorDashboardCreateFragment.MAX_WEIGHT ){
                    textView.setText("" + (num+1));
                }
                btn_update.setEnabled(true);
            }
        });

        // Save BUTTON on btnMoreAndLess and Horizontal Layout
        horizontalGroup.addView(button);
        btnMoreAndLess.add(button);

        EditText INPUT = new EditText(context);
        INPUT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                btn_update.setEnabled(true);
            }
        });

        INPUT.setHint("Option " + (num_option+1));
        INPUT.setText(choice);

        // Apply the layout parameters to TextView widget
        INPUT.setLayoutParams(ll);
        horizontalGroup.addView(INPUT);
        editTextOption.add(INPUT);
        ListOptionGroupLayout.add(horizontalGroup);

        // ADD View to Layout...
        linearLayoutRoot.addView(horizontalGroup);
        num_option++;
    }
}