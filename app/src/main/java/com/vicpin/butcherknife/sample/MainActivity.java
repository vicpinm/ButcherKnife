package com.vicpin.butcherknife.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Book b = new Book(new Date().getTime(),4432434,"http://www.worldbank.org/content/dam/photos/780x439/2016/sep-1/ssf2016AEM.jpg", false, false, false);


         BookBinding.with(this).bind(b);

        final RadioButton c = (RadioButton) findViewById(R.id.db);

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("fdsfds","changed " + b);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("fdsfds","toggle");

                c.toggle();
            }
        },4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("fdsfds","check");

                c.setChecked(false);
            }
        },8000);


    }
}
