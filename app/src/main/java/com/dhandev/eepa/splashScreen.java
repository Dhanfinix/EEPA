package com.dhandev.eepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

import com.dhandev.eepa.ui.home.HomeFragment;

public class splashScreen extends AppCompatActivity {

    private  static int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim;
    ImageView logoBg, logo;
    TextView nama;
    TextView copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        logo    = findViewById(R.id.logo);
        logoBg  = findViewById(R.id.logoBg);
        nama    = findViewById(R.id.nama);
        copyright   = findViewById(R.id.copyright);

        logo.setAnimation(topAnim);
        logoBg.setAnimation(topAnim);
        nama.setAnimation(bottomAnim);
        copyright.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, OnBoarding.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}