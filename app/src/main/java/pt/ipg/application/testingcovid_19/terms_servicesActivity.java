package pt.ipg.application.testingcovid_19;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class terms_servicesActivity extends AppCompatActivity {
    ListView terms ;
    Toolbar toolbar;
    DrawerLayout drawerLayout ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_services);
        terms=findViewById(R.id.terms);
        toolbar=(Toolbar) findViewById(R.id.toolbar3);
        drawerLayout=findViewById(R.id.drawer_layout3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<String> strings = new ArrayList<>();
        strings.add("lorem ipsum doctor sit amet,consectetur adipiscing elit, Fusce convallis pellentesque metus id lancinia,Nunc dapibus pulvinar auctor");
        strings.add("lorem ipsum doctor sit amet,consectetur adipiscing elit, Fusce convallis pellentesque metus id lancinia,Nunc dapibus pulvinar auctor");
        strings.add("lorem ipsum doctor sit amet,consectetur adipiscing elit, Fusce convallis pellentesque metus id lancinia,Nunc dapibus pulvinar auctor");
        strings.add("lorem ipsum doctor sit amet,consectetur adipiscing elit, Fusce convallis pellentesque metus id lancinia,Nunc dapibus pulvinar auctor");
        strings.add("lorem ipsum doctor sit amet,consectetur adipiscing elit, Fusce convallis pellentesque metus id lancinia,Nunc dapibus pulvinar auctor");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strings);
        terms.setAdapter(arrayAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }
}