package pt.ipg.application.testingcovid_19;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class DoctorAuthActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapterWelcome pagerAdapterWelcome;

    private EditText TextInputUsername;
    private EditText TextInputPassword;
    private CheckBox RememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_auth);

        drawerLayout = findViewById(R.id.drawer_layout);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        pagerAdapterWelcome = new PagerAdapterWelcome(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterWelcome);
        tabLayout.setupWithViewPager(viewPager);

        TextInputUsername = (EditText) findViewById(R.id.et_email);
        TextInputPassword = (EditText) findViewById(R.id.et_password);
        RememberMe = (CheckBox) findViewById(R.id.cb_remember_me);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(DoctorAuthActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    private void auto_insert(){
        try{
            Intent intent = new Intent();
            String new_email = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_EMAIL);
            String new_passw = intent.getStringExtra(DoctorSignUpActivity.EXTRA_TEXT_PASSWORD);
            System.out.println( new_email + " * " + new_passw );
            if( !new_email.isEmpty() && !new_passw.isEmpty() ){
                TextInputUsername.setText(new_email);
                TextInputPassword.setText(new_passw);
            }
        }catch(Exception e){}
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}