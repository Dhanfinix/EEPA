package com.dhandev.eepa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dhandev.eepa.ui.home.HomeFragment;

public class Materi extends AppCompatActivity {

    private ImageView arrowBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        arrowBack = findViewById(R.id.arrowBack);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.createNavigateOnClickListener(R.id.navigation_home); //smooth transition
                finish();
            }
        });
    }
}