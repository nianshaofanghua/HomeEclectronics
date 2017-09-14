package com.yidan.homeelectronics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yidan.homeelectronics.activity.BaseToolBarActivity;

public class MainActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
