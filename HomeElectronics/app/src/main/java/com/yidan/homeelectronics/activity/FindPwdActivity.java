package com.yidan.homeelectronics.activity;

import android.os.Bundle;

import com.yidan.homeelectronics.R;

public class FindPwdActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setTopBar(R.layout.activity_find,"找回密码",false);
    }

}
