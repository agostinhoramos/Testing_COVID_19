package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.object.Doctor;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DB_Doctor_AndroidJUnitTest {
    @Before
    @After
    public void deleteDatabase() {
        getTargetContext().deleteDatabase(DatabaseOpenHelper.NAME_DATABASE);
    }

    @Test
    public void OpenDatabase() {
        // Context of the app under test.
        Context appContext = getTargetContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private long insertDoctor(DBTableDoctor tbDoctor, String Name, String TIN, String Email,
                              String Phone, String Password, String Confirmed) {
        Doctor doctor = new Doctor();

        doctor.setName(Name);
        doctor.setTIN(TIN);
        doctor.setEmail(Email);
        doctor.setPhone(Phone);
        doctor.setPassword(Password);
        doctor.setConfirmed(Confirmed);

        return insertDoctor(tbDoctor, doctor);
    }

    private long insertDoctor(DBTableDoctor tbDoctor, Doctor doctor) {
        return tbDoctor.insert(Convert.doctorToContentValues(doctor));
    }

    @Test
    public void canInsert() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        DBTableDoctor table = new DBTableDoctor(db);

        long myid = insertDoctor(table,
                "Fábio Santos",
                "23424365",
                "fabiosantos@gmail.com",
                "936458264",
                "banana3",
                "1");

        db.close();
    }

    @Test
    public void canRead(){
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        DBTableDoctor table = new DBTableDoctor(db);

        Cursor cursor = table.query(DBTableDoctor.ALL_COLUMN, null, null, null, null, null);
        int register = cursor.getCount();
        cursor.close();

        insertDoctor(table,
                "Mario Gomes",
                "237434365",
                "mariogomes@gmail.com",
                "9364849564",
                "patoey34",
                "1");

        cursor = table.query(DBTableDoctor.ALL_COLUMN, null, null, null, null, null);

        assertEquals(register + 1, cursor.getCount());
        cursor.close();

        db.close();
    }

    @Test
    public void canChange(){
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DBTableDoctor table = new DBTableDoctor(db);

        // HERE FIND BY _ID ...
        Doctor doctor = new Doctor();
        doctor.setName("Agostinho");
        doctor.setTIN("287273962");
        doctor.setEmail("a@gmail.com");
        doctor.setPhone("934927329");
        doctor.setPassword("password2020");
        doctor.setConfirmed("Guarda");

        long id = insertDoctor(table, doctor);

        // HERE STAY WHAT YOU GONNA CHANGE...
        doctor.setName("Augusto");
        // ...

        int recordsAffected = table.update(Convert.doctorToContentValues(doctor), DBTableDoctor._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, recordsAffected);

        db.close();
    }

    @Test
    public void canDelete(){
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        DBTableDoctor table = new DBTableDoctor(db);
        long id = insertDoctor(table,
                "Bill Joe",
                "237430005",
                "billjoe@gmail.com",
                "936434637464",
                "bed0mouse",
                "0");


        int recordsDeleted = table.delete(DBTableDoctor._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, recordsDeleted);

        db.close();
    }
}