package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pt.ipg.application.testingcovid_19.database.table.DBTableAvatar;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;

public class ContentProviderTesting extends android.content.ContentProvider {
    private static final String AUTHORITY = "PT.IPG.APPLICATION.HISTORYINGCOVID_19";

    private static final String DOCTOR = "doctor";
    private static final String QUESTION = "question";
    private static final String CHOICES = "choices";
    private static final String USER = "user";
    private static final String AVATAR = "avatar";
    private static final String USER_CHOICE = "user_choice";
    private static final String HISTORY = "history";
    private static final String FAQ = "faq";

    private static final Uri BASE_ADDRESS = Uri.parse("content://" + AUTHORITY);

    public static final Uri DOCTOR_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, DOCTOR);
    public static final Uri QUESTION_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, QUESTION);
    public static final Uri CHOICES_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, CHOICES);
    public static final Uri USER_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri AVATAR_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, AVATAR);
    public static final Uri USER_CHOICE_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER_CHOICE);
    public static final Uri HISTORY_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, HISTORY);
    public static final Uri FAQ_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, FAQ);

    private static final int URI_DOCTOR = 100;
    private static final int URI_ID_DOCTOR = 101;

    private static final int URI_QUESTION = 200;
    private static final int URI_ID_QUESTION = 201;

    private static final int URI_CHOICES = 300;
    private static final int URI_ID_CHOICES= 301;

    private static final int URI_USER = 400;
    private static final int URI_ID_USER = 401;

    private static final int URI_AVATAR = 500;
    private static final int URI_ID_AVATAR = 501;

    private static final int URI_USER_CHOICE = 600;
    private static final int URI_ID_USER_CHOICE = 601;

    private static final int URI_HISTORY = 700;
    private static final int URI_ID_HISTORY = 701;

    private static final int URI_FAQ = 800;
    private static final int URI_ID_FAQ = 801;

    private static final String CURSOR_DIR = "vdn.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vdn.android.cursor.item/";

    private DatabaseOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, DOCTOR, URI_DOCTOR);
        uriMatcher.addURI(AUTHORITY, DOCTOR +"/#", URI_ID_DOCTOR);

        uriMatcher.addURI(AUTHORITY, QUESTION, URI_QUESTION);
        uriMatcher.addURI(AUTHORITY, QUESTION +"/#", URI_ID_QUESTION);

        uriMatcher.addURI(AUTHORITY, CHOICES, URI_CHOICES);
        uriMatcher.addURI(AUTHORITY, CHOICES +"/#", URI_ID_CHOICES);

        uriMatcher.addURI(AUTHORITY, USER, URI_USER);
        uriMatcher.addURI(AUTHORITY, USER +"/#", URI_ID_USER);

        uriMatcher.addURI(AUTHORITY, AVATAR, URI_AVATAR);
        uriMatcher.addURI(AUTHORITY, AVATAR +"/#", URI_ID_AVATAR);

        uriMatcher.addURI(AUTHORITY, USER_CHOICE, URI_USER_CHOICE);
        uriMatcher.addURI(AUTHORITY, USER_CHOICE +"/#", URI_ID_USER_CHOICE);

        uriMatcher.addURI(AUTHORITY, HISTORY, URI_HISTORY);
        uriMatcher.addURI(AUTHORITY, HISTORY +"/#", URI_ID_HISTORY);

        uriMatcher.addURI(AUTHORITY, FAQ, URI_FAQ);
        uriMatcher.addURI(AUTHORITY, FAQ +"/#", URI_ID_FAQ);

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

            case URI_DOCTOR:
                return new DBTableDoctor(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_DOCTOR:
                return new DBTableDoctor(db).query(projection, DBTableDoctor._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_QUESTION:
                return new DBTableQuestion(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_QUESTION:
                return new DBTableQuestion(db).query(projection, DBTableQuestion._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_CHOICES:
                return new DBTableChoice(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_CHOICES:
                return new DBTableChoice(db).query(projection, DBTableChoice._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_USER:
                return new DBTableUser(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_USER:
                return new DBTableUser(db).query(projection, DBTableUser._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_AVATAR:
                return new DBTableAvatar(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_AVATAR:
                return new DBTableAvatar(db).query(projection, DBTableAvatar._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_USER_CHOICE:
                return new DBTableUserChoice(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_USER_CHOICE:
                return new DBTableUserChoice(db).query(projection, DBTableUserChoice._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_HISTORY:
                return new DBTableHistory(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_HISTORY:
                return new DBTableHistory(db).query(projection, DBTableHistory._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_FAQ:
                return new DBTableFaq(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_FAQ:
                return new DBTableFaq(db).query(projection, DBTableFaq._ID + "=?", new String[] { id }, null, null, sortOrder);

            default:
                throw new UnsupportedOperationException("Invalid query address: " + uri.getPath());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int codigoUri = getUriMatcher().match(uri);

        switch (codigoUri) {
            case URI_DOCTOR:
                return CURSOR_DIR + DOCTOR;
            case URI_ID_DOCTOR:
                return CURSOR_ITEM + DOCTOR;

            case URI_QUESTION:
                return CURSOR_DIR + QUESTION;
            case URI_ID_QUESTION:
                return CURSOR_ITEM + QUESTION;

            case URI_CHOICES:
                return CURSOR_DIR + CHOICES;
            case URI_ID_CHOICES:
                return CURSOR_ITEM + CHOICES;

            case URI_USER:
                return CURSOR_DIR + USER;
            case URI_ID_USER:
                return CURSOR_ITEM + USER;

            case URI_AVATAR:
                return CURSOR_DIR + AVATAR;
            case URI_ID_AVATAR:
                return CURSOR_ITEM + AVATAR;

            case URI_USER_CHOICE:
                return CURSOR_DIR + USER_CHOICE;
            case URI_ID_USER_CHOICE:
                return CURSOR_ITEM + USER_CHOICE;

            case URI_HISTORY:
                return CURSOR_DIR + HISTORY;
            case URI_ID_HISTORY:
                return CURSOR_ITEM + HISTORY;

            case URI_FAQ:
                return CURSOR_DIR + FAQ;
            case URI_ID_FAQ:
                return CURSOR_ITEM + FAQ;

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
            case URI_DOCTOR:
                id = (new DBTableDoctor(bd).insert(values));
                break;

            case URI_QUESTION:
                id = (new DBTableQuestion(bd).insert(values));
                break;

            case URI_CHOICES:
                id = (new DBTableChoice(bd).insert(values));
                break;

            case URI_USER:
                id = (new DBTableUser(bd).insert(values));
                break;

            case URI_AVATAR:
                id = (new DBTableAvatar(bd).insert(values));
                break;

            case URI_USER_CHOICE:
                id = (new DBTableUserChoice(bd).insert(values));
                break;

            case URI_HISTORY:
                id = (new DBTableHistory(bd).insert(values));
                break;

            case URI_FAQ:
                id = (new DBTableFaq(bd).insert(values));
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
            case URI_ID_DOCTOR:
                return new DBTableDoctor(bd).delete(DBTableDoctor._ID + "=?", new String[]{id});

            case URI_ID_QUESTION:
                return new DBTableQuestion(bd).delete(DBTableQuestion._ID + "=?", new String[]{id});

            case URI_ID_CHOICES:
                return new DBTableChoice(bd).delete(DBTableChoice._ID + "=?", new String[]{id});

            case URI_ID_USER:
                return new DBTableUser(bd).delete(DBTableUser._ID + "=?", new String[]{id});

            case URI_ID_AVATAR:
                return new DBTableAvatar(bd).delete(DBTableAvatar._ID + "=?", new String[]{id});

            case URI_ID_USER_CHOICE:
                return new DBTableUserChoice(bd).delete(DBTableUserChoice._ID + "=?", new String[]{id});

            case URI_ID_HISTORY:
                return new DBTableHistory(bd).delete(DBTableHistory._ID + "=?", new String[] { id });

            case URI_ID_FAQ:
                return new DBTableFaq(bd).delete(DBTableFaq._ID + "=?", new String[]{id});

            default:
                throw new UnsupportedOperationException("Invalid delete address: " + uri.getPath());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();
        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_ID_DOCTOR:
                return new DBTableDoctor(bd).update(values,DBTableDoctor._ID + "=?", new String[]{id});

            case URI_ID_QUESTION:
                return new DBTableQuestion(bd).update(values,DBTableQuestion._ID + "=?", new String[]{id});

            case URI_ID_CHOICES:
                return new DBTableChoice(bd).update(values, DBTableChoice._ID + "=?", new String[]{id});

            case URI_ID_USER:
                return new DBTableUser(bd).update(values,DBTableUser._ID + "=?", new String[]{id});

            case URI_ID_AVATAR:
                return new DBTableAvatar(bd).update(values,DBTableAvatar._ID + "=?", new String[]{id});

            case URI_ID_USER_CHOICE:
                return new DBTableUserChoice(bd).update(values, DBTableUserChoice._ID + "=?", new String[]{id});

            case URI_ID_HISTORY:
                return new DBTableHistory(bd).update(values, DBTableHistory._ID + "=?", new String[] { id });

            case URI_ID_FAQ:
                return new DBTableFaq(bd).update(values, DBTableFaq._ID + "=?", new String[]{id});

            default:
                throw new UnsupportedOperationException("Invalid update address: " + uri.getPath());
        }
    }
}