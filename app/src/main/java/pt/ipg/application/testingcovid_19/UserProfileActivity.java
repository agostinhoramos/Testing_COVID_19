package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import pt.ipg.application.testingcovid_19.database.Convert;
import pt.ipg.application.testingcovid_19.database.DatabaseOpenHelper;
import pt.ipg.application.testingcovid_19.database.table.DBTableUser;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.other.Function;

public class UserProfileActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private Button btn_cancel;
    private Button btn_update;
    private Intent intent = null;

    public EditText full_name;
    public Spinner spinner;
    public TextView birthday;
    public EditText tin;
    public EditText email;
    public EditText phone;
    public EditText district;
    public EditText country;
    private Date birthday_date;

    private TextView tv_level;
    private ImageView iv_profile;

    private SQLiteDatabase db;
    private Cursor cursor;
    private Function fn;
    private int position;

    private DatePickerDialog  StartTime;
    private Calendar newCalendar;

    public String levelText = "Your Level: ";
    public static String[] TypeGender = {"Male", "Female", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Context appContext = getApplicationContext();
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(appContext);
        db = openHelper.getWritableDatabase();
        fn = new Function();

        full_name = findViewById(R.id.et_full_name);
        spinner = findViewById(R.id.spinner);
        birthday = findViewById(R.id.tv_birthday);
        tin = findViewById(R.id.et_TIN);
        email = findViewById(R.id.et_email);
        phone = findViewById(R.id.et_phone);
        district = findViewById(R.id.et_district);
        country = findViewById(R.id.et_country);

        tv_level = findViewById(R.id.tv_level);
        iv_profile = findViewById(R.id.iv_profile);

        position = -1;


        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        displayData();

        newCalendar = Calendar.getInstance();
        StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthday_date = newDate.getTime();
                birthday.setText(DateFormat.getDateInstance(DateFormat.LONG).format(birthday_date));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime.show();
            }
        });
    }

    private void displayData(){
        setSpinnerContent();
        DBTableUser tb_user = new DBTableUser(db);
        int id_user = 1;
        try{
            String selection = DBTableUser._ID+"=?";
            String[] args = {String.valueOf(id_user)};
            cursor = tb_user.query(DBTableUser.ALL_COLUMN, selection, args, null, null, null);
            cursor.moveToPosition(0);

            full_name.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_NAME)));
            spinner.setSelection(getIndexGender(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_GENDER))));
            birthday_date = fn.StringToDate( cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_BIRTHDAY)) );
            birthday.setText( DateFormat.getDateInstance(DateFormat.LONG).format(birthday_date) );
            tin.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_TIN)));
            email.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_EMAIL)));
            phone.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_PHONE)));
            district.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_DISTRICT)));
            country.setText(cursor.getString(cursor.getColumnIndex(DBTableUser.COLUMN_COUNTRY)));

            tv_level.setText(levelText+"Severe");

        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(){
        int id_user = 1;
        DBTableUser tb_user = new DBTableUser(db);
        User obj_user = new User();
        String full_name = this.full_name.getText().toString();
        String tin = this.tin.getText().toString();
        String email = this.email.getText().toString();
        String phone = this.phone.getText().toString();
        String district = this.district.getText().toString();
        String country = this.country.getText().toString();
        // make validation

        obj_user.setName(full_name);
        obj_user.setGender(TypeGender[position]);
        obj_user.setBirthday(fn.DateToString(birthday_date));
        obj_user.setTin(tin);
        obj_user.setEmail(email);
        obj_user.setPhone(phone);
        obj_user.setDistrict(district);
        obj_user.setCountry(country);

        obj_user.setCreated_at(fn.DateToString(new Date()));
        String clause = DBTableUser._ID + "=?";
        String[] args = {String.valueOf(id_user)};
        tb_user.update(Convert.userToContentValues(obj_user), clause, args);
        Toast.makeText(getApplicationContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
    }

    private void setSpinnerContent() {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, TypeGender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    private int getIndexGender(String gender){
        for(int i=0; i<TypeGender.length; i++){
            if( TypeGender[i] == gender){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(this.position>-1){
            Toast.makeText(getApplicationContext(), TypeGender[position] , Toast.LENGTH_LONG).show();
        }
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}