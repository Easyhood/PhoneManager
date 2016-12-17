package com.qingcheng.mobilemanager.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/22 10:48
 */

public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    /**
     * 初始化布局
     */
    public abstract void initView();


    /**
     * 初始化数据
     */
    public abstract void initData();
}
