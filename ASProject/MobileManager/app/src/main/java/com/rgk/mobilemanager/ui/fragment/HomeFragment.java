package com.rgk.mobilemanager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rgk.mobilemanager.Listeners.CleanFragmentTouchListener;
import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.ui.activity.RightsActivity;
import com.rgk.mobilemanager.ui.activity.RubbishActivity;
import com.rgk.mobilemanager.ui.activity.SaveEleActivity;
import com.rgk.mobilemanager.ui.activity.TrafficActivity;

/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {

    private CleanFragmentTouchListener touchListener;

    public HomeFragment(){
    }

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

       final RelativeLayout rlFragmentHomeOpt = (RelativeLayout) view.findViewById(R.id.rl_fragment_home_opt);
        rlFragmentHomeOpt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              touchListener.onCleanFragmentTouch(getView(), rlFragmentHomeOpt, true);
                CleanFragment.pretView(mActivity);
            }
        });
        final RelativeLayout homeRubbish = (RelativeLayout) view.findViewById(R.id.home_rubbish);
        homeRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RubbishActivity.class);
                startActivity(intent);
                mActivity.finish();
            }
        });
        final RelativeLayout homeSave = (RelativeLayout) view.findViewById(R.id.home_save);
        homeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, SaveEleActivity.class);
                startActivity(intent);
                mActivity.finish();
            }
        });
        final RelativeLayout homeTraffic = (RelativeLayout) view.findViewById(R.id.home_traffic);
        homeTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, TrafficActivity.class);
                startActivity(intent);
                mActivity.finish();
            }
        });
        final RelativeLayout homeRights = (RelativeLayout) view.findViewById(R.id.home_rights);
        homeRights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RightsActivity.class);
                startActivity(intent);
                mActivity.finish();
            }
        });

    }

    @Override
    public void initData() {

    }

    public void setCleanFragmentTouchListener(CleanFragmentTouchListener touchListener){
        this.touchListener = touchListener;
    }
}
