package com.rgk.mobilemanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.widget.TextLinear;

/**
 * 垃圾清理activity
 */
public class CleanRamActivity extends BaseSonActivity {

    private static LinearLayout llRubbishActivityAdd;
    private RelativeLayout rlRubbish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_ram);
        initView();
        pretView(getApplicationContext());
        preBack();
    }

    @Override
    public void initView() {
        llRubbishActivityAdd = (LinearLayout) findViewById(R.id.ll_rubbish_activity_add);
        rlRubbish = (RelativeLayout) findViewById(R.id.rl_rubbish_pre);
    }

    public static void pretView(final Context context){
       llRubbishActivityAdd.removeAllViews();
        for (int i = 0; i < 5; i++){
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LinearLayout llView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_textview,null);
                    TextLinear tlPersion = (TextLinear) llView.findViewById(R.id.tlPersion);
                    tlPersion.setText("清理垃圾","第"+finalI+"条");
                    llRubbishActivityAdd.addView(llView);
                }
            },350*i);
        }
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
