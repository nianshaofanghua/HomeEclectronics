package com.yidan.homeelectronics.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yidan.homeelectronics.MainActivity;
import com.yidan.homeelectronics.R;
import com.yidan.homeelectronics.http.MoOkHttp;
import com.yidan.homeelectronics.http.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yidan.homeelectronics.R2.id.pwd;


public class LoginActivity extends BaseToolBarActivity {


    Boolean mIsOpen = false;
    Context mContext;
    MoOkHttp mMoOkHttp;
    Intent mIntent;
    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.pwd)
    EditText mPwd;
    @BindView(R.id.eye)
    ImageView mEye;
    @BindView(R.id.forget_pwd)
    TextView mForgetPwd;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.join)
    Button mJoin;
    @BindView(R.id.lay)
    LinearLayout mLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        mContext = this;
        mMoOkHttp = new MoOkHttp();
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.eye, R.id.forget_pwd, R.id.login, R.id.join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye:
                if (mIsOpen) {
                    mPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIsOpen = false;
                } else {
                    //   mPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIsOpen = true;
                }
                break;
            case R.id.forget_pwd:
                mIntent = new Intent(this, ForgetPwdActivity.class);
                startActivity(mIntent);
                break;
            case R.id.login:
                if (TextUtils.isEmpty(mPhone.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(mPwd.getText().toString().trim())) {
                    ToastUtils.showToast(mContext, "请输入密码");
                    return;
                }
                Map<String, String> param = new HashMap<>();
                //   String login = mMoOkHttp.doPost("", param);
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                break;
            case R.id.join:
                mIntent = new Intent(this, JoinActivity.class);
                startActivity(mIntent);
                break;
        }
    }


}
