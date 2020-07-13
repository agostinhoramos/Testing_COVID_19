package pt.ipg.application.testingcovid_19.controllers;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.other.ViewElement;

public class UIController {

    private Button btn_next;
    private LinearLayout linearLayout_area;
    private ArrayList<String> result;
    private int limitSelect;
    private ArrayList<String> allResult;
    private TextView textView_info;
    private TextView textView_question;
    private Context context;
    private int answered;
    private String str_info;

    public UIController(Context context, Button btn_next, LinearLayout area, TextView info, TextView question){
        this.context = context;
        this.btn_next = btn_next;
        this.linearLayout_area = area;
        this.textView_info = info;
        this.textView_question = question;
        this.str_info = "Question answered: ";

        result = new ArrayList<>();
        allResult = new ArrayList<>();
        limitSelect = 1;
        answered = 0;

        //init function
        disableNext();
        textView_info.setText(str_info+answered);
    }

    public void setLimitSelect(int limit){
        this.limitSelect = limit;
        this.auxLimit = limit;
    }

    public ArrayList<String> getResult(){
        return result;
    }

    private ArrayList<View> view = new ArrayList<>();
    int auxLimit = limitSelect;
    public void drawOption(ArrayList<Choice> choice){
        linearLayout_area.removeAllViews();
        for(int i=0; i<choice.size(); i++){
            view.add(ViewElement.add(context,
                choice.get(i).getType(),
                choice.get(i).getChoice(),
                (int)choice.get(i).getWeight()
            ));
            linearLayout_area.addView(view.get(i));
            if(choice.get(i).getType().indexOf("ToggleButton")>=0){
                view.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( auxLimit<= 0 ){
                            deselectOpt();
                            auxLimit = limitSelect;
                        }
                        selectOpt(v);
                        auxLimit--;
                    }
                });
            }
        }
    }

    public void selectOpt(View view){
        view.setBackgroundColor(Color.parseColor("#bdbdbd"));
        view.setClickable(false);
        view.setEnabled(false);
        result.add(view.getTag().toString());
        elementCallback();
    }

    public void deselectOpt(){
        disableNext();
        for (int i=0; i<view.size(); i++){
            view.get(i).setBackgroundColor(Color.parseColor("#f3f3f3"));
            view.get(i).setClickable(true);
            view.get(i).setEnabled(true);
        }
        for (int i=0; i<result.size(); i++){
            result.remove(i);
        }
    }

    public void enableNext(){
        if(result.size() == limitSelect){
            btn_next.setClickable(true);
            btn_next.setEnabled(true);
            btn_next.setBackgroundColor(Color.parseColor("#098aec"));
            btn_next.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void disableNext(){
        btn_next.setClickable(false);
        btn_next.setEnabled(false);
        btn_next.setBackgroundColor(Color.parseColor("#f3f3f3"));
        btn_next.setTextColor(Color.parseColor("#098aec"));
    }

    public void elementCallback(){
        enableNext();
        for (int i=0; i<result.size(); i++){
            System.out.println("R: "+ result.get(i));
        }
    }

    public void on_next_click(){
        deselectOpt();
        answered++;
        textView_info.setText(str_info+answered);
    }
}