package com.qingcheng.mobilemanager.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.widget.TextLinear;

/**
 * 主页清理界面的Fragment
 */
public class CleanFragment extends BaseFragment {


    private static LinearLayout llAdd;
    private View view;

    public CleanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_clean, container, false);
        initView();
        return view;
    }
    @Override
    public void initView() {
        llAdd = (LinearLayout) view.findViewById(R.id.ll_add);
        llAdd.removeAllViews();
    }
    @Override
    public void initData() {


    }

    public static void pretView(final Context context){
        llAdd.removeAllViews();
        for (int i = 0; i < 4; i++){
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LinearLayout llView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_textview,null);
                    TextLinear tlPersion = (TextLinear) llView.findViewById(R.id.tlPersion);
                    tlPersion.setText("清理垃圾","第"+finalI+"条");
                    llAdd.addView(llView);
                }
            },350*i);
        }
    }
}
