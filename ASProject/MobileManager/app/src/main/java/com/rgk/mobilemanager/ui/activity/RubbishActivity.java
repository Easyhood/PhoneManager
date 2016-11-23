package com.rgk.mobilemanager.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.widget.TextLinear;

/**
 * 垃圾清理activity
 */
public class RubbishActivity extends BaseActivity {

    private static LinearLayout llRubbishActivityAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish);
        llRubbishActivityAdd = (LinearLayout) findViewById(R.id.ll_rubbish_activity_add);
        pretView(getApplicationContext());
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
}
