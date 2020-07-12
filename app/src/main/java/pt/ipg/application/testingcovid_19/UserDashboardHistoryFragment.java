package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipg.application.testingcovid_19.database.ContentProvider;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;

public class UserDashboardHistoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private View view;
    public static final int LOADER_CURSOR_HISTORY = 0;
    UserHistoryAdapter userHistoryAdapter;
    FloatingActionButton fab_start;
    private Intent intent;

    public UserDashboardHistoryFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard_user_history, container,false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        RecyclerView recyclerViewHistory = (RecyclerView) view.findViewById(R.id.recycleViewUserHistory);
        userHistoryAdapter = new UserHistoryAdapter(context);
        recyclerViewHistory.setAdapter(userHistoryAdapter);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(context));

        fab_start = view.findViewById(R.id.fab_start);
        fab_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), TestActivity.class);
                startActivity(intent);
            }
        });

        userHistoryAdapter.setCursorHistory(null);
        LoaderManager.getInstance(this).initLoader(LOADER_CURSOR_HISTORY, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> cursorLoader = null;
        if(id == LOADER_CURSOR_HISTORY){
            cursorLoader = new CursorLoader(getContext(), ContentProvider.HISTORY_ADDRESS,
                    DBTableHistory.ALL_COLUMN, null, null, DBTableHistory._ID);
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if( id == LOADER_CURSOR_HISTORY ){
            userHistoryAdapter.setCursorHistory(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        int id = loader.getId();
        if( id == LOADER_CURSOR_HISTORY ){
            userHistoryAdapter.setCursorHistory(null);
        }
    }
}