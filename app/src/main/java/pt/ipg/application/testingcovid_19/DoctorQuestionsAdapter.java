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
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.other.Function;

class DoctorQuestionsAdapter extends RecyclerView.Adapter<DoctorQuestionsAdapter.ViewHolderQuestion> {

    public static final String EXTRA_ID_QUESTION = "PT.IPG.APPLICATION.TESTINGCOVID_19.EXTRA_ID_QUESTION";
    private Context context;
    public final String[] letter = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                                    "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public int numQuestion;
    public RecyclerView recyclerView;
    public Question question = null;
    public Function fn;
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
        numQuestion = -1;
        fn = new Function();
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
        int len = cursorQuestion.getCount();
        int maxPos = len-1;
        if(numQuestion == -1){
            numQuestion = len;
        }
        cursorQuestion.moveToPosition(maxPos-position);
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
            recyclerView = itemView.findViewById(R.id.recycleViewDoctorQuestion);
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

        public void setQuestion(Question myQuestion) {
            question = myQuestion;
            String str_question = myQuestion.getQuestion();
            long id = myQuestion.getId();

            if(!str_question.isEmpty()){
                str_question = fn.Capitalize(str_question);
                textViewQuestion.setText(numQuestion + " - " + str_question);
                int count = 0;
                layout_allChoice.removeAllViews();
                for(int i=0; i<cursorChoice.getCount(); i++){
                    cursorChoice.moveToPosition(i);
                    Choice choice = Convert.cursorToChoice(cursorChoice);
                    if( choice.getFk_question() == id ){
                        TextView myTextView = new TextView(context);
                        myTextView.setPadding(0, 10, 0, 0);
                        count++;
                        String lines = letter[count - 1] + ") " + choice.getChoice()
                                + " - Weight ~( " + choice.getWeight() + " )";
                        myTextView.setText(lines);
                        layout_allChoice.addView(myTextView);
                    }
                }

                textViewChoice.setText("Options " + count);
                numQuestion = numQuestion - 1;

                // BUTTONS CONTROLS
                Button btnEdit = itemView.findViewById(R.id.edit);
                btnEdit.setTag(id);
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = v.getTag().toString();
                        Intent intent = new Intent(v.getContext(), DashboardDoctorEditActivity.class);
                        intent.putExtra(EXTRA_ID_QUESTION, id);
                        context.startActivity(intent);
                    }
                });
                Button btnDel = itemView.findViewById(R.id.del);
                ArrayList<String> data = new ArrayList<>();
                data.add(0, String.valueOf(id));
                data.add(1, fn.Capitalize(str_question));
                btnDel.setTag(data);
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAlert(v.getContext(), (ArrayList<String>) v.getTag());
                    }
                });
            }
        }
    }

    public void deleteAlert(Context context, final ArrayList<String> questionData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete the question");
        String html = "Are you sure you want to delete the <b>"+questionData.get(1)+"</b> question?";
        builder.setMessage(Html.fromHtml(html));
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setPositiveButton("Yes, delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Will delete id:"+questionData.get(0));
                deleteQuestion(Integer.parseInt(questionData.get(0)));
            }
        });
        builder.setNegativeButton("No, cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Canceled
            }
        });
        builder.show();
    }

    private void deleteQuestion(int id) {
        try {
            Uri questionAddress = Uri.withAppendedPath(ContentProvider.QUESTION_ADDRESS, String.valueOf(id));
            int deleted = context.getContentResolver().delete(questionAddress, null, null);
            // TODO DELETE CHOICE TABLE USING QUESTION ID
            if (deleted == 1) {
                Toast.makeText(context, "Question successfully deleted", Toast.LENGTH_SHORT).show();
                // TODO RELOAD RECYCLERVIEW
                DoctorDashboardActivity activity = (DoctorDashboardActivity) DoctorQuestionsAdapter.this.context;
                activity.refreshActivity();
            }
        } catch (Exception e) {}
    }
}