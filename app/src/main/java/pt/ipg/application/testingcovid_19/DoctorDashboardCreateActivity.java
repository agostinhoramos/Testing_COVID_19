package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class DoctorDashboardCreateActivity extends Fragment {

    private final static int MIN_WEIGHT = 0;
    private final static int MAX_WEIGHT = 5;

    String[] inputType = {"Plain Text", "Input numeric"};
    private Button btn_add_option, btn_save;

    ArrayList<Button> btns = new ArrayList<>();
    ArrayList<TextView> textViews = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();
    ArrayList<LinearLayout> ListOptionGroupLayout = new ArrayList<>();

    private LinearLayout linearLayoutRoot;
    private int position = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_doctor_create, container,false);

        linearLayoutRoot = (LinearLayout) view.findViewById(R.id.layout_optionPlace);

        setSpinnerContent(view);

        btn_add_option = view.findViewById(R.id.addOption);
        btn_add_option.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                addOption(view);
            }
        });

        btn_save = view.findViewById(R.id.save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // clear all views from layout
                if( num_option_plainText > 0 ){
                    linearLayoutRoot.removeAllViews();
                    num_option_plainText = 0;
                    Toast.makeText(view.getContext(), "Saved successfully!" , Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addOption(View view){
        if( true || position == 0 ){ // TODO add others views type...
            view_plainText(view.getContext());
        }
    }

    private void setSpinnerContent( View view )
    {
        Spinner spin = (Spinner) view.findViewById(R.id.spinner_option);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, inputType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private TextView textView;
    private int num_option_plainText = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void view_plainText(Context context){
        // TODO save fragment status..
        // Create a LayoutParams for TextView
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT // Height of TextView
        );

        final LinearLayout horizontalGroup = new LinearLayout(context);

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

                if( num > MIN_WEIGHT ){
                    textView.setText("" + (num-1));
                }

                if( num < 0 ){
                    // Delete linear layout by id
                    LinearLayout g =  ListOptionGroupLayout.get(pos);
                    g.removeAllViews();
                }
            }
        });

        horizontalGroup.addView(button);

        textView.setText("0");
        horizontalGroup.addView(textView);

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
                if( num < MAX_WEIGHT ){
                    textView.setText("" + (num+1));
                }
            }
        });

        horizontalGroup.addView(button);
        btns.add(button);

        EditText INPUT = new EditText(context);
        editTexts.add(INPUT);
        INPUT.setHint("Option " + (num_option_plainText+1));

        // Apply the layout parameters to TextView widget
        INPUT.setLayoutParams(ll);
        horizontalGroup.addView(INPUT);
        ListOptionGroupLayout.add(horizontalGroup);

        // ADD View to Layout...
        linearLayoutRoot.addView(horizontalGroup);
        num_option_plainText++;
    }
}