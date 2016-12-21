package com.qingcheng.mobilemanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.adapter.AppManagerAdapter;
import com.qingcheng.mobilemanager.bean.AppInfo;
import com.qingcheng.mobilemanager.engine.AppInfoProvider;
import com.qingcheng.mobilemanager.utils.ToastUtils;

import java.util.ArrayList;


/**
 * 流量监控activity
 */
public class AppManagerActivity extends BaseSonActivity{

    private RelativeLayout rlTrafficPre;
    private ListView lvAppDisplay;
    private ArrayList<AppInfo> mDatas;
    private Context context = this;
    private RelativeLayout rlUninstall;
    private AppManagerAdapter mAdapter;
    private AppInfo mCurrentCliclAppInfo;  //当前被点击info
    private TextView tvCheckAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        initView();
        preBack();
    }

    @Override
    public void initView() {
        rlTrafficPre = (RelativeLayout) findViewById(R.id.rl_traffic_pre);
        lvAppDisplay = (ListView) findViewById(R.id.lv_app_display);
        rlUninstall = (RelativeLayout) findViewById(R.id.rl_uninstall);
        tvCheckAll = (TextView) findViewById(R.id.tv_check_all);
        initData();
    }

    @Override
    public void preBack(){
        rlTrafficPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /**
         * 卸载键被点击
         */
         rlUninstall.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 uninstall();
             }
         });
        /**
         * 全选事件
         */
        tvCheckAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(context,"全选被点击");
            }
        });
    }

    @Override
    public void initData() {
        new Thread(){
            @Override
            public void run() {
                ArrayList<AppInfo> list = AppInfoProvider.getInstalledApps(getApplicationContext());
                mDatas = new ArrayList<>();
                /**
                 * 区分出来用户应用
                 */
                for (AppInfo info : list){
                    if (info.isUserApp){
                        mDatas.add(info);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AppManagerAdapter(context, mDatas);
                        lvAppDisplay.setAdapter(mAdapter);
                    }
                });
            }
        }.start();
    }

    /**
     * 点击卸载的方法
     */
    public void uninstall(){
        ToastUtils.showToast(context,"卸载被点击");
    }

}
