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

    //检查最小时间
    public static final long MIN_CHECK_TIME = 4000;

    //检查最大时间
    public static final long MAX_CHECK_TIME = 6000;

    //矩形
    public static final Rect mTmpRect = new Rect();

    //int消息队列
    public static final int FIRST_OPEN_INT = 1; //第一次打开App
    public static final int CHECK_IS_END = 2; //检查结束
    public static final int IN_THE_OPT_INT = 3; //正在优化中
    public static final int OPT_IS_END_INT = 4; //优化结束

    //string消息队列
    public static final String CONFIG = "config";
    public static final String FIRST_OPEN_STRING = "first open"; //第一次打开
}
