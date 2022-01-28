package com.dhandev.eepa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhandev.eepa.ui.home.HomeFragment;

public class Login extends AppCompatActivity {

    EditText nama;
    Button loginBTN;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nama    = findViewById(R.id.editTextNama);
        loginBTN    = findViewById(R.id.buttonLogin);
        sharedPreferences   = getSharedPreferences("userName", MODE_PRIVATE);

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

        loginBTN.setOnClickListener(new View.OnClickListener() {
            View fokus = null;
            boolean cancel = false;
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().equals("")) {
                    nama.setError("This field is required");
                    fokus = nama;
                    cancel = true;
                }
                else {
                    DoLogin(nama.getText().toString());
                }
            }
        });
    }

    public void DoLogin(String nama) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama", nama);
        editor.commit();
        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}