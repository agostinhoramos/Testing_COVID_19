package pt.ipg.application.testingcovid_19.other;

import java.util.regex.Pattern;

public class Validations {
    public static final Pattern matchPASSWORD(){
        return Pattern.compile(
                "^" + // start-of-string
                //"(?=.*[0-9])" + // a digit must occur at least once
                "(?=.*[a-z])" + // a lower case letter must occur at least once
                //"(?=.*[A-Z])" + // an upper case letter must occur at least once
                //"(?=.*[@#!$%^&+=])" + // a special character must occur at least once you can replace with your special characters.
                "(?=\\S+$)" + // no whitespace allowed in the entire string
                ".{6,}" + // at least 6 characters
                "$" // end-of-string
        );
    }

    public static final boolean matchFULLNAME(String name){
        boolean boo = true;
        if( name.length() < 3 ){
            boo = false;
        }
        if( !name.contains(" ") ){
            boo = false;
        }
        if( Validations.isNumeric(name) ){
            // Number can't be a full name.
            boo = false;
        }
        return boo;
    }

    public static final boolean isNumeric(String strNum) {
        strNum = strNum.trim(); // Remove unnecessary spaces in string
        strNum = strNum.replaceAll("\\s+",""); // Replace all space..

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
                "^" + // From begin
                "[" +
                "a-z" + // accept only lower case
                "A-Z" + // accept only upper case
                "0-9" + // accept only number
                "._-" + // accept only symbols like '.' or '_' or '-'
                "]" +
                "{6,35}" + // at least 6 characters ( Max: 35 )
                "$" // To end
        );
    }

    public static  final Pattern matchTIN(){
        return Pattern.compile(
                "([a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9])"
        );
    }
}
