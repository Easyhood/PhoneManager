package com.qingcheng.mobilemanager.engine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.qingcheng.mobilemanager.bean.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 获取已安装的应用信息
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/21 11:10
 */

public class AppInfoProvider {
    /**
     * 获取已安装的软件
     */
    public static ArrayList<AppInfo> getInstalledApps(Context ctx){
        PackageManager pm = ctx.getPackageManager();

        /**
         * 获取已安装的包
         */
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        ArrayList<AppInfo> list = new ArrayList<>();
        for (PackageInfo packageInfo : installedPackages){
            AppInfo info = new AppInfo();
            String packageName = packageInfo.packageName;
            info.packageName = packageName;

            /**
             * 获取当前包的应用信息
             */
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;

            /**
             * 系统分配给应用程序的uid
             */
            int uid = applicationInfo.uid;

            String name = applicationInfo.loadLabel(pm).toString();
            Drawable icon = applicationInfo.loadIcon(pm);
            info.appName = name;
            info.appIcon = icon;

            /**
             * 安装路径
             */
            String sourceDir = applicationInfo.sourceDir;
            File file = new File(sourceDir);
            long length = file.length();//安装包大小
            info.length = length;

            /**
             * 区分系统应用和用户应用
             */
            int flags = applicationInfo.flags;
            if ((flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM){
                /**
                 * 具备系统应用属性
                 */
                info.isUserApp = false;
            } else {
                info.isUserApp = true;
            }

            list.add(info);
        }


        return list;
    }
}
