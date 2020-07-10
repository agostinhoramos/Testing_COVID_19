package pt.ipg.application.testingcovid_19;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import pt.ipg.application.testingcovid_19.object.Question;

public class DoctorDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    public ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapterDashboard pagerAdapterDashboard;
    public RecyclerView recyclerView;
    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        setTitle("");

        drawerLayout = findViewById(R.id.drawer_layout);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        pagerAdapterDashboard = new PagerAdapterDashboard(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterDashboard);
        tabLayout.setupWithViewPager(viewPager);
        recyclerView = findViewById(R.id.recycleViewDoctorQuestion);

        navigationView = findViewById(R.id.nav_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(DoctorDashboardActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
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
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.start: {
                //do somthing
                System.out.println("Agostinho Ramos");
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void refreshActivity(){
        finish();
        startActivity(getIntent());
    }
}