package com.rgk.mobilemanager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rgk.mobilemanager.Listeners.CleanFragmentTouchListener;
import com.rgk.mobilemanager.R;

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
        rlFragmentHomeOpt.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
              touchListener.onCleanFragmentTouch(getView(), rlFragmentHomeOpt, true);
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
