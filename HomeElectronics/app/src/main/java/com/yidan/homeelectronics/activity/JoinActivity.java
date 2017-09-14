package com.yidan.homeelectronics.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yidan.homeelectronics.R;

public class JoinActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        setTopBar(R.layout.activity_join,"申请成为合作商",false);
    }
}
