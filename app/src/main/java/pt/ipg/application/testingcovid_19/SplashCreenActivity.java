package pt.ipg.application.testingcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashCreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 10000;//10 segundos
    //Variavel que pretendo animar na intro
    TextView descApp;
   // ImageView icon;

    //Animation Variable
    Animation iconAnimation, bottonAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash_creen);

        bottonAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        iconAnimation = AnimationUtils.loadAnimation(this,R.anim.icon_animation);


        //icon = findViewById(R.id.imageIcon);
        descApp = findViewById(R.id.textDescri);

       // icon.setAnimation(iconAnimation);
        descApp.setAnimation(bottonAnimation);

        //Splace Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashCreenActivity.this, ApplicationStartActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}