package com.rgk.mobilemanager.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.rgk.mobilemanager.global.GlobalConstant;

/**
 * Description: fragment上划隐藏与下滑显示动画
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/22 17:04
 */

public class SlidingUtil {
    /**
     * 上划隐藏动画
     * @param editContainer
     * @param moveableView
     * @param view
     * @param container
     */
    public static void slideInToTop(View editContainer, View moveableView, View view, FrameLayout container){
        Log.d(GlobalConstant.HOMEACTICITY_TAG,"container view height : " + String.valueOf(view.getHeight()));

        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", container.getHeight());
        anim.setDuration(0);
        anim.start();

        view.setVisibility(View.VISIBLE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", editContainer.getTop() + moveableView.getHeight()),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1)
        );
        set.setDuration(GlobalConstant.ANIMATION_DURATION);
        set.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
        set.start();
    }

    /**
     * 下滑显示动画
     * @param editContainer
     * @param view
     * @param container
     */
    public static void slideInToBottom(View editContainer, View view, FrameLayout container){
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"transY", container.getHeight()),
                ObjectAnimator.ofFloat(view, "alpha", 1 , 0)
        );
        set.setDuration(GlobalConstant.ANIMATION_DURATION);
        set.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
        set.start();
    }
}
