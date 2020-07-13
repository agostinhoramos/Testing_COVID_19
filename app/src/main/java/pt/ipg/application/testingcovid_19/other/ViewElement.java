package pt.ipg.application.testingcovid_19.other;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import pt.ipg.application.testingcovid_19.R;

public class ViewElement {

    public ViewElement(){ }

    public static String[] type = {};

    public static View add(Context context, String type, final String[] data){
        View view = null;
        if(type=="ToggleButton"){
            view = ToggleButton(context, data[0]);
        }
        if(type=="Number"){
            view = Number(context, data[0]);
        }
        if(type=="CheckBox"){
            view = CheckBox(context, data[0]);
        }
        if(type=="RadioButton"){
            view = RadioButton(context, data[0]);
        }
        if(type=="EditText"){
            view = EditText(context, data[0]);
        }

        view.setTag(data[1]);

        return view;
    }

    public static View Number(Context context, String text){
        EditText view = new EditText(context);
        view.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,5,20,5);
        view.setLayoutParams(params);
        view.setInputType(InputType.TYPE_CLASS_NUMBER);
        view.setGravity(Gravity.CENTER);
        view.setHint(text);
        return view;
    }

    public static View CheckBox(Context context, String text){
        CheckBox view = new CheckBox(context);
        view.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,5,20,5);
        view.setLayoutParams(params);
        view.setText(text);
        return view;
    }

    public static View RadioButton(Context context, String text){
        RadioButton view = new RadioButton(context);
        view.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,5,20,5);
        view.setLayoutParams(params);
        view.setText(text);
        return view;
    }

    public static View EditText(Context context, String text){
        EditText view = new EditText(context);
        view.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,5,20,5);
        view.setLayoutParams(params);
        view.setGravity(Gravity.CENTER);
        view.setHint(text);
        return view;
    }

    public static View ToggleButton(Context context, String text){
        Button view = new Button(new ContextThemeWrapper(context, R.style.simpleButton));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,5,20,5);
        view.setLayoutParams(params);
        view.setText(text);
        view.setBackgroundColor(Color.parseColor("#f3f3f3"));
        view.setTextSize(18);
        return view;
    }
}