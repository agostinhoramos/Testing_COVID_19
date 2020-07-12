package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.other.DateTimeStyle;
import pt.ipg.application.testingcovid_19.other.DateTimeUtils;
import pt.ipg.application.testingcovid_19.other.Function;

class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.ViewHolderHistory> {

    private Context context;
    public RecyclerView recyclerView;
    public Function fn;
    private Cursor cursorHistory = null;
    public History history;

    public static String[] type_color = {"#34E4E4E4","#26FFCD43","#26DD5246"};
    public static String[] type_level = {"Mild","Severe","Very severe"};
    public static String[][] recommendation = {
            {"Apparently look good. Avoid from closed places.", "You must stay at home."},
            {"HANDS Wash frequently","ELBOW Use to cough","FACE Don't touch","SPACE Maintains safe distance","HOME Stay if possible"},
            {"You must stay at home until you have prior authorization from the doctor.","We will contact you as soon as possible."}
    };

    public void setCursorHistory(Cursor cursor) {
        if (cursor != this.cursorHistory) {
            this.cursorHistory = cursor;
            notifyDataSetChanged();
        }
    }

    public UserHistoryAdapter(Context context) {
        this.context = context;
        fn = new Function();
    }

    @NonNull
    @Override
    public ViewHolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_user_history, parent, false);
        return new ViewHolderHistory(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHistoryAdapter.ViewHolderHistory holder, int position) {
        // Display all data from here :)
        int len = cursorHistory.getCount();
        int maxPos = len-1;
        cursorHistory.moveToPosition(maxPos-position);
        History history = Convert.cursorToHistory(cursorHistory);
        holder.setHistory(history);
    }

    @Override
    public int getItemCount() {
        if(cursorHistory == null) {
            return 0;
        }
        return cursorHistory.getCount();
    }

    private ViewHolderHistory viewHolderSelectedHistory = null;
    public class ViewHolderHistory extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView_level;
        private TextView textView_date;
        private LinearLayout linearLayout_main_label;
        private LinearLayout linearLayout_detail;

        public ViewHolderHistory(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            textView_level = itemView.findViewById(R.id.textView_level);
            textView_date = itemView.findViewById(R.id.textView_date);
            linearLayout_main_label = itemView.findViewById(R.id.linearLayout_main_label);
            linearLayout_detail = itemView.findViewById(R.id.linearLayout_detail);
        }

        /**
         * Called when a view has been clicked.
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            if (viewHolderSelectedHistory == this) {
                return;
            }
            if (viewHolderSelectedHistory != null) {
                viewHolderSelectedHistory.setInvisible();
            }

            viewHolderSelectedHistory = this;
            setVisible();
        }

        public void setHistory(History history){
            String level = history.getLevel();
            String date = history.getDate();
            if(level.indexOf("0")>-1){
                linearLayout_main_label.setBackgroundColor(Color.parseColor(type_color[0]));
            }
            if(level.indexOf("1")>-1){
                linearLayout_main_label.setBackgroundColor(Color.parseColor(type_color[1]));
            }
            if(level.indexOf("2")>-1){
                linearLayout_main_label.setBackgroundColor(Color.parseColor(type_color[2]));
            }
            int num_level = Integer.parseInt(level);
            textView_level.setText(type_level[num_level]);
            String beautyDate = DateTimeUtils.getTimeAgo(context, fn.StringToDate(date), DateTimeStyle.AGO_FULL_STRING);
            textView_date.setText(beautyDate);
            linearLayout_detail.removeAllViews();
            for(int i=0; i<recommendation[num_level].length; i++){
                TextView myTextView = new TextView(context);
                myTextView.setPadding(0, 10, 0, 0);
                myTextView.setText((i+1)+") " + recommendation[num_level][i]);
                linearLayout_detail.addView(myTextView);
            }
        }

        public void setVisible(){
            linearLayout_detail.setVisibility(View.VISIBLE);
        }
        public void setInvisible(){
            linearLayout_detail.setVisibility(View.GONE);
        }
    }

}