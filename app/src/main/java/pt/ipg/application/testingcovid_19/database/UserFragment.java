package pt.ipg.application.testingcovid_19.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.loader.content.CursorLoader;

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;

public class UserFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int ID_CURSOR_LOADER_USERS = 0;

    //LoaderManager.LoaderCallbacks<Cursor>
    // Call when main activity is opened

    private UserAdapter userAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return null;//inflater.inflate(R.layout.user_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        // Call the main activity
        //DashboardUserActivity activity = (DashboardUserActivity) getActivity();
        //activity.setFragmentActual(context);

        //activity.setCurrentMenu(R.menu.menu_lista_livros);

        /*RecyclerView recyclerViewUser = (RecyclerView) view.findViewById(R.id.recyclerViewUser);
        userAdapter = new UserAdapter(context);
        recyclerViewUser.setAdapter(userAdapter);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(context));
        */
        userAdapter.setCursor(null);

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_USERS, null, this);
    }


    // CRUD activity
    public void changeUser() {
        NavController navController = NavHostFragment.findNavController(UserFragment.this);
        //navController.navigate(R.id.action_alterar_livro);
    }

    public void newUSer() {
        NavController navController = NavHostFragment.findNavController(UserFragment.this);
        //navController.navigate(R.id.action_novo_livro);
    }

    public void deleteUser() {
        NavController navController = NavHostFragment.findNavController(UserFragment.this);
        //navController.navigate(R.id.action_eliminar_livro);
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(getContext(), ContentProviderTesting.USER_ADDRESS, DBTableUser.ALL_COLUMN, null, null, DBTableUser.COLUMN_NAME);
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *  @param loader The Loader that has finished.
     *
     * @param data The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        userAdapter.setCursor(data);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        userAdapter.setCursor(null);
    }
}