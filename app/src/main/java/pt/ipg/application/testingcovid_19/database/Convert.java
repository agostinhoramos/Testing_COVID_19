package pt.ipg.application.testingcovid_19.database;

import android.content.ContentValues;
import android.database.Cursor;

import pt.ipg.application.testingcovid_19.database.table.DBTableAvatar;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.database.table.DBTableUserChoice;
import pt.ipg.application.testingcovid_19.object.Avatar;
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
        values.put(DBTableDoctor.COLUMN_CREATED_AT, doctor.getCreated_at());
        return values;
    }

    public static ContentValues userToContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(DBTableUser.COLUMN_NAME, user.getName());
        values.put(DBTableUser.COLUMN_GENDER, user.getGender());
        values.put(DBTableUser.COLUMN_TIN, user.getTin());
        values.put(DBTableUser.COLUMN_EMAIL, user.getEmail());
        values.put(DBTableUser.COLUMN_PHONE, user.getPhone());
        values.put(DBTableUser.COLUMN_BIRTHDAY, user.getBirthday());
        values.put(DBTableUser.COLUMN_DISTRICT, user.getDistrict());
        values.put(DBTableUser.COLUMN_COUNTRY, user.getCountry());
        values.put(DBTableUser.COLUMN_CREATED_AT, user.getCreated_at());
        return values;
    }

    public static ContentValues avatarToContentValues(Avatar avatar){
        ContentValues values = new ContentValues();
        values.put(DBTableAvatar.COLUMN_URL, avatar.getUrl());
        values.put(DBTableAvatar.COLUMN_CREATED_AT, avatar.getCreated_at());
        values.put(DBTableAvatar.COLUMN_UPDATED_AT, avatar.getUpdated_at());
        values.put(DBTableAvatar.COLUMN_FK_USER, avatar.getFk_user());
        return values;
    }

    public static ContentValues historyToContentValues(History history){
        ContentValues values = new ContentValues();
        values.put(DBTableHistory.COLUMN_FK_USER, history.getFk_user());
        values.put(DBTableHistory.COLUMN_DATE, history.getDate());
        values.put(DBTableHistory.COLUMN_LEVEL, history.getLevel());
        return values;
    }

    public static ContentValues userChoiceToContentValues(UserChoice userChoice){
        ContentValues values = new ContentValues();
        values.put(DBTableUserChoice.COLUMN_FK_USER, userChoice.getUser_id());
        values.put(DBTableUserChoice.COLUMN_FK_CHOICE, userChoice.getChoice_id());
        return values;
    }

    public static ContentValues choicesToContentValues(Choice choice){
        ContentValues values = new ContentValues();
        values.put(DBTableChoice.COLUMN_FK_QUESTION, choice.getFk_question());
        values.put(DBTableChoice.COLUMN_CHOICE, choice.getChoice());
        values.put(DBTableChoice.COLUMN_WEIGHT, choice.getWeight());
        return values;
    }

    public static ContentValues questionToContentValues(Question question){
        ContentValues values = new ContentValues();
        values.put(DBTableQuestion.COLUMN_FK_DOCTOR, question.getFk_doctor());
        values.put(DBTableQuestion.COLUMN_QUESTION, question.getQuestion());
        return values;
    }

    public static ContentValues faqToContentValues(Faq faqs){
        ContentValues values = new ContentValues();
        values.put(DBTableFaq.COLUMN_FK_USER, faqs.getFk_user());
        values.put(DBTableFaq.COLUMN_FK_DOCTOR, faqs.getFk_doctor());
        values.put(DBTableFaq.COLUMN_QUESTION, faqs.getQuestion());
        values.put(DBTableFaq.COLUMN_ANSWER, faqs.getAnswer());
        values.put(DBTableFaq.COLUMN_CREATE_AT, faqs.getCreate_at());
        return values;
    }

    public static String ParseInsertSQL(String table, String[] aColumns, String[] aValues, boolean[] aTypes){
        String query = "";
        String columns="", values="";
        String sep = "";
        for(int i=0;i<aColumns.length;i++){
            String column = aColumns[i];
            String value = aValues[i];
            if(i+1 < aColumns.length){
                sep=", ";
            }else{
                sep="";
            }
            if(aTypes[i]){
                value = "'"+value+"'";
            }
            if(
                    !aTypes[i] && (column=="_id" || column.indexOf("fk_")>-1) && Integer.parseInt(aValues[i]) != -1 ||
                    aTypes[i] && aValues[i] != null
            ){
                columns += "`"+column+"`"+sep;
                values += value+sep;
            }
        }
        query="INSERT INTO `"+table+"` ("+columns+") VALUES ("+values+");";
        return query;
    }

    // --> ???
    public static User contentValuesToUser(ContentValues values){
        User user = new User();
        //user.setId(values.getAsLong(DBTableUser._ID));
        user.setName(values.getAsString(DBTableUser.COLUMN_NAME));
        user.setGender(values.getAsString(DBTableUser.COLUMN_GENDER));
        user.setTin(values.getAsString(DBTableUser.COLUMN_TIN));
        user.setEmail(values.getAsString(DBTableUser.COLUMN_EMAIL));
        user.setPhone(values.getAsString(DBTableUser.COLUMN_PHONE));
        user.setBirthday(values.getAsString(DBTableUser.COLUMN_BIRTHDAY));
        user.setDistrict(values.getAsString(DBTableUser.COLUMN_DISTRICT));
        user.setCountry(values.getAsString(DBTableUser.COLUMN_COUNTRY));
        user.setCreated_at(values.getAsString(DBTableUser.COLUMN_CREATED_AT));
        return user;
    }

    public static Doctor contentValuesToDoctor(ContentValues values){
        Doctor doctor = new Doctor();
        //doctor.setId(values.getAsLong(DBTableDoctor._ID));
        doctor.setName(values.getAsString(DBTableDoctor.COLUMN_NAME));
        doctor.setTin(values.getAsString(DBTableDoctor.COLUMN_TIN));
        doctor.setAvatar(values.getAsString(DBTableDoctor.COLUMN_AVATAR));
        doctor.setEmail(values.getAsString(DBTableDoctor.COLUMN_EMAIL));
        doctor.setPhone(values.getAsString(DBTableDoctor.COLUMN_PHONE));
        doctor.setPassword(values.getAsString(DBTableDoctor.COLUMN_PASSWORD));
        doctor.setConfirmed(values.getAsString(DBTableDoctor.COLUMN_CONFIRMED));
        doctor.setCreated_at(values.getAsString(DBTableDoctor.COLUMN_CREATED_AT));
        return doctor;
    }

    public static Avatar contentValuesToAvatar(ContentValues values){
        Avatar avatar = new Avatar();
        //avatar.setId(values.getAsLong(DBTableAvatar._ID));
        avatar.setUrl(values.getAsString(DBTableAvatar.COLUMN_URL));
        avatar.setCreated_at(values.getAsString(DBTableAvatar.COLUMN_CREATED_AT));
        avatar.setCreated_at(values.getAsString(DBTableAvatar.COLUMN_UPDATED_AT));
        avatar.setFk_user(values.getAsLong(DBTableAvatar.COLUMN_FK_USER));
        return avatar;
    }

    public static Choice contentValuesToChoice(ContentValues values){
        Choice choice = new Choice();
        //choice.setId(values.getAsLong(DBTableChoice._ID));
        choice.setChoice(values.getAsString(DBTableChoice.COLUMN_CHOICE));
        choice.setWeight(values.getAsInteger(DBTableChoice.COLUMN_WEIGHT));
        choice.setFk_question(values.getAsLong(DBTableChoice.COLUMN_FK_QUESTION));
        return choice;
    }

    public static Faq contentValuesToFaq(ContentValues values){
        Faq faq = new Faq();
        //faq.setId(values.getAsLong(DBTableFaq._ID));
        faq.setQuestion(values.getAsString(DBTableFaq.COLUMN_QUESTION));
        faq.setAnswer(values.getAsString(DBTableFaq.COLUMN_ANSWER));
        faq.setCreate_at(values.getAsString(DBTableFaq.COLUMN_CREATE_AT));
        faq.setFk_user(values.getAsLong(DBTableFaq.COLUMN_FK_USER));
        faq.setFk_doctor(values.getAsLong(DBTableFaq.COLUMN_FK_DOCTOR));
        return faq;
    }

    public static History contentValuesToHistory(ContentValues values){
        History history = new History();
        //history.setId(values.getAsLong(DBTableHistory._ID));
        history.setDate(values.getAsString(DBTableHistory.COLUMN_DATE));
        history.setLevel(values.getAsString(DBTableHistory.COLUMN_LEVEL));
        history.setFk_user(values.getAsLong(DBTableHistory.COLUMN_FK_USER));
        return history;

    }

    public static Question contentValuesToQuestion(ContentValues values){
        Question question = new Question();
        //question.setId(values.getAsLong(DBTableQuestion._ID));
        question.setQuestion(values.getAsString(DBTableQuestion.COLUMN_QUESTION));
        question.setFk_doctor(values.getAsLong(DBTableQuestion.COLUMN_FK_DOCTOR));
        return question;
    }

    /*
    public static User cursorToUser(Cursor cursor){
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(DBTableUser._ID)));
        user.setName(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME)));
        user.setGender(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER)));
        user.setTin(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_TIN)));
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
    }*/
}