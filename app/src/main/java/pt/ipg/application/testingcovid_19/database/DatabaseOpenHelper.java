package pt.ipg.application.testingcovid_19.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import pt.ipg.application.testingcovid_19.data.Seed;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFAQs;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestionChoices;
import pt.ipg.application.testingcovid_19.database.table.DBTableTest;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserQuestionAnswer;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String NAME_DATABASE = "covid_19.db";
    private static final int VERSION_DATABASE = 1;
    private static final boolean DEVELOPER = true;

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        DBTableDoctor doctorTable = new DBTableDoctor(db);
        doctorTable.create();

        DBTableQuestion questionTable = new DBTableQuestion(db);
        questionTable.create();

        DBTableQuestionChoices questionChoicesTable = new DBTableQuestionChoices(db);
        questionChoicesTable.create();

        DBTableUser userTable = new DBTableUser(db);
        userTable.create();

        DBTableUserQuestionAnswer userQuestionAnswerTable = new DBTableUserQuestionAnswer(db);
        userQuestionAnswerTable.create();

        DBTableTest testTable = new DBTableTest(db);
        testTable.create();

        DBTableFAQs faqsTable = new DBTableFAQs(db);
        faqsTable.create();

        if ( DEVELOPER ) {
            Seed seed = new Seed(db);
            seed.load();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
