package com.qingcheng.mobilemanager.holder;

import android.view.View;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 16:21
 */

public  abstract class BaseHolder<T> {
    public View convertView;
    public T mData;

    public BaseHolder(){
        convertView = initView();
        convertView.setTag(this);
    }

    public abstract View initView();

    public void setData(T data){
        mData = data;
    }
}
