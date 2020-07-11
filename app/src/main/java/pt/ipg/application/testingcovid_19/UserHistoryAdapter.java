package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.database.ContentProvider;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.other.Function;

class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.ViewHolderHistory> {

    private Context context;
    public RecyclerView recyclerView;
    public Function fn;
    private Cursor cursorHistory = null;
    public History history;

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
        
        public ViewHolderHistory(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
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
                //viewHolderSelectedHistory.setBtnInvisible();
            }

            viewHolderSelectedHistory = this;
            //setBtnVisible();
        }

        public void setHistory(History history){
            System.out.println( history.getLevel() );
        }
        
    }

}