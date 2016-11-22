package com.rgk.mobilemanager.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgk.mobilemanager.R;

/**
 * 主页清理界面的Fragment
 */
public class CleanFragment extends BaseFragment {


    public CleanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clean, container, false);
        return view;
    }

    @Override
    public void initData() {

    }
}
