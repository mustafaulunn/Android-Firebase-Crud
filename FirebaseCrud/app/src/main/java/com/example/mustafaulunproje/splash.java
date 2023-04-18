package com.example.mustafaulunproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(splash.this,girisactivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }; thread.start();



    }


}