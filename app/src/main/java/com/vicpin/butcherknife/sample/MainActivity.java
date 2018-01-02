package com.vicpin.butcherknife.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Book b = new Book(new Date().getTime(),4432434,"http://www.worldbank.org/content/dam/photos/780x439/2016/sep-1/ssf2016AEM.jpg");


         BookBinding.with(this).bind(b);


    }
}
