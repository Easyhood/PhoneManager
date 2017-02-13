package com.qingcheng.mobilemanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingcheng.mobilemanager.R;
import com.qingcheng.mobilemanager.adapter.AppManagerAdapter;
import com.qingcheng.mobilemanager.bean.AppInfo;
import com.qingcheng.mobilemanager.engine.AppInfoProvider;
import com.qingcheng.mobilemanager.holder.AppViewholder;
import com.qingcheng.mobilemanager.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 应用管理的activity
 */
public class AppManagerActivity extends BaseSonActivity{

    private RelativeLayout rlTrafficPre;
    private ListView lvAppDisplay;
    private ArrayList<AppInfo> mDatas;
    private Context context = this;
    private RelativeLayout rlUninstall;
    private AppManagerAdapter mAdapter;
    private boolean checkedAll = false;
    private List<AppInfo> mCurrentCheckedAppInfo = new ArrayList<AppInfo>();  //当前被选中info的集合
    private TextView tvCheckAll;
    private List<Integer> listItemID = new ArrayList<Integer>();//被选中应用的position的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        initView();
        preBack();
    }
    @Override
    public void initView() {
        rlTrafficPre = (RelativeLayout) findViewById(R.id.rl_traffic_pre);
        lvAppDisplay = (ListView) findViewById(R.id.lv_app_display);
        rlUninstall = (RelativeLayout) findViewById(R.id.rl_uninstall);
        tvCheckAll = (TextView) findViewById(R.id.tv_check_all);
        initData();
    }

    @Override
    public void preBack(){
        rlTrafficPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /**
         * 卸载键被点击
         */
         rlUninstall.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 uninstall();
             }
         });
        /**
         * 全选事件
         */
        tvCheckAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkedAll){
                    for (int i = 0; i < mDatas.size(); i++){
                      mAdapter.mChecked.set(i,true);
                    }
                    checkedAll = true;
                    mAdapter.notifyDataSetChanged();
                }else{
                    for (int i = 0; i < mDatas.size(); i++){
                        mAdapter.mChecked.set(i,false);
                    }
                    checkedAll = false;
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
        /**
         * 每个item被点击监听
         */
        lvAppDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter.mChecked.get(position)){
                    mAdapter.mChecked.set(position,false);
                }else if (!mAdapter.mChecked.get(position)){
                    mAdapter.mChecked.set(position,true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        new Thread(){
            @Override
            public void run() {
                ArrayList<AppInfo> list = AppInfoProvider.getInstalledApps(getApplicationContext());
                mDatas = new ArrayList<>();
                /**
                 * 区分出来用户应用
                 */
                for (AppInfo info : list){
                    if (info.isUserApp){
                        mDatas.add(info);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AppManagerAdapter(context, mDatas);
                        lvAppDisplay.setAdapter(mAdapter);
                    }
                });
            }
        }.start();
    }

    /**
     * 点击卸载的方法
     */
    public void uninstall(){
        listItemID.clear();
        mCurrentCheckedAppInfo.clear();
        try{
            for (int i = 0;i< mAdapter.mChecked.size();i++){
                if (mAdapter.mChecked.get(i)){
                    listItemID.add(i);
                    mCurrentCheckedAppInfo.add(mDatas.get(i));
                }
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            ToastUtils.showIntToast(context,R.string.without_checked_app);
        }

        if (listItemID.size() == 0){
            ToastUtils.showIntToast(context,R.string.without_checked_app);
        } else {
            for (int i = 0; i < mCurrentCheckedAppInfo.size(); i++){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DELETE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + mCurrentCheckedAppInfo.get(i).packageName));
                startActivityForResult(intent,0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAdapter.notifyDataSetChanged();
        initView();
    }
}
