package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.database.Cursor;

import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFAQs;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestionChoices;
import pt.ipg.application.testingcovid_19.database.table.DBTableTest;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserQuestionAnswer;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.FAQs;
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.object.QuestionChoices;
import pt.ipg.application.testingcovid_19.object.Test;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.object.UserQuestionAnswer;

public class Convert {

    // --> ToContentValues
    public static ContentValues doctorToContentValues(Doctor doctor){
        ContentValues values = new ContentValues();
        values.put(DBTableDoctor.COLUMN_NAME, doctor.getName());
        values.put(DBTableDoctor.COLUMN_TIN, doctor.getTIN());
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

    public static ContentValues testToContentValues(Test test){
        ContentValues values = new ContentValues();
        values.put(DBTableTest.COLUMN_LEVEL, test.getLevel());
        values.put(DBTableTest.COLUMN_DATE, test.getDate());
        return values;
    }

    public static ContentValues userQuestionAnswerToContentValues(UserQuestionAnswer userQuestionAnswer){
        ContentValues values = new ContentValues();
        values.put(DBTableUserQuestionAnswer.COLUMN_FK_USER, userQuestionAnswer.getUser_id());
        values.put(DBTableUserQuestionAnswer.COLUMN_FK_CHOICE, userQuestionAnswer.getChoice_id());
        return values;
    }

    public static ContentValues questionChoicesToContentValues(QuestionChoices questionChoices){
        ContentValues values = new ContentValues();
        values.put(DBTableQuestionChoices.COLUMN_FK_QUESTION, questionChoices.getQuestion_id());
        values.put(DBTableQuestionChoices.COLUMN_CHOICE, questionChoices.getChoice());
        values.put(DBTableQuestionChoices.COLUMN_WEIGHT, questionChoices.getWeight());
        return values;
    }

    public static ContentValues questionToContentValues(Question question){
        ContentValues values = new ContentValues();
        values.put(DBTableQuestion.COLUMN_FK_DOCTOR, question.getDoctor_id());
        values.put(DBTableQuestion.COLUMN_QUESTION, question.getQuestion());
        return values;
    }

    public static ContentValues faqsToContentValues(FAQs faqs){
        ContentValues values = new ContentValues();
        values.put(DBTableFAQs.COLUMN_FK_USER, faqs.getUser_id());
        values.put(DBTableFAQs.COLUMN_FK_DOCTOR, faqs.getDoctor_id());
        values.put(DBTableFAQs.COLUMN_QUESTION, faqs.getQuestion());
        values.put(DBTableFAQs.COLUMN_ANSWER, faqs.getAnswer());
        values.put(DBTableFAQs.COLUMN_DATE, faqs.getDate());
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


    public static Test ContentValuesToTest(ContentValues values){
        Test test = new Test();
        test.setId(values.getAsLong(DBTableUser._ID));
        test.setLevel(values.getAsString(DBTableTest.COLUMN_LEVEL));
        return test;
    }

    // WHEN WE USE CURSOR? TODO
    // CAN WE ALSO USE THIS WITH FOREIGN KEY?
    public static Test cursorToTest(Cursor cursor){
        Test test = new Test();
        test.setId(cursor.getLong(cursor.getColumnIndex(DBTableTest._ID)));
        test.setDate(cursor.getString(cursor.getColumnIndex(DBTableTest.COLUMN_DATE)));
        test.setLevel(cursor.getString(cursor.getColumnIndex(DBTableTest.COLUMN_LEVEL)));
        return test;
    }
}