package pt.ipg.application.testingcovid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ipg.application.testingcovid_19.db.Convert;
import pt.ipg.application.testingcovid_19.db.table.DBTableTest;
import pt.ipg.application.testingcovid_19.db.table.DBTableUser;
import pt.ipg.application.testingcovid_19.db.table.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    /*
    @Before
    @After
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(DatabaseOpenHelper.NAME_DATABASE);
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getReadableDatabase();
        assertTrue(bdTest.isOpen());
        bdTest.close();
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private long insereUser(DBTableUser tabelaUsers, User user) {
        long id = tabelaUsers.insert(Convert.userToContentValues(user));
        assertNotEquals(-1, id);
        return id;
    }

    private long insereUser(DBTableUser tabelaUsers, String name) {
        User user = new User();
        user.setName(name);
        return insereUser(tabelaUsers, user);
    }

    private long inseretest(SQLiteDatabase bdTest, String level, String descUser) {
        DBTableUser tabelaUsers = new DBTableUser(bdTest);
        long idUser = insereUser(tabelaUsers, descUser);
        Test test = new Test();
        test.setLevel(level);
        DBTableTest tabelaTest = new DBTableTest(bdTest);
        long id = tabelaTest.insert(Convert.testToContentValues(test));
        assertNotEquals(-1, id);
        return  id;
        return -1;
    }

    @Test
    public void consegueInserirUsers() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();

        DBTableUser tabelaUsers = new DBTableUser(bdTest);

        insereUser(tabelaUsers, "Ação");

        bdTest.close();
    }

    @Test
    public void consegueLerUsers() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();

        DBTableUser tabelaUsers = new DBTableUser(bdTest);

        Cursor cursor = tabelaUsers.query(DBTableUser.ALL_COLUMN, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereUser(tabelaUsers, "Sci-fi");

        cursor = tabelaUsers.query(DBTableUser.ALL_COLUMN, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdTest.close();
    }

    @Test
    public void consegueAlterarUsers() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();

        DBTableUser tabelaUsers = new DBTableUser(bdTest);

        User user = new User();
        user.setName("Romanc");

        long id = insereUser(tabelaUsers, user);

        user.setName("Romance");
        int registosAfetados = tabelaUsers.update(Convert.userToContentValues(user), DBTableUser._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAfetados);

        bdTest.close();
    }

    @Test
    public void consegueEliminarUsers() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();

        DBTableUser tabelaUsers = new DBTableUser(bdTest);

        long id = insereUser(tabelaUsers, "TESTE");

        int registosEliminados = tabelaUsers.delete(DBTableUser._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdTest.close();
    }

    @Test
    public void consegueInserirTest() {
        Context appContext = getTargetContext();

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();

        inseretest(bdTest, "O Intruso", "Terror");

        bdTest.close();
    }

    @Test
    public void consegueLerTest() {
        Context appContext = getTargetContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();
        BdTableTest tabelaTest = new BdTableTest(bdTest);
        Cursor cursor = tabelaTest.query(BdTableTest.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        inseretest(bdTest, "O silêncio dos inocentes", "Thriller");
        cursor = tabelaTest.query(BdTableTest.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        bdTest.close();
    }

    @Test
    public void consegueAlterarTest() {
        Context appContext = getTargetContext();
        BdTestOpenHelper openHelper = new BdTestOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();
        long idtest = inseretest(bdTest, "O silêncio dos inocentes", "Thriller");
        BdTableTest tabelaTest = new BdTableTest(bdTest);
        Cursor cursor = tabelaTest.query(BdTableTest.TODOS_CAMPOS, BdTableTest.CAMPO_ID_COMPLETO + "=?", new String[]{ String.valueOf(idtest) }, null, null, null);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToNext());
        test test = Converte.cursorTotest(cursor);
        cursor.close();
        assertEquals("O silêncio dos inocentes", test.getTitulo());
        test.setTitulo("O mistério do quarto secreto");
        int registosAfetados = tabelaTest.update(Converte.testToContentValues(test), BdTableTest.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(test.getId())});
        assertEquals(1, registosAfetados);
        bdTest.close();
    }

    @Test
    public void consegueEliminarTest() {
        Context appContext = getTargetContext();
        BdTestOpenHelper openHelper = new BdTestOpenHelper(appContext);
        SQLiteDatabase bdTest = openHelper.getWritableDatabase();
        long id = inseretest(bdTest, "O silêncio dos inocentes", "Thriller");
        BdTableTest tabelaTest = new BdTableTest(bdTest);
        int registosEliminados = tabelaTest.delete(BdTableTest._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);
        bdTest.close();
    }

     */
}