package pt.ipg.application.testingcovid_19.database.remote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pt.ipg.application.testingcovid_19.data.Seed;
import pt.ipg.application.testingcovid_19.database.table.DBTableAvatar;
import pt.ipg.application.testingcovid_19.database.table.DBTableChoice;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.database.table.DBTableQuestion;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.object.Avatar;
import pt.ipg.application.testingcovid_19.object.Choice;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.Faq;
import pt.ipg.application.testingcovid_19.object.History;
import pt.ipg.application.testingcovid_19.object.Question;
import pt.ipg.application.testingcovid_19.object.User;

import static pt.ipg.application.testingcovid_19.database.Convert.ParseInsertSQL;

public class SyncDB {

    private static Context context;
    private static SQLiteDatabase db;
    private static RemoteDB rDB;

    private DBTableDoctor tableDoctor;
    private DBTableFaq tableFaq;
    private DBTableUser tableUser;
    private DBTableAvatar tableAvatar;
    private DBTableHistory tableHistory;
    private DBTableQuestion tableQuestion;
    private DBTableChoice tableChoice;

    private Doctor doctor;
    private Faq faq;
    private User user;
    private Avatar avatar;
    private History history;
    private Question question;
    private Choice choice;

    public SyncDB(SQLiteDatabase db, Context context){
        this.db = db;
        this.context = context;

        tableDoctor = new DBTableDoctor(db);
        tableFaq = new DBTableFaq(db);
        tableUser = new DBTableUser(db);
        tableAvatar = new DBTableAvatar(db);
        tableHistory = new DBTableHistory(db);
        tableQuestion = new DBTableQuestion(db);
        tableChoice = new DBTableChoice(db);

        doctor = new Doctor();
        faq = new Faq();
        user = new User();
        avatar = new Avatar();
        history = new History();
        question = new Question();
        choice = new Choice();
    }

    public boolean createAllTables(){
        rDB = new RemoteDB(context);

        String sql = DBTableDoctor.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableAvatar.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableChoice.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableFaq.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableHistory.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableQuestion.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        sql = DBTableUser.CREATE_QUERY();
        sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
        rDB.query(sql);

        return true;
    }

    public void init(){
        // Write a message to the database
        rDB = new RemoteDB(context);

        /*
        remoteDB.query("SELECT * FROM `"+DBTableDoctor.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    System.out.println(remoteDB.getValue());
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableDoctor._ID);
                    ArrayList<String> c_name = remoteDB.getColumn(DBTableDoctor.COLUMN_NAME);
                    ArrayList<String> c_tin = remoteDB.getColumn(DBTableDoctor.COLUMN_TIN);
                    ArrayList<String> c_avatar = remoteDB.getColumn(DBTableDoctor.COLUMN_AVATAR);
                    ArrayList<String> c_email = remoteDB.getColumn(DBTableDoctor.COLUMN_EMAIL);
                    ArrayList<String> c_phone = remoteDB.getColumn(DBTableDoctor.COLUMN_PHONE);
                    ArrayList<String> c_password = remoteDB.getColumn(DBTableDoctor.COLUMN_PASSWORD);
                    ArrayList<String> c_confirmed = remoteDB.getColumn(DBTableDoctor.COLUMN_CONFIRMED);

                    for(int i=0; i<c_id.size(); i++){
                        doctor.setName(c_name.get(i));
                        doctor.setTin(c_tin.get(i));
                        doctor.setAvatar(c_avatar.get(i));
                        doctor.setEmail(c_email.get(i));
                        doctor.setPhone(c_phone.get(i));
                        doctor.setPassword(c_password.get(i));
                        doctor.setConfirmed(c_confirmed.get(i));
                        tableDoctor.insert(Convert.doctorToContentValues(doctor));
                    }
                }

                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+DBTableFaq.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_fk_user = remoteDB.getColumn(DBTableFaq.COLUMN_FK_USER);
                    ArrayList<String> c_fk_doctor = remoteDB.getColumn(DBTableFaq.COLUMN_FK_DOCTOR);
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableFaq._ID);
                    ArrayList<String> c_question = remoteDB.getColumn(DBTableFaq.COLUMN_QUESTION);
                    ArrayList<String> c_answer = remoteDB.getColumn(DBTableFaq.COLUMN_ANSWER);
                    ArrayList<String> c_date = remoteDB.getColumn(DBTableFaq.COLUMN_DATE);

                    for(int i=0; i<c_id.size(); i++) {
                        faq.setUser_id(Integer.parseInt(c_fk_user.get(i)));
                        faq.setDoctor_id(Integer.parseInt(c_fk_doctor.get(i)));
                        faq.setId(Integer.parseInt(c_id.get(i)));
                        faq.setQuestion(c_question.get(i));
                        faq.setAnswer(c_answer.get(i));
                        faq.setDate(c_date.get(i));
                        tableFaq.insert(Convert.faqToContentValues(faq));
                    }
                }

                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+DBTableUser.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableUser._ID);
                    ArrayList<String> c_name = remoteDB.getColumn(DBTableUser.COLUMN_NAME);
                    ArrayList<String> c_gender = remoteDB.getColumn(DBTableUser.COLUMN_GENDER);
                    ArrayList<String> c_tin = remoteDB.getColumn(DBTableUser.COLUMN_TIN);
                    ArrayList<String> c_email = remoteDB.getColumn(DBTableUser.COLUMN_EMAIL);
                    ArrayList<String> c_phone = remoteDB.getColumn(DBTableUser.COLUMN_PHONE);
                    ArrayList<String> c_birthday = remoteDB.getColumn(DBTableUser.COLUMN_BIRTHDAY);
                    ArrayList<String> c_district = remoteDB.getColumn(DBTableUser.COLUMN_DISTRICT);
                    ArrayList<String> c_country = remoteDB.getColumn(DBTableUser.COLUMN_COUNTRY);

                    for(int i=0; i<c_id.size(); i++) {
                        user.setId(Integer.parseInt(c_id.get(i)));
                        user.setName(c_name.get(i));
                        user.setGender(c_gender.get(i));
                        user.setTIN(c_tin.get(i));
                        user.setEmail(c_email.get(i));
                        user.setPhone(c_phone.get(i));
                        user.setBirthday(c_birthday.get(i));
                        user.setDistrict(c_district.get(i));
                        user.setCountry(c_country.get(i));
                        tableFaq.insert(Convert.userToContentValues(user));
                    }
                }

                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+DBTableAvatar.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_fk_user = remoteDB.getColumn(DBTableAvatar.COLUMN_FK_USER);
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableAvatar._ID);
                    ArrayList<String> c_url = remoteDB.getColumn(DBTableAvatar.COLUMN_URL);
                    ArrayList<String> c_createdat = remoteDB.getColumn(DBTableAvatar.COLUMN_CREATEDAT);
                    ArrayList<String> c_updatedat = remoteDB.getColumn(DBTableAvatar.COLUMN_UPDATEDAT);

                    for(int i=0; i<c_id.size(); i++) {
                        avatar.setUser_fk(Integer.parseInt(c_fk_user.get(i)));
                        avatar.setId(Integer.parseInt(c_id.get(i)));
                        avatar.setUrl(c_url.get(i));
                        avatar.setCreated_at(c_createdat.get(i));
                        avatar.setUpdated_at(c_updatedat.get(i));
                        tableFaq.insert(Convert.faqToContentValues(faq));
                    }
                }

                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+DBTableHistory.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_fk_user = remoteDB.getColumn(DBTableHistory.COLUMN_FK_USER);
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableHistory._ID);
                    ArrayList<String> c_date = remoteDB.getColumn(DBTableHistory.COLUMN_DATE);
                    ArrayList<String> c_level = remoteDB.getColumn(DBTableHistory.COLUMN_LEVEL);

                    for(int i=0; i<c_id.size(); i++) {
                        history.setUser_fk(Integer.parseInt(c_fk_user.get(i)));
                        history.setId(Integer.parseInt(c_id.get(i)));
                        history.setDate(c_date.get(i));
                        history.setLevel(c_level.get(i));
                        tableHistory.insert(Convert.historyToContentValues(history));
                    }
                }
                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+DBTableQuestion.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_fk_doctor = remoteDB.getColumn(DBTableQuestion.COLUMN_FK_DOCTOR);
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableQuestion._ID);
                    ArrayList<String> c_question = remoteDB.getColumn(DBTableQuestion.COLUMN_QUESTION);

                    for(int i=0; i<c_id.size(); i++) {
                        question.setDoctor_id(Integer.parseInt(c_fk_doctor.get(i)));
                        question.setQuestion_id(Integer.parseInt(c_id.get(i)));
                        question.setQuestion(c_question.get(i));
                        tableFaq.insert(Convert.questionToContentValues(question));
                    }
                }

                return null;
            }
        });

        remoteDB.query("SELECT * FROM `"+ DBTableChoice.TABLE_NAME+"`", new Callable() {
            @Override
            public String call() throws Exception {
                if(remoteDB.isReady()){
                    ArrayList<String> c_fk_question = remoteDB.getColumn(DBTableChoice.COLUMN_FK_QUESTION);
                    ArrayList<String> c_id = remoteDB.getColumn(DBTableChoice._ID);
                    ArrayList<String> c_choice = remoteDB.getColumn(DBTableChoice.COLUMN_CHOICE);
                    ArrayList<String> c_weight = remoteDB.getColumn(DBTableChoice.COLUMN_WEIGHT);

                    for(int i=0; i<c_id.size(); i++) {
                        choice.setQuestion_id(Integer.parseInt(c_fk_question.get(i)));
                        choice.setId(Integer.parseInt(c_id.get(i)));
                        choice.setChoice(c_choice.get(i));
                        choice.setWeight(Integer.parseInt(c_weight.get(i)));
                        tableFaq.insert(Convert.choicesToContentValues(choice));
                    }

                }
                return null;
            }
        });
        */
    }
}