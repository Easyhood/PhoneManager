package com.qingcheng.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.qingcheng.mobilemanager.R;

/**
 * 流量监控activity
 */
public class AppManagerActivity extends BaseSonActivity {

    private RelativeLayout rlTrafficPre;
    private ListView lvAppDisplay;

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

    }

    @Override
    public void preBack(){
        rlTrafficPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 点击卸载键的逻辑
     */
    public void uninstall(){

    }
}
