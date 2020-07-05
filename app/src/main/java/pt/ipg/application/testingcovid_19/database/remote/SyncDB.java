package pt.ipg.application.testingcovid_19.database.remote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.table.DBTableDoctor;
import pt.ipg.application.testingcovid_19.database.table.DBTableFaq;
import pt.ipg.application.testingcovid_19.database.table.DBTableHistory;
import pt.ipg.application.testingcovid_19.object.Doctor;
import pt.ipg.application.testingcovid_19.object.Faq;
import pt.ipg.application.testingcovid_19.object.History;

public class SyncDB {

    private SQLiteDatabase db;
    private RemoteDB remoteDB;
    private boolean isNew;

    private DBTableDoctor tableDoctor;
    private DBTableFaq tableFaq;
    private DBTableHistory tableHistory;

    private Doctor doctor;
    private Faq faq;
    private History history;

    private long id_doctor, rId_doctor;
    private long id_faq, rId_faq;
    private long id_history, rId_history;

    public SyncDB(SQLiteDatabase db){
        this.db = db;
        isNew = false;

        tableDoctor = new DBTableDoctor(db);
        tableFaq = new DBTableFaq(db);
        tableHistory = new DBTableHistory(db);

        doctor = new Doctor();
        faq = new Faq();
        history = new History();
    }

    public void init(){
        this.isNew = true;
    }

    public void Start(Context context){
        // Write a message to the database

        remoteDB = new RemoteDB(context);

        if( isNew ) {
            String sql = tableDoctor.CREATE_QUERY();
            sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
            remoteDB.query(sql);

            sql = tableFaq.CREATE_QUERY();
            sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
            remoteDB.query(sql);

            sql = tableHistory.CREATE_QUERY();
            sql = sql.replace("AUTOINCREMENT", "AUTO_INCREMENT");
            remoteDB.query(sql);
        }

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
                    }

                    tableDoctor.insert(Convert.doctorToContentValues(doctor));
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
                    }

                    tableFaq.insert(Convert.faqToContentValues(faq));
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
                    ArrayList<String> c_name = remoteDB.getColumn(DBTableHistory.COLUMN_NAME);
                    ArrayList<String> c_country = remoteDB.getColumn(DBTableHistory.COLUMN_COUNTRY);
                    ArrayList<String> c_district = remoteDB.getColumn(DBTableHistory.COLUMN_DISTRICT);
                    ArrayList<String> c_email = remoteDB.getColumn(DBTableHistory.COLUMN_EMAIL);
                    ArrayList<String> c_phone = remoteDB.getColumn(DBTableHistory.COLUMN_PHONE);

                    for(int i=0; i<c_id.size(); i++) {
                        history.setUser_fk(Integer.parseInt(c_fk_user.get(i)));
                        history.setId(Integer.parseInt(c_id.get(i)));
                        history.setDate(c_date.get(i));
                        history.setLevel(c_level.get(i));
                        history.setName(c_name.get(i));
                        history.setCountry(c_country.get(i));
                        history.setDistrict(c_district.get(i));
                        history.setEmail(c_email.get(i));
                        history.setPhone(c_phone.get(i));
                    }

                    tableHistory.insert(Convert.historyToContentValues(history));
                }
                return null;
            }
        });
    }
}