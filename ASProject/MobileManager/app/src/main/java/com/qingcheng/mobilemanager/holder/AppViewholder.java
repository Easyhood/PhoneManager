package com.qingcheng.mobilemanager.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.utils.UiUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/12/20 16:56
 */

public class AppViewholder<AppInfo> extends BaseHolder {

    private ImageView ivAppIcon;
    private TextView tvAppName;
    private CheckBox cbAppUninstall;

    @Override
    public View initView() {
        View view = UiUtils.inflate(R.layout.list_item_app_manager);
        ivAppIcon = (ImageView) view.findViewById(R.id.iv_app_icon);
        tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
        cbAppUninstall = (CheckBox) view.findViewById(R.id.cb_app_uninstall);

        return view;
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
    }
}
