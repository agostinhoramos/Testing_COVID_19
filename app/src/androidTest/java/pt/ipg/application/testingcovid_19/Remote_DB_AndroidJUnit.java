package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.remote.SyncDB;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Remote_DB_AndroidJUnit {

    @Test
    public void canCreateAllDB(){
        // Context of the app under test.
        Context appContext = getTargetContext();
        SyncDB syncDB = new SyncDB(null, appContext);
        boolean boo = syncDB.createAllTables();
        assertTrue(boo);
    }

    private SQLiteDatabase getLocalDatabase(){
        // Context of the app under test.
        Context appContext = getTargetContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
}