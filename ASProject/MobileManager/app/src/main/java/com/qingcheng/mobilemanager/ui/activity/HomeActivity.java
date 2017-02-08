package com.qingcheng.mobilemanager.ui.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingcheng.mobilemanager.Listeners.CleanFragmentTouchListener;
import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.global.GlobalConstant;
import com.qingcheng.mobilemanager.ui.fragment.CleanFragment;
import com.qingcheng.mobilemanager.ui.fragment.HomeFragment;
import com.qingcheng.mobilemanager.utils.EventUtil;
import com.qingcheng.mobilemanager.utils.RotatingUtil;
import com.qingcheng.mobilemanager.utils.SlidingUtil;
import com.qingcheng.mobilemanager.widget.RiseNumberTextView;

public class HomeActivity extends BaseActivity implements CleanFragmentTouchListener{

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            EventUtil event = (EventUtil)data.getSerializable(GlobalConstant.KEY);
            int intMsg = event.getIntMsg();
            switch (intMsg){
                case GlobalConstant.CHECK_IS_END:
                    mEvent = GlobalConstant.IN_THE_OPT_INT;
                    mStatus = GlobalConstant.HOMEFRAG_IS_WORKING;
                    break;
                case GlobalConstant.IN_THE_OPT_INT:
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //大圈圈显示，小圈圈开始转至停
                            longCircular.setVisibility(View.VISIBLE);
                            shortCircular.clearAnimation();
                            //显示优化完毕 点击返回字样
                            tvOpt.setText(R.string.optimization_complete);
                            tvHomeReturn.setVisibility(View.VISIBLE);
                            //可以点击
                            rlTransDes.setClickable(true);
                        }
                    });
                    break;
            }
        }
    };

    private FrameLayout mHomeContainer;
    private HomeFragment homeFragment;
    private CleanFragment cleanFragment;

    private boolean cleanFragmentVisible;
    private int mEvent = GlobalConstant.FIRST_OPEN_INT;
    private int mStatus = GlobalConstant.HOMEFRAG_IS_WORKING;

    private TextView tvOpt;
    private RelativeLayout rlFragmentHome;
    private LinearLayout llFragmentHome;
    private RiseNumberTextView tvHomeCount;
    private TextView tvHomeReturn;

    private View longCircular;
    private FrameLayout cleanFrame;
    private View cleanView;
    private View cleanMovableView;
    private boolean cleanAnimated;
    private View shortCircular;
    private View rlTransDes;

    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHomeContainer = (FrameLayout) findViewById(R.id.home_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        homeFragment.setCleanFragmentTouchListener(this);
        fragmentManager.beginTransaction().replace(R.id.home_fragment,homeFragment).commit();
        homeFragment.setHandler(mHandler);

        cleanFragment = new CleanFragment();
        fragmentManager.beginTransaction().replace(R.id.clean_fragment,cleanFragment).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCleanFragmentTouch(View view, View movableView, boolean animated) {
        cleanView = view;
        cleanMovableView = movableView;
        cleanAnimated = animated;
        cleanFrame = (FrameLayout) findViewById(R.id.clean_fragment);
        rlFragmentHome = (RelativeLayout) findViewById(R.id.rl_fragment_home);
        tvOpt = (TextView) findViewById(R.id.tv_opt);
        tvHomeCount = (RiseNumberTextView) findViewById(R.id.tv_home_count);
        llFragmentHome = (LinearLayout) findViewById(R.id.ll_fragment_home);
        tvHomeReturn = (TextView) findViewById(R.id.tv_home_return);
        longCircular = findViewById(R.id.long_circular);
        shortCircular = findViewById(R.id.short_circular);
        rlTransDes = findViewById(R.id.rl_trans_des);
         if (mEvent == GlobalConstant.IN_THE_OPT_INT){
            //不可点击
            rlTransDes.setClickable(false);
            llFragmentHome.setVisibility(View.INVISIBLE);
            //大圈圈隐藏
            longCircular.setVisibility(View.INVISIBLE);
            //数字增长到100
            tvHomeCount.setRiseInterval(GlobalConstant.LAST_NUMBER,100)
                    .setDuration(2000)
                    .runInt(true)
                    .start();
            //小圈圈开始转
            Animation rotatingSelf = RotatingUtil.RotatingSelf(mActivity);
            if (rotatingSelf != null) {
                shortCircular.setAnimation(rotatingSelf);
            }
            //显示优化中
            tvOpt.setText(R.string.in_the_optimization);
            view.getDrawingRect(GlobalConstant.mTmpRect);

            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -(movableView.getTop()));
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToTop(view,movableView, cleanFrame,mHomeContainer);
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(GlobalConstant.KEY,new EventUtil(GlobalConstant.IN_THE_OPT_INT));
                    message.setData(bundle);
                    mHandler.sendMessage(message);


                    mEvent = GlobalConstant.OPT_IS_END_INT;
                    mStatus = GlobalConstant.CLEANFRAG_IS_WORKING;
                }
            }.start();

        } else if (mEvent == GlobalConstant.OPT_IS_END_INT ){
            mEvent = GlobalConstant.CHECK_IS_END;
            tvOpt.setText(R.string.optimization_complete);
            tvHomeReturn.setVisibility(View.INVISIBLE);
            llFragmentHome.setVisibility(View.VISIBLE);
            view.getDrawingRect(GlobalConstant.mTmpRect);
            rlTransDes.setClickable(false);
            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Movable view top: " + String.valueOf(movableView.getTop()));
            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Container view top: " + String.valueOf(view.getTop()));

            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0);
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToBottom(view, cleanFrame, mHomeContainer);
        }

    }



    /**
     * 重写返回键
     */
    @Override
    public void onBackPressed() {
        if (mEvent == GlobalConstant.OPT_IS_END_INT){
            mEvent = GlobalConstant.CHECK_IS_END;

            //大圈圈显示，小圈圈开始转至停
            longCircular.setVisibility(View.VISIBLE);
            shortCircular.clearAnimation();

            tvOpt.setText(R.string.optimization_complete);
            tvHomeReturn.setVisibility(View.INVISIBLE);
            llFragmentHome.setVisibility(View.VISIBLE);
            cleanView.getDrawingRect(GlobalConstant.mTmpRect);
            rlTransDes.setClickable(false);

            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Movable view top: " + String.valueOf(cleanMovableView.getTop()));
            Log.d(GlobalConstant.HOMEACTICITY_TAG, "Container view top: " + String.valueOf(cleanView.getTop()));

            ObjectAnimator anim = ObjectAnimator.ofFloat(cleanView, "translationY", 0);
            anim.setInterpolator(GlobalConstant.ANIMATION_INTERPOLATOR);
            anim.setDuration(GlobalConstant.ANIMATION_DURATION);
            anim.start();

            SlidingUtil.slideInToBottom(cleanView, cleanFrame, mHomeContainer);
        }else if(mEvent == GlobalConstant.CHECK_IS_END){
            super.onBackPressed();
        }else if (mStatus == GlobalConstant.HOMEFRAG_IS_WORKING){
            super.onBackPressed();
        }
    }
}
