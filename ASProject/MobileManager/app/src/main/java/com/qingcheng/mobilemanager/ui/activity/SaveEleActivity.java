package com.qingcheng.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.qingcheng.mobilemanager.R;

/**
 * 省电activity
 */
public class SaveEleActivity extends BaseSonActivity {

    private RelativeLayout rlSavePre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_ele);
        initView();
        preBack();
    }

    @Override
    public void initView() {
        rlSavePre = (RelativeLayout) findViewById(R.id.rl_save_pre);
    }

    @Override
    public void preBack(){
        rlSavePre.setOnClickListener(new View.OnClickListener() {
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
