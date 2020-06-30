package pt.ipg.application.testingcovid_19;

import android.util.Log;

import org.json.JSONObject;
import org.junit.Test;

import pt.ipg.application.testingcovid_19.other.Validations;

import static org.junit.Assert.*;
import static pt.ipg.application.testingcovid_19.other.Validations.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Validation_UnitTest {

    // VALIDATIONS..
    @Test
    public void password_matcher (){
        String[] password = {"password", "pass123", "passWord123!"};

        for(int i=0; i<password.length; i++) {
            boolean boo = matchPASSWORD().matcher(password[i]).matches();
            assertTrue(boo);
        }
    }

    @Test
    public void fullName_matcher(){
        // Support any type of name..
        String[] name = {"الاسم العربي", "Nome Apelido", "アラビ ア語名"};

        for(int i=0; i<name.length; i++) {
            boolean boo = matchFULLNAME(name[i]);
            assertTrue(boo);
        }
    }

    @Test
    public void mail_matcher(){
        String[] mail = {"a@b.c", "agostinhoramos@example.com"};

        for(int i=0; i<mail.length; i++){
            boolean boo = matchEMAIL().matcher(mail[i]).matches();
            assertTrue(boo);
        }
    }

    @Test
    public void username_matcher(){
        String[] username = {"username", "user123", "user_name", "username-21"};

        for(int i=0; i<username.length; i++) {
            boolean boo = matchUSERNAME().matcher(username[i]).matches();
            assertTrue(boo);
        }
    }

    @Test
    public void tin_matcher(){
        String[] tin = {"287173962"};

        for(int i=0; i<tin.length; i++) {
            boolean boo = matchTIN().matcher(tin[i]).matches();
            assertTrue(boo);
        }
    }

    @Test
    public void isNumber(){
        String[] isNum = {"03 95", "3 99", "123", "-321", "0.123"};

        for(int i=0; i<isNum.length; i++){
            // isNumeric -> Already exist by default..
            // For this reason I called the 'Validations' class
            // Support space between numbers
            assertTrue(Validations.isNumeric(isNum[i]));
        }
    }
}