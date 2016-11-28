package com.rgk.mobilemanager.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.rgk.mobilemanager.R;

/**
 * Description: 旋转动画
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/25 15:19
 */

public class RotatingUtil {
    /**
     * 旋转动画的方法
     * @param context
     * @param id
     */
    public static Animation RotatingSelf(Context context){
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.roteta_self);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        return operatingAnim;
    }
}
