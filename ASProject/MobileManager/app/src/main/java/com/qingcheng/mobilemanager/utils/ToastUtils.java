package com.qingcheng.mobilemanager.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/21 13:37
 */

public class ToastUtils {
    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }
}
