package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.database.Cursor;

import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.object.Faq;
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.object.UserChoice;

public class Convert {

    // --> ToContentValues
    public static ContentValues doctorToContentValues(Doctor doctor){
        ContentValues values = new ContentValues();
        values.put(DBTableDoctor.COLUMN_NAME, doctor.getName());
        values.put(DBTableDoctor.COLUMN_TIN, doctor.getTin());
        values.put(DBTableDoctor.COLUMN_AVATAR, doctor.getAvatar());
        values.put(DBTableDoctor.COLUMN_EMAIL, doctor.getEmail());
        values.put(DBTableDoctor.COLUMN_PHONE, doctor.getPhone());
        values.put(DBTableDoctor.COLUMN_PASSWORD, doctor.getPassword());
        values.put(DBTableDoctor.COLUMN_CONFIRMED, doctor.getConfirmed());
        return values;
    }

    public static ContentValues userToContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(DBTableUser.COLUMN_NAME, user.getName());
        values.put(DBTableUser.COLUMN_GENDER, user.getGender());
        values.put(DBTableUser.COLUMN_TIN, user.getTIN());
        values.put(DBTableUser.COLUMN_EMAIL, user.getEmail());
        values.put(DBTableUser.COLUMN_PHONE, user.getPhone());
        values.put(DBTableUser.COLUMN_BIRTHDAY, user.getBirthday());
        values.put(DBTableUser.COLUMN_DISTRICT, user.getDistrict());
        values.put(DBTableUser.COLUMN_COUNTRY, user.getCountry());
        return values;
    }

    public static ContentValues historyToContentValues(History history){
        ContentValues values = new ContentValues();
        values.put(DBTableHistory.COLUMN_FK_USER, history.getUser_fk());
        values.put(DBTableHistory.COLUMN_DATE, history.getDate());
        values.put(DBTableHistory.COLUMN_LEVEL, history.getLevel());
        values.put(DBTableHistory.COLUMN_NAME, history.getName());
        values.put(DBTableHistory.COLUMN_COUNTRY, history.getCountry());
        values.put(DBTableHistory.COLUMN_DISTRICT, history.getDistrict());
        values.put(DBTableHistory.COLUMN_EMAIL, history.getEmail());
        values.put(DBTableHistory.COLUMN_PHONE, history.getPhone());
        return values;
    }

    public static ContentValues userQuestionAnswerToContentValues(UserChoice userChoice){
        ContentValues values = new ContentValues();
        values.put(DBTableUserChoice.COLUMN_FK_USER, userChoice.getUser_id());
        values.put(DBTableUserChoice.COLUMN_FK_CHOICE, userChoice.getChoice_id());
        return values;
    }

    public static ContentValues questionChoicesToContentValues(Choice choice){
        ContentValues values = new ContentValues();
        values.put(DBTableChoice.COLUMN_FK_QUESTION, choice.getQuestion_id());
        values.put(DBTableChoice.COLUMN_CHOICE, choice.getChoice());
        values.put(DBTableChoice.COLUMN_WEIGHT, choice.getWeight());
        return values;
    }

    public static ContentValues questionToContentValues(Question question){
        ContentValues values = new ContentValues();
        values.put(DBTableQuestion.COLUMN_FK_DOCTOR, question.getDoctor_id());
        values.put(DBTableQuestion.COLUMN_QUESTION, question.getQuestion());
        return values;
    }

    public static ContentValues faqToContentValues(Faq faqs){
        ContentValues values = new ContentValues();
        values.put(DBTableFaq.COLUMN_FK_USER, faqs.getUser_id());
        values.put(DBTableFaq.COLUMN_FK_DOCTOR, faqs.getDoctor_id());
        values.put(DBTableFaq.COLUMN_QUESTION, faqs.getQuestion());
        values.put(DBTableFaq.COLUMN_ANSWER, faqs.getAnswer());
        values.put(DBTableFaq.COLUMN_DATE, faqs.getDate());
        return values;
    }

    // --> ???
    public static User ContentValuesToUser(ContentValues values){
        User user = new User();
        user.setId(values.getAsLong(DBTableUser._ID));
        user.setName(values.getAsString(DBTableUser.COLUMN_NAME));
        user.setGender(values.getAsString(DBTableUser.COLUMN_GENDER));
        user.setTIN(values.getAsString(DBTableUser.COLUMN_TIN));
        user.setEmail(values.getAsString(DBTableUser.COLUMN_EMAIL));
        user.setPhone(values.getAsString(DBTableUser.COLUMN_PHONE));
        user.setBirthday(values.getAsString(DBTableUser.COLUMN_BIRTHDAY));
        user.setDistrict(values.getAsString(DBTableUser.COLUMN_DISTRICT));
        user.setCountry(values.getAsString(DBTableUser.COLUMN_COUNTRY));

        return user;
    }

    public static User cursorToUser(Cursor cursor){
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(DBTableUser._ID)));
        user.setName(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME)));
        user.setGender(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER)));
        user.setTIN(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_TIN)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_EMAIL)));
        user.setPhone(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_PHONE)));
        user.setBirthday(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_BIRTHDAY)));
        user.setDistrict(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_DISTRICT)));
        user.setCountry(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_COUNTRY)));
        return user;
    }


    public static History ContentValuesToTest(ContentValues values){
        History history = new History();
        history.setId(values.getAsLong(DBTableUser._ID));
        history.setLevel(values.getAsString(DBTableHistory.COLUMN_LEVEL));
        return history;
    }

    // WHEN WE USE CURSOR? TODO
    // CAN WE ALSO USE THIS WITH FOREIGN KEY?
    public static History cursorToTest(Cursor cursor){
        History history = new History();
        history.setId(cursor.getLong(cursor.getColumnIndex(DBTableHistory._ID)));
        history.setDate(cursor.getString(cursor.getColumnIndex(DBTableHistory.COLUMN_DATE)));
        history.setLevel(cursor.getString(cursor.getColumnIndex(DBTableHistory.COLUMN_LEVEL)));
        return history;
    }
}