package com.qingcheng.mobilemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.bean.AppInfo;
import com.qingcheng.mobilemanager.holder.AppViewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 17:07
 */

public class AppManagerAdapter extends MyBaseAdapter<AppInfo> {

    private Context mContext;
    private ArrayList<AppInfo> mDatas;
    public List<Boolean> mChecked;

    public AppManagerAdapter(Context context, ArrayList<AppInfo> dataList) {
        super(context, dataList);
        this.mContext = context;
        this.mDatas = dataList;

        mChecked = new ArrayList<Boolean>();
        for (int i = 0; i < dataList.size(); i++){
            mChecked.add(false);
        }
    }

    @Override
    public AppInfo getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AppViewholder holder ;
        if (convertView == null){
            convertView = View.inflate(mContext,R.layout.list_item_app_manager,null);
            holder = new AppViewholder();
            holder.ivAppIcon = (ImageView)convertView.findViewById(R.id.iv_app_icon);
            holder.tvAppName = (TextView) convertView.findViewById(R.id.tv_app_name);
            holder.cbAppUninstall = (CheckBox) convertView.findViewById(R.id.cb_app_uninstall);
            convertView.setTag(holder);
        }else{
            holder = (AppViewholder) convertView.getTag();
        }
        AppInfo info = getItem(position);
        holder.ivAppIcon.setImageDrawable(info.appIcon);
        holder.tvAppName.setText(info.appName);
        holder.cbAppUninstall.setChecked(mChecked.get(position));
        return convertView;
    }
}
