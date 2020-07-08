package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.ipg.application.testingcovid_19.database.ContentProvider;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;


public class DoctorDashboardListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_CURSOR_QUESTION = 0;
    public static final int LOADER_CURSOR_CHOICE = 1;

    View view;

    DoctorQuestionsAdapter doctorQuestionsAdapter;

    public DoctorDashboardListFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard_doctor_list, container,false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        DoctorDashboardActivity activity = (DoctorDashboardActivity) getActivity();
        activity.setCurrentFragment(this);

        RecyclerView recyclerViewQuestions = (RecyclerView) view.findViewById(R.id.recycleViewDoctorQuestion);
        doctorQuestionsAdapter = new DoctorQuestionsAdapter(context);
        recyclerViewQuestions.setAdapter(doctorQuestionsAdapter);
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(context));

        doctorQuestionsAdapter.setCursorQuestion(null);
        doctorQuestionsAdapter.setCursorChoice(null);

        LoaderManager.getInstance(this).initLoader(LOADER_CURSOR_QUESTION, null, this);
        LoaderManager.getInstance(this).initLoader(LOADER_CURSOR_CHOICE, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> cursorLoader = null;
        if(id == LOADER_CURSOR_QUESTION){
            cursorLoader = new CursorLoader(getContext(), ContentProvider.QUESTION_ADDRESS,
                    DBTableQuestion.ALL_COLUMN, null, null, DBTableQuestion._ID);
        }else if(id == LOADER_CURSOR_CHOICE){
            cursorLoader = new CursorLoader(getContext(), ContentProvider.CHOICES_ADDRESS,
                    DBTableChoice.ALL_COLUMN, null, null, null);
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if( id == LOADER_CURSOR_QUESTION ){
            doctorQuestionsAdapter.setCursorQuestion(data);
        }else if( id == LOADER_CURSOR_CHOICE ){
            doctorQuestionsAdapter.setCursorChoice(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        int id = loader.getId();
        if( id == LOADER_CURSOR_QUESTION ){
            doctorQuestionsAdapter.setCursorQuestion(null);
        }else if( id == LOADER_CURSOR_CHOICE ){
            doctorQuestionsAdapter.setCursorChoice(null);
        }
    }
}