package com.qingcheng.mobilemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.qingcheng.mobilemanager.R;

import static com.qingcheng.mobilemanager.ui.fragment.CleanFragment.pretView;

/**
 * 垃圾清理activity
 */
public class CleanRamActivity extends BaseSonActivity {

    private RelativeLayout rlRubbish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_ram);
        initView();
        preBack();
    }

    @Override
    public void initView() {
        rlRubbish = (RelativeLayout) findViewById(R.id.rl_rubbish_pre);
    }

    @Override
    public void preBack(){
        rlRubbish.setOnClickListener(new View.OnClickListener() {
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
