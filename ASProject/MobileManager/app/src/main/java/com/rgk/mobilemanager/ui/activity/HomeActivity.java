package com.rgk.mobilemanager.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rgk.mobilemanager.Listeners.CleanFragmentTouchListener;
import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.global.GlobalConstant;
import com.rgk.mobilemanager.ui.fragment.CleanFragment;
import com.rgk.mobilemanager.ui.fragment.HomeFragment;
import com.rgk.mobilemanager.utils.EventUtil;
import com.rgk.mobilemanager.utils.SlidingUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends BaseActivity implements CleanFragmentTouchListener{

    private FrameLayout mHomeContainer;
    private HomeFragment homeFragment;
    private CleanFragment cleanFragment;

    private boolean cleanFragmentVisible;
    private int mEvent = -1;

    private TextView tvOpt;
    private RelativeLayout rlFragmentHome;
    private LinearLayout llFragmentHome;
    private TextView tvHomeCount;
    private TextView tvHomeReturn;

    private View longCircular;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        mHomeContainer = (FrameLayout) findViewById(R.id.home_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        homeFragment.setCleanFragmentTouchListener(this);
        fragmentManager.beginTransaction().replace(R.id.home_fragment,homeFragment).commit();

        cleanFragment = new CleanFragment();
        fragmentManager.beginTransaction().replace(R.id.clean_fragment,cleanFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCleanFragmentTouch(View view, View movableView, boolean animated) {
        FrameLayout cleanFrame = (FrameLayout) findViewById(R.id.clean_fragment);
        rlFragmentHome = (RelativeLayout) findViewById(R.id.rl_fragment_home);
        tvOpt = (TextView) findViewById(R.id.tv_opt);
        tvHomeCount = (TextView) findViewById(R.id.tv_home_count);
        llFragmentHome = (LinearLayout) findViewById(R.id.ll_fragment_home);
        tvHomeReturn = (TextView) findViewById(R.id.tv_home_return);
        longCircular = findViewById(R.id.long_circular);
        if (mEvent == GlobalConstant.CHECK_IS_END){
             mEvent = GlobalConstant.IN_THE_OPT_INT;
            tvOpt.setText(R.string.optimization_complete);
            tvHomeReturn.setVisibility(View.VISIBLE);
            tvHomeCount.setText("100");
            llFragmentHome.setVisibility(View.INVISIBLE);

            view.getDrawingRect(GlobalConstant.mTmpRect);

            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -(movableView.getTop()));
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToTop(view,movableView,cleanFrame,mHomeContainer);
        } else if (mEvent == GlobalConstant.IN_THE_OPT_INT ){
            mEvent = GlobalConstant.OPT_IS_END_INT;
            tvOpt.setText(R.string.click_optimization);
            tvHomeReturn.setVisibility(View.INVISIBLE);
            tvHomeCount.setText("92");
            llFragmentHome.setVisibility(View.VISIBLE);


            view.getDrawingRect(GlobalConstant.mTmpRect);

            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Movable view top: " + String.valueOf(movableView.getTop()));
            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Container view top: " + String.valueOf(view.getTop()));

            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0);
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToBottom(view, cleanFrame, mHomeContainer);
        } else if (mEvent == GlobalConstant.OPT_IS_END_INT){
            mEvent = GlobalConstant.IN_THE_OPT_INT;
            tvOpt.setText(R.string.optimization_complete);
            tvHomeReturn.setVisibility(View.VISIBLE);
            tvHomeCount.setText("100");
            llFragmentHome.setVisibility(View.INVISIBLE);

            view.getDrawingRect(GlobalConstant.mTmpRect);

            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -(movableView.getTop()));
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToTop(view,movableView,cleanFrame,mHomeContainer);
        }

//        if (!cleanFragmentVisible){
//            cleanFragmentVisible = true;
//            mEvent = GlobalConstant.IN_THE_OPT_INT;
//            tvOpt.setText(R.string.optimization_complete);
//            tvHomeReturn.setVisibility(View.VISIBLE);
//            tvHomeCount.setText("100");
//            llFragmentHome.setVisibility(View.INVISIBLE);
//
//            view.getDrawingRect(GlobalConstant.mTmpRect);
//
//            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -(movableView.getTop()));
//            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
//            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
//            anim.start();
//
//            SlidingUtil.slideInToTop(view,movableView,cleanFrame,mHomeContainer);
//        } else {
//            cleanFragmentVisible = false;
//            tvOpt.setText(R.string.click_optimization);
//            tvHomeReturn.setVisibility(View.INVISIBLE);
//            tvHomeCount.setText("92");
//            llFragmentHome.setVisibility(View.VISIBLE);
//
//
//            view.getDrawingRect(GlobalConstant.mTmpRect);
//
//            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Movable view top: " + String.valueOf(movableView.getTop()));
//            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Container view top: " + String.valueOf(view.getTop()));
//
//            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0);
//            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
//            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
//            anim.start();
//
//            SlidingUtil.slideInToBottom(view, cleanFrame, mHomeContainer);
//        }

    }


    /**
     * 处理eventbus发送来的消息
     * @param event
     */
    @Subscribe
    public void onEvent(EventUtil event){
        int msg = event.getIntMsg();
        switch (msg){
            case GlobalConstant.CHECK_IS_END:
                mEvent = GlobalConstant.CHECK_IS_END;
                break;
        }
    }
}
