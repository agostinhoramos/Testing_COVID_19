package pt.ipg.application.testingcovid_19.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import pt.ipg.application.testingcovid_19.data.Seed;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String NAME_DATABASE = "covid_19.db";
    private static final int VERSION_DATABASE = 1;
    private static final boolean DEVELOPER = true;
    private Context context;

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        DBTableDoctor doctorTable = new DBTableDoctor(db);
        doctorTable.create();

        DBTableQuestion questionTable = new DBTableQuestion(db);
        questionTable.create();

        DBTableChoice questionChoicesTable = new DBTableChoice(db);
        questionChoicesTable.create();

        DBTableUser userTable = new DBTableUser(db);
        userTable.create();

        DBTableUserChoice userQuestionAnswerTable = new DBTableUserChoice(db);
        userQuestionAnswerTable.create();

        DBTableHistory testTable = new DBTableHistory(db);
        testTable.create();

        DBTableFaq faqsTable = new DBTableFaq(db);
        faqsTable.create();

        if ( DEVELOPER ) {
            Seed seed = new Seed(db, context);
            seed.load();
        }

        // Database Sync Data
        //SyncDB syncData = new SyncDB(db);
        //syncData.start();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
