package pt.ipg.application.testingcovid_19.Others;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validations {
    public static final Pattern matchPASSWORD(){
        return Pattern.compile(
                    "(?=.*[0-9])" + // at least 1 digit
                    "(?=.*[a-z])" + // at least 1 lower case letter
                    "(?=.*[A-Z])" + // at least 1 upper case letter
                            // "(?=.*[a-zA-Z])" + // any letter
                    "(?=.*[@#$%^&+=])"+ // at least 1 special character
                    "(?=\\S+$)" + // no white spaces
                    ".{6,45}"+ // at least 6 characters in total, max 45 characters
                    "$" // end of the string
        );
    }

    public static final Pattern matchFULLNAME(){
        return Pattern.compile(
                "^[a-zA-Z\\\\s]+"
        );
    }

    public static final Pattern matchEMAIL(){
        return Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
    }

    public static final Pattern matchUSERNAME(){
        return Pattern.compile(
                "^[a-z0-9_-]{3,15}$"
        );
    }

    public static  final Pattern matchTIN(){
        return Pattern.compile(
                "([a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9])"
        );
    }
}
