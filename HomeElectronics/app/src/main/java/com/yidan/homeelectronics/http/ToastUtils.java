package com.yidan.homeelectronics.http;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yxy on 2017/7/6 0006.
 */

public class ToastUtils {

    public static void showToast(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}
