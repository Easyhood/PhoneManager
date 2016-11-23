package com.rgk.mobilemanager.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rgk.mobilemanager.R;
import com.rgk.mobilemanager.utils.DrawableUtil;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/23 9:48
 */

public class TextLinear extends LinearLayout {
    private TextView contenteTv;
    private TextView subContenteTv;
    private View topDivider;
    private View bottomDivider;
    private Context context;

    public TextLinear(Context context) {
        super(context);
        this.context=context;
        initView(context);
    }

    @SuppressLint("NewApi")
    public TextLinear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView(context);
        init(context, attrs);
    }

    public TextLinear(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView(context);
        init(context, attrs);
    }

    public void initView(Context context) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_textlinear, this);
        contenteTv=(TextView) findViewById(R.id.widget_text_content);
        topDivider=findViewById(R.id.widget_divider_top);
        bottomDivider =findViewById(R.id.widget_divider_bottom);
        subContenteTv=(TextView) findViewById(R.id.widget_text_subContent);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextLinear);
        contenteTv.setText(a.getString(R.styleable.TextLinear_leftText));
        Drawable leftDrawable=a.getDrawable(R.styleable.TextLinear_leftDrawable);
        contenteTv.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null,
                null);
        contenteTv.setPadding(a.getDimensionPixelOffset(R.styleable.TextLinear_leftPadding,0),0,0,0);
//        contenteTv.setCompoundDrawables(leftDrawable,null,null,null);
        subContenteTv.setText(a.getString(R.styleable.TextLinear_rightText));
        boolean rightDrawable=a.getBoolean(R.styleable.TextLinear_rightDrawable,true);
        boolean topVisibility=a.getBoolean(R.styleable.TextLinear_topDriver,false);
        boolean bottomVisibility=a.getBoolean(R.styleable.TextLinear_bottomDriver,false);
        contenteTv.setTextColor(a.getColor(R.styleable.TextLinear_apptextColor, getResources().getColor(R.color.C6)));
//		subContenteTv.setVisibility(rightDrawable ? View.VISIBLE : View.GONE);
        if(rightDrawable){
            subContenteTv.setCompoundDrawablesWithIntrinsicBounds(null, null, DrawableUtil.GetDrawable(context,R.mipmap.ic_folder_right),
                    null);
        }else{
            subContenteTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,null);
        }
        topDivider.setVisibility(topVisibility?View.VISIBLE:View.INVISIBLE);
        bottomDivider.setVisibility(bottomVisibility?View.VISIBLE:View.INVISIBLE);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



    /**
     * 文本赋值，
     * @param content    左侧字
     * @param subcontent 右侧字
     */
    public void setText(String content,String subcontent) {
        if (!TextUtils.isEmpty(content)) {
            contenteTv.setText(content);
        }
        if (!TextUtils.isEmpty(subcontent)) {
            subContenteTv.setText(subcontent);
        }
    }
    /**
     * 文本设置字体大小，
     * @param leftSize    左侧字大小
     * @param rightSize   右侧字大小
     */
    public void setTextSize(int leftSize,int rightSize) {
        if (leftSize != 0) {
            contenteTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,leftSize);
        }
        if (rightSize != 0) {
            subContenteTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,rightSize);
        }
    }
    /**
     * 给左侧字添加左边图标
     * @param leftDrawable    左侧字
     */
    public void setLeftDrable(Drawable leftDrawable) {
        contenteTv.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null,
                null);
    }
    /**
     * 文本赋值，
     * @param subcontent 右侧字
     */
    public void setSubText(String subcontent) {
        if (!TextUtils.isEmpty(subcontent)) {
            subContenteTv.setText(subcontent);
        }else{
            subContenteTv.setText("");
        }
    }
    /**
     * 获取右侧文本，
     */
    public String getSubText() {
        return subContenteTv.getText().toString().trim();
    }

    /**
     * 设置下边线是否显示
     * @param visibility
     */
    public  void setBottomDividerVisibility(int visibility){
        bottomDivider.setVisibility(visibility);
    }

    /**
     * 设置上边线是否显示
     * @param visibility
     */
    public  void setTopDividerVisibility(int visibility){
        topDivider.setVisibility(visibility);
    }

    public  void setRightDrawable(int drawable){
        subContenteTv.setCompoundDrawables(null,null,
                DrawableUtil.GetDrawable(context, (Integer) drawable),null);
    }


}
