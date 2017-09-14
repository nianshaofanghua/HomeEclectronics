package com.yidan.homeelectronics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yidan.homeelectronics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseToolBarActivity {

    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.pwd)
    EditText mPwd;
    @BindView(R.id.getcode)
    TextView mGetcode;
    @BindView(R.id.next)
    Button mNext;
    Intent mIntent;
    MyCount mMyCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopBar(R.layout.activity_forget_pwd, "找回密码", false);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mMyCount = new MyCount(60000,1000);
    }

    @OnClick({R.id.getcode, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getcode:
            mMyCount.start();
                mGetcode.setBackground(getDrawable(R.drawable.no_solid_white));
                break;
            case R.id.next:
                mIntent = new Intent(this, FindPwdActivity.class);
                startActivity(mIntent);
                break;
        }
    }


    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onTick(long millisUntilFinished) {
            mGetcode.setClickable(false);
            mGetcode.setText("获取验证码(" + millisUntilFinished / 1000 + ")");

        }
    }
}
