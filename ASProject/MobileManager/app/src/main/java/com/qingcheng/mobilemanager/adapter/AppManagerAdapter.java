package com.qingcheng.mobilemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.holder.AppViewholder;

import java.util.ArrayList;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 17:07
 */

public class AppManagerAdapter extends MyBaseAdapter {

    public AppManagerAdapter(Context context, ArrayList dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppViewholder holder = null;
        if (holder == null){
            convertView = inflater.inflate(R.layout.list_item_app_manager,null);
            holder = new AppViewholder();

            holder.initView();
            convertView.setTag(holder);
        }else{
            holder = (AppViewholder) convertView.getTag();
        }
        return convertView;
    }
}
