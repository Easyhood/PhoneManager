package com.rgk.mobilemanager.global;

import android.graphics.Rect;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.rgk.mobilemanager.ui.activity.HomeActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/22 16:44
 */

public class GlobalConstant {
    //homeactivity标记
    public static final String HOMEACTICITY_TAG = HomeActivity.class.getName();

    //动画插入器
    public static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();

    //动画持续时间
    public static final int ANIMATION_DURATION = 250;

    //矩形
    public static final Rect mTmpRect = new Rect();
}
