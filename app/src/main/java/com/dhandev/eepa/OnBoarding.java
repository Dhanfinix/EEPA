package com.dhandev.eepa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhandev.eepa.ui.SliderAdapter;

public class OnBoarding extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mdots;
    private int curretPage;
    Button btnNext, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        slideViewPager  = findViewById(R.id.slideViewPager);
        dotsLayout      = findViewById(R.id.layoutDots);
        btnNext         = findViewById(R.id.btn_next);
        btnBack         = findViewById(R.id.btn_back);

        sliderAdapter   = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

        btnBack.setVisibility(View.INVISIBLE);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(curretPage +1);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(curretPage -1);
            }
        });

        //read it here https://stackoverflow.com/questions/16419627/making-an-activity-appear-only-once-when-the-app-is-started/16419799
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }
    }

    public void addDotsIndicator(int position){
        mdots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i<mdots.length; i++){
            mdots[i]    = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;")); //unicode html bentuk o
            mdots[i].setTextSize(45);
            mdots[i].setTextColor(getResources().getColor(R.color.white));
            mdots[i].setElevation(10);

            dotsLayout.addView(mdots[i]);
        }
        if (mdots.length>0){
            mdots[position].setTextColor(getResources().getColor(R.color.biru));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            curretPage = position;
            if (position ==0){
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);
                btnBack.setVisibility(View.INVISIBLE);
                btnNext.setText(getString(R.string.btn_next));
            } else if (position==mdots.length -1 ){
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);
                btnNext.setText("Login");

                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(OnBoarding.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            } else {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);
                btnNext.setText(getString(R.string.btn_next));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}