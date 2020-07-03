package pt.ipg.application.testingcovid_19.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.R;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {
    Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor){
        if(cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }
    public UserAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemUser = LayoutInflater.from(context).inflate(R.layout.user_adapter, parent, false);
        return new ViewHolderUser(itemUser);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolderUser holder, int position) {
        cursor.moveToPosition(position);
        User user = Convert.cursorToUser(cursor);
        holder.setUser(user);
    }

    @Override
    public int getItemCount() {
        if(cursor != null){
            return 0;
        }
        return cursor.getCount();
    }

    private ViewHolderUser viewHolderUserSelected = null;

    public class ViewHolderUser extends RecyclerView.ViewHolder implements View.OnClickListener{
        private User user = null;
        TextView textViewName;
        TextView textViewGender;
        TextView textViewTIN;
        TextView textViewEmail;
        TextView textViewPhone;
        TextView textViewBirthday;
        TextView textViewDistrict;
        TextView textViewCountry;

        public ViewHolderUser(@NonNull View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.user_name);
            textViewGender = (TextView) itemView.findViewById(R.id.user_gender);
            /*textViewTIN = (TextView) itemView.findViewById(R.id.user_);
            textViewEmail = (TextView) itemView.findViewById();
            textViewPhone = (TextView) itemView.findViewById();
            textViewBirthday = (TextView) itemView.findViewById();
            textViewDistrict = (TextView) itemView.findViewById();
            textViewCountry = (TextView) itemView.findViewById();*/
            itemView.setOnClickListener(this);
        }

        public void setUser(User user){
            this.user = user;
            textViewName.setText(user.getName());
            textViewGender.setText(user.getGender());
        }

        @Override
        public void onClick(View v){
            if(viewHolderUserSelected == this){
                return;
            }
            if(viewHolderUserSelected != null){
                viewHolderUserSelected.unChecked();
            }
            viewHolderUserSelected = this;
            checked();
        }

        private void unChecked() {
            itemView.setBackgroundResource(R.color.colorAccent);
        }

        private void checked(){
            itemView.setBackgroundResource(android.R.color.white);
        }
    }
}