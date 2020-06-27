package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pt.ipg.application.testingcovid_19.database.table.DBTableTest;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;

public class ContentProviderTesting extends android.content.ContentProvider {
    private static final String AUTHORITY = "PT.IPG.APPLICATION.TESTINGCOVID_19";
    private static final String USER = "user";
    private static final String TEST = "test";

    private static final Uri BASE_ADDRESS = Uri.parse("content://" + AUTHORITY);
    public static final Uri USER_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri TEST_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, TEST);

    private static final int URI_USER = 100;
    private static final int URI_ID_USER = 101;
    private static final int URI_TEST = 200;
    private static final int URI_ID_TEST = 201;
    private static final String CURSOR_DIR = "vdn.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vdn.android.cursor.item/";

    private DatabaseOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, USER, URI_USER);
        uriMatcher.addURI(AUTHORITY, USER +"/#", URI_ID_USER);
        uriMatcher.addURI(AUTHORITY, TEST, URI_TEST);
        uriMatcher.addURI(AUTHORITY, TEST +"/#", URI_ID_TEST);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        openHelper = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String id = uri.getLastPathSegment();
        switch (getUriMatcher().match(uri)) {
            case URI_USER:
                return new DBTableUser(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_USER:
                return new DBTableUser(db).query(projection, DBTableUser._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_TEST:
                return new DBTableTest(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_TEST:
                return new DBTableTest(db).query(projection, DBTableTest._ID + "=?", new String[] { id }, null, null, sortOrder);

            default:
                throw new UnsupportedOperationException("Invalid query address: " + uri.getPath());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int codigoUri = getUriMatcher().match(uri);

        switch (codigoUri) {
            case URI_USER:
                return CURSOR_DIR + USER;
            case URI_ID_USER:
                return CURSOR_ITEM + USER;
            case URI_TEST:
                return CURSOR_DIR + TEST;
            case URI_ID_TEST:
                return CURSOR_ITEM + TEST;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        long id;

        switch (getUriMatcher().match(uri)) {
            case URI_USER:
                id = (new DBTableUser(bd).insert(values));
                break;

            case URI_TEST:
                id = (new DBTableTest(bd).insert(values));
                break;

            default:
                throw new UnsupportedOperationException("Invalid insert address: " + uri.getPath());
        }

        if (id == -1) {
            throw new SQLException("Could not insert record: " + uri.getPath());
        }

        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();
        String id = uri.getLastPathSegment();
        switch (getUriMatcher().match(uri)) {
            case URI_ID_USER:
                return new DBTableUser(bd).delete(DBTableUser._ID + "=?", new String[]{id});

            case URI_ID_TEST:
                return new DBTableTest(bd).delete(DBTableTest._ID + "=?", new String[] { id });

            default:
                throw new UnsupportedOperationException("Invalid delete address: " + uri.getPath());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_ID_USER:
                return new DBTableUser(bd).update(values, DBTableUser._ID + "=?", new String[] { id });

            case URI_ID_TEST:
                return new DBTableTest(bd).update(values,DBTableTest._ID + "=?", new String[] { id });

            default:
                throw new UnsupportedOperationException("Invalid update address: " + uri.getPath());
        }
    }
}
