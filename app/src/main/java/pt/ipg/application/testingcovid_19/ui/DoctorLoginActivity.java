package pt.ipg.application.testingcovid_19.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

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

import pt.ipg.application.testingcovid_19.R;
import pt.ipg.application.testingcovid_19.object.User;
import pt.ipg.application.testingcovid_19.ui.login.DoctorAuthPagerAdapter;

public class DoctorLoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth mAuth;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ViewPager viewPager;
    TabLayout tabLayout;
    DoctorAuthPagerAdapter doctorLoginPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Hi, Doctor!");
        mAuth = FirebaseAuth.getInstance();

        /*if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(DoctorLoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        doctorLoginPagerAdapter = new DoctorAuthPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(doctorLoginPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(DoctorLoginActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}