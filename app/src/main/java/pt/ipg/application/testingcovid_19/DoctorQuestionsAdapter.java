package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.Question;

class DoctorQuestionsAdapter extends RecyclerView.Adapter<DoctorQuestionsAdapter.ViewHolderQuestion> {

    private Context context;

    private Cursor cursorQuestion = null;
    public void setCursorQuestion(Cursor cursor) {
        if (cursor != this.cursorQuestion) {
            this.cursorQuestion = cursor;
            notifyDataSetChanged();
        }
    }

    private Cursor cursorChoice = null;
    public void setCursorChoice(Cursor cursor) {
        if (cursor != this.cursorChoice) {
            this.cursorChoice = cursor;
            notifyDataSetChanged();
        }
    }

    public DoctorQuestionsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderQuestion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new ViewHolderQuestion(item);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorQuestionsAdapter.ViewHolderQuestion holder, int position) {
        // Display all data from here :)
        cursorQuestion.moveToPosition(position);
        Question question = Convert.cursorToQuestion(cursorQuestion);
        holder.setQuestion(question);
    }

    @Override
    public int getItemCount() {
        if(cursorQuestion == null) {
            return 0;
        }
        return cursorQuestion.getCount();
    }

    private ViewHolderQuestion viewHolderSelectedQuestion = null;

    public class ViewHolderQuestion extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Question question = null;

        private TextView textViewQuestion;
        private TextView textViewChoice;

        private Button btnEdit;
        private Button btnDel;

        private LinearLayout layout_allChoice;

        public ViewHolderQuestion(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = (TextView) itemView.findViewById(R.id.question);
            textViewChoice = (TextView) itemView.findViewById(R.id.nOptions);

            btnEdit = itemView.findViewById(R.id.edit);
            btnDel = itemView.findViewById(R.id.del);

            layout_allChoice = itemView.findViewById(R.id.layout_allChoice);

            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            if (viewHolderSelectedQuestion == this) {
                return;
            }

            if (viewHolderSelectedQuestion != null) {
                viewHolderSelectedQuestion.setBtnInvisible();
            }

            viewHolderSelectedQuestion = this;
            setBtnVisible();

            DoctorDashboardActivity activity = (DoctorDashboardActivity) DoctorQuestionsAdapter.this.context;
            activity.setBtnInvisible();
        }

        public void setBtnVisible(){
            btnEdit.setVisibility(View.VISIBLE);
            btnDel.setVisibility(View.VISIBLE);
            layout_allChoice.setVisibility(View.VISIBLE);
        }
        public void setBtnInvisible(){
            btnEdit.setVisibility(View.GONE);
            btnDel.setVisibility(View.GONE);
            layout_allChoice.setVisibility(View.GONE);
        }

        final String[] letter = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        public void setQuestion(Question question) {
            String q = question.getQuestion();
            long id = question.getId();
            if(!q.isEmpty()){
                textViewQuestion.setText(q);
                int count = 0;
                layout_allChoice.removeAllViews();
                for(int i=0; i<cursorChoice.getCount(); i++){
                    cursorChoice.moveToPosition(i);
                    Choice choice = Convert.cursorToChoice(cursorChoice);
                    if( choice.getFk_question() == id ){
                        count++;
                        TextView myTextView = new TextView(context);
                        myTextView.setPadding(0,10,0,0);
                        String lines;
                        lines = letter[count-1]+") "+choice.getChoice()+" - Weight ~( "+choice.getWeight()+" )";
                        myTextView.setText(lines);
                        layout_allChoice.addView(myTextView);
                    }
                }
                textViewChoice.setText("Options " + count);
            }
        }
    }
}