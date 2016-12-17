package com.qingcheng.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.qingcheng.mobilemanager.R;

public class RightsActivity extends BaseSonActivity {

    private RelativeLayout rlRightsPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rights);
        initView();
        preBack();
    }

    @Override
    public void initView() {
        rlRightsPre = (RelativeLayout) findViewById(R.id.rl_rights_pre);
    }


    @Override
    public void preBack(){
        rlRightsPre.setOnClickListener(new View.OnClickListener() {
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
}
