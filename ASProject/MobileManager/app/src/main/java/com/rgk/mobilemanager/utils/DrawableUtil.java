package com.rgk.mobilemanager.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Description: 根据drawable获取id对象
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/23 9:51
 */

public class DrawableUtil {
    public static Drawable GetDrawable(Context mcontext, int resid) {
        Drawable drawable = mcontext.getResources().getDrawable(resid);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        return drawable;
    }
}
