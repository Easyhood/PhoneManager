package com.qingcheng.mobilemanager.bean;

import android.graphics.drawable.Drawable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 17:45
 */

public class AppInfo {
    public String appName;
    public Drawable appIcon;
    public String packageName;
    public long length;

    public boolean isUserApp;//true表示用户应用，false表示是系统应用


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 对比两个对象是否是一个对象
     */
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof AppInfo)){
            return false;
        }
        AppInfo appInfo = (AppInfo) o;

        if (appInfo.packageName == null){
            return false;
        }

        if (appInfo.packageName == null) {
            return false;
        }

        if (appInfo.packageName.equals(this.packageName)){
            return true;
        }
        return false;
    }
}
