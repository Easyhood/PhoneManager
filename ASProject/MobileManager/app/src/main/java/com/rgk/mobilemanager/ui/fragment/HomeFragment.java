package com.rgk.mobilemanager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rgk.mobilemanager.Listeners.CleanFragmentTouchListener;
import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.global.GlobalConstant;
import com.rgk.mobilemanager.ui.activity.CleanRamActivity;
import com.rgk.mobilemanager.ui.activity.RightsActivity;
import com.rgk.mobilemanager.ui.activity.SaveEleActivity;
import com.rgk.mobilemanager.ui.activity.TouchCleanActivity;
import com.rgk.mobilemanager.utils.EventUtil;
import com.rgk.mobilemanager.utils.PrefUtils;
import com.rgk.mobilemanager.utils.RotatingUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {

    private CleanFragmentTouchListener touchListener;
    private View shortCircular;
    private View view;
    private TextView tvOpt;


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;
    }

    @Override
    public void initView() {
        checkOpened();
        shortCircular = view.findViewById(R.id.short_circular);
        tvOpt = (TextView) view.findViewById(R.id.tv_opt);
        tvOpt.setText(R.string.on_examination);
        Animation rotatingSelf = RotatingUtil.RotatingSelf(mActivity);
        if (rotatingSelf != null) {
            shortCircular.setAnimation(rotatingSelf);
        }

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final RelativeLayout rlFragmentHomeOpt = (RelativeLayout) view.findViewById(R.id.rl_fragment_home_opt);
        final RelativeLayout rlTransDes = (RelativeLayout) view.findViewById(R.id.rl_trans_des);
        rlTransDes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                touchListener.onCleanFragmentTouch(getView(), rlFragmentHomeOpt, true);
                CleanFragment.pretView(mActivity);
            }
        });
        final RelativeLayout homeRam = (RelativeLayout) view.findViewById(R.id.home_ram);
        homeRam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CleanRamActivity.class);
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
        final RelativeLayout homeTouch = (RelativeLayout) view.findViewById(R.id.home_touch);
        homeTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, TouchCleanActivity.class);
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


    public void setCleanFragmentTouchListener(CleanFragmentTouchListener touchListener) {
        this.touchListener = touchListener;
    }

    /**
     * 第一次打开时应该做的事情
     */
    public void firstOpenEvent() {
        //圈圈开始转
        //显示检测中
        Log.e("-----HomeFragment----", "开始执行firstOpenEvent方法");
        new Thread() {
            @Override
            public void run() {

                long startTime = System.currentTimeMillis();

                //检测智能省电是否打开
                //检测权限管理是否开启
                //检测后台进程打开了多少个
                //数字开始增长至相应分数

                long endTime = System.currentTimeMillis();
                long useTime = endTime - startTime;
                if (useTime < GlobalConstant.MIN_CHECK_TIME) {
                    SystemClock.sleep(GlobalConstant.MIN_CHECK_TIME - useTime);
                } else if (useTime > GlobalConstant.MAX_CHECK_TIME) {
                    SystemClock.sleep(GlobalConstant.MIN_CHECK_TIME);
                }
                EventBus.getDefault().post(new EventUtil(GlobalConstant.CHECK_IS_END));

            }
        }.start();

    }

    /**
     * 检测是否打开过App
     */
    public void checkOpened(){
        PrefUtils.putBoolean(mActivity,GlobalConstant.FIRST_OPEN_STRING,false);
        boolean isOpened = PrefUtils.getBoolean(mActivity, GlobalConstant.FIRST_OPEN_STRING, false);
        Log.e("------HomeFragment-----","isOpened : "+ isOpened);
        if (!isOpened){
            EventBus.getDefault().post(new EventUtil(GlobalConstant.FIRST_OPEN_INT));
            Log.e("-----HomeFragment------","发送了 "+GlobalConstant.FIRST_OPEN_INT+" 消息");
            //标记已打开过App
            PrefUtils.putBoolean(mActivity,GlobalConstant.FIRST_OPEN_STRING,true);
        }
    }

    /**
     * 处理eventbus发送来的事件
     * @param event
     */
    @Subscribe
    public void onEvent(EventUtil event){
        int msg = event.getIntMsg();
        switch (msg){
            case GlobalConstant.FIRST_OPEN_INT:
                Log.e("---HomeFragment---","接收到了"+ GlobalConstant.FIRST_OPEN_INT +"消息");
                firstOpenEvent();
                break;
        }
    }
}


