package com.qingcheng.mobilemanager.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.qingcheng.mobilemanager.application.MyApplication;

/**
 * Description: 处理Ui操作相关的一些方法
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 16:33
 */

public class UiUtils {
    /**
     * 获取在Application中获取的Context
     */
    public static Context getContext(){
        return MyApplication.getContext();
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler(){
        return MyApplication.getHandler();
    }

    /**
     * 获取主线程的id
     * @return
     */
    public static int getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    /**
     * 获取字符串资源
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    // 获取字符串数组
    public static String[] getStringArray(int resId) {
        return getContext().getResources().getStringArray(resId);
    }

    // 获取drawable
    public static Drawable getDrawable(int resId) {
        return getContext().getResources().getDrawable(resId);
    }

    // 获取color的值
    public static int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }

    // 获取颜色的状态选择器
    public static ColorStateList getColorStateList(int resId) {
        return getContext().getResources().getColorStateList(resId);
    }

    // 获取dimen下定义的值
    public static int getDimen(int resId) {
        return getContext().getResources().getDimensionPixelSize(resId);
    }

    // dp--px
    public static int dp2px(int dp) {
        // 1、获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        // 2、进行乘法操作
        return (int) (dp * density + 0.5);
    }

    // px--dp
    public static int px2dp(int px) {
        // 1、获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        // 2、进行除法操作
        return (int) (px / density + 0.5);
    }

    // 判断当前线程是否处于主线程
    public static boolean isRunOnUiThread() {
        // 1、获取当前线程的线程id
        int currentThreadId = android.os.Process.myTid();
        // 2、获取主线程的线程id
        int mainThreadId = getMainThreadId();
        // 3、比较
        return currentThreadId == mainThreadId;
    }

    // 保证传递进来的r一定是在主线程中运行
    public static void runOnUiThread(Runnable r) {
        if (isRunOnUiThread()) {
            r.run();
            // new Thread(r).start();//此时启动了子线程
        } else {
            getMainThreadHandler().post(r);// 将r丢到了主线程的消息队列当中
        }
    }

    public static View inflate(int resId) {
        View view = View.inflate(getContext(), resId, null);
        return view;
    }

    public static void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //获取圆角矩形  -- shape
    public static GradientDrawable getGradientDrawable(int radius, int color) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);// 设置圆角半径
        drawable.setColor(color);
        return drawable;
    }
    //selector
    public static StateListDrawable getSelecor(Drawable pressedDrawable, Drawable normalDrawable) {
        StateListDrawable selector = new StateListDrawable();
        //设置某些状态下应该显示的图片
        selector.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        selector.addState(new int[]{}, normalDrawable);
        return selector;
    }

    public static int getScreenWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }
}
