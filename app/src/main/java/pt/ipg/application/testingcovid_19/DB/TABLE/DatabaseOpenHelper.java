package pt.ipg.application.testingcovid_19.DB.TABLE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import pt.ipg.application.testingcovid_19.DB.Convert;
import pt.ipg.application.testingcovid_19.Objects.Test;
import pt.ipg.application.testingcovid_19.Objects.User;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BASE_DADOS = "covid_19.db";
    private static final int VERSAO_BASE_DADOS = 1;
    private static final boolean DESENVOLVIMENTO = true;

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DBTableUser userTable = new DBTableUser(db);
        userTable.create();

        DBTableTest testTable = new DBTableTest(db);
        testTable.create();

        if (DESENVOLVIMENTO) {
            seedData(db);
        }
    }

    private void seedData(SQLiteDatabase db) {

        // USER's TABLE
        ArrayList<String> userName = new ArrayList<String>();
        DBTableUser userTable = new DBTableUser(db);
        userName.add("Agostinho Pina Ramos");
        userName.add("João Luis Lopes");
        userName.add("Maria Patricio");
        User user;

        for(int i=0; i<userName.size(); i++){
            user = new User();
            user.setName(userName.get(i));
            userTable.insert(Convert.userToContentValues(user));
        }

        // TEST's TABLE
        DBTableTest testTable = new DBTableTest(db);
        ArrayList<String> testLevel = new ArrayList<String>();
        testLevel.add("Mild");
        testLevel.add("Severe");
        testLevel.add("Very Severe");
        Test test;

        for(int i=0; i<testLevel.size(); i++){
            test = new Test();
            test.setLevel(testLevel.get(i));
            userTable.insert(Convert.testToContentValues(test));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
