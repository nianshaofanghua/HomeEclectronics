package com.yidan.homeelectronics.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yidan.homeelectronics.R;
import com.yidan.homeelectronics.utils.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by ${syj} on 2017/9/11.
 */

public class BaseToolBarActivity extends AppCompatActivity{
    TextView tv_title, tv_title_right;
    LinearLayout ll_title_bar_left, ll_empty_content, ll_title_bar_right;
    RelativeLayout title_bar;
    public Context mContext;
    ViewGroup.LayoutParams lp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.icon_blue);//通知栏所需颜色


        }


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);


    }





    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void update() {


    }

    public void setTopBar(int layoutResID, String title, boolean isShowRightBtn) {
        View v = LayoutInflater.from(this).inflate(
                R.layout.activity_base, null);
        initMyView(v);
        if (TextUtils.isEmpty(title)) {
            title_bar.setVisibility(View.GONE);
        }
        if (isShowRightBtn) {
            ll_title_bar_right.setVisibility(View.VISIBLE);
        } else {
            ll_title_bar_right.setVisibility(View.INVISIBLE);
        }
        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p1.addRule(RelativeLayout.BELOW, v.getId());
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        ll_empty_content.addView(contentView, p1);
        setContentView(v);
        tv_title.setText(title);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    public void initData() {
    }

    public void initView() {
    }
    public void initMyView(View v) {
        title_bar = (RelativeLayout) v.findViewById(R.id.title_bar);
        tv_title = (TextView) v.findViewById(R.id.tv_title_bar_center);
        ll_title_bar_left = (LinearLayout) v.findViewById(R.id.ll_title_bar_left);
        ll_title_bar_right = (LinearLayout) v.findViewById(R.id.ll_title_bar_right);
        ll_empty_content = (LinearLayout) v.findViewById(R.id.ll_empty_content);
        tv_title_right = (TextView) v.findViewById(R.id.tv_title_right);
        View view = v.findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = getStatusBarHeight();


        ll_title_bar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }





}
