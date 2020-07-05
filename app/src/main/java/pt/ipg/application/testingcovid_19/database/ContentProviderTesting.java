package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;

public class ContentProviderTesting extends android.content.ContentProvider {
    private static final String AUTHORITY = "PT.IPG.APPLICATION.TESTINGCOVID_19";

    private static final String DOCTOR = "doctor";
    private static final String QUESTION = "question";
    private static final String QUESTIONCHOICES = "questionchoices";
    private static final String USER = "user";
    private static final String USERQUESTIONANSWER = "userquestionanswer";
    private static final String TEST = "test";
    private static final String FAQS = "faqs";

    private static final Uri BASE_ADDRESS = Uri.parse("content://" + AUTHORITY);

    public static final Uri DOCTOR_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri QUESTION_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri QUESTIONCHOICES_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri USER_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri USERQUESTIONANSWER_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);
    public static final Uri TEST_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, TEST);
    public static final Uri FAQS_ADDRESS = Uri.withAppendedPath(BASE_ADDRESS, USER);

    private static final int URI_DOCTOR = 100;
    private static final int URI_ID_DOCTOR = 101;

    private static final int URI_QUESTION = 200;
    private static final int URI_ID_QUESTION = 201;

    private static final int URI_QUESTIONCHOICES = 300;
    private static final int URI_ID_QUESTIONCHOICES= 301;

    private static final int URI_USER = 400;
    private static final int URI_ID_USER = 401;

    private static final int URI_USERQUESTIONANSWER = 500;
    private static final int URI_ID_USERQUESTIONANSWER = 501;

    private static final int URI_TEST = 600;
    private static final int URI_ID_TEST = 601;

    private static final int URI_FAQS = 700;
    private static final int URI_ID_FAQS = 701;

    private static final String CURSOR_DIR = "vdn.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vdn.android.cursor.item/";

    private DatabaseOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, DOCTOR, URI_DOCTOR);
        uriMatcher.addURI(AUTHORITY, DOCTOR +"/#", URI_ID_DOCTOR);

        uriMatcher.addURI(AUTHORITY, QUESTION, URI_QUESTION);
        uriMatcher.addURI(AUTHORITY, QUESTION +"/#", URI_ID_QUESTION);

        uriMatcher.addURI(AUTHORITY, QUESTIONCHOICES, URI_QUESTIONCHOICES);
        uriMatcher.addURI(AUTHORITY, QUESTIONCHOICES +"/#", URI_ID_QUESTIONCHOICES);

        uriMatcher.addURI(AUTHORITY, USER, URI_USER);
        uriMatcher.addURI(AUTHORITY, USER +"/#", URI_ID_USER);

        uriMatcher.addURI(AUTHORITY, USERQUESTIONANSWER, URI_USERQUESTIONANSWER);
        uriMatcher.addURI(AUTHORITY, USERQUESTIONANSWER +"/#", URI_ID_USERQUESTIONANSWER);

        uriMatcher.addURI(AUTHORITY, TEST, URI_TEST);
        uriMatcher.addURI(AUTHORITY, TEST +"/#", URI_ID_TEST);

        uriMatcher.addURI(AUTHORITY, FAQS, URI_FAQS);
        uriMatcher.addURI(AUTHORITY, FAQS +"/#", URI_ID_FAQS);

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

            case URI_QUESTIONCHOICES:
                return new DBTableChoice(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_QUESTIONCHOICES:
                return new DBTableChoice(db).query(projection, DBTableChoice._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_USER:
                return new DBTableUser(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_USER:
                return new DBTableUser(db).query(projection, DBTableUser._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_USERQUESTIONANSWER:
                return new DBTableUserChoice(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_USERQUESTIONANSWER:
                return new DBTableUserChoice(db).query(projection, DBTableUserChoice._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_TEST:
                return new DBTableHistory(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_TEST:
                return new DBTableHistory(db).query(projection, DBTableHistory._ID + "=?", new String[] { id }, null, null, sortOrder);

            case URI_FAQS:
                return new DBTableFaq(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_FAQS:
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

            case URI_QUESTIONCHOICES:
                return CURSOR_DIR + QUESTIONCHOICES;
            case URI_ID_QUESTIONCHOICES:
                return CURSOR_ITEM + QUESTIONCHOICES;

            case URI_USER:
                return CURSOR_DIR + USER;
            case URI_ID_USER:
                return CURSOR_ITEM + USER;

            case URI_USERQUESTIONANSWER:
                return CURSOR_DIR + USERQUESTIONANSWER;
            case URI_ID_USERQUESTIONANSWER:
                return CURSOR_ITEM + USERQUESTIONANSWER;

            case URI_TEST:
                return CURSOR_DIR + TEST;
            case URI_ID_TEST:
                return CURSOR_ITEM + TEST;

            case URI_FAQS:
                return CURSOR_DIR + FAQS;
            case URI_ID_FAQS:
                return CURSOR_ITEM + FAQS;

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

            case URI_QUESTIONCHOICES:
                id = (new DBTableChoice(bd).insert(values));
                break;

            case URI_USER:
                id = (new DBTableUser(bd).insert(values));
                break;

            case URI_USERQUESTIONANSWER:
                id = (new DBTableUserChoice(bd).insert(values));
                break;

            case URI_TEST:
                id = (new DBTableHistory(bd).insert(values));
                break;

            case URI_FAQS:
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

            case URI_ID_QUESTIONCHOICES:
                return new DBTableChoice(bd).delete(DBTableChoice._ID + "=?", new String[]{id});

            case URI_ID_USER:
                return new DBTableUser(bd).delete(DBTableUser._ID + "=?", new String[]{id});

            case URI_ID_USERQUESTIONANSWER:
                return new DBTableUserChoice(bd).delete(DBTableUserChoice._ID + "=?", new String[]{id});

            case URI_ID_TEST:
                return new DBTableHistory(bd).delete(DBTableHistory._ID + "=?", new String[] { id });

            case URI_ID_FAQS:
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

            case URI_ID_QUESTIONCHOICES:
                return new DBTableChoice(bd).update(values, DBTableChoice._ID + "=?", new String[]{id});

            case URI_ID_USER:
                return new DBTableUser(bd).update(values,DBTableUser._ID + "=?", new String[]{id});

            case URI_ID_USERQUESTIONANSWER:
                return new DBTableUserChoice(bd).update(values, DBTableUserChoice._ID + "=?", new String[]{id});

            case URI_ID_TEST:
                return new DBTableHistory(bd).update(values, DBTableHistory._ID + "=?", new String[] { id });

            case URI_ID_FAQS:
                return new DBTableFaq(bd).update(values, DBTableFaq._ID + "=?", new String[]{id});

            default:
                throw new UnsupportedOperationException("Invalid update address: " + uri.getPath());
        }
    }
}