package com.kirito.simpletoolbar;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Chipmunk on 2017/8/3.
 */

public class mToolBar extends Toolbar {

    private static final String TAG = "mToolBar";

    public static final int MIDDLE = -1;
    public static final int LEFT = -2;
    public static final int RIGHT = -3;

    private LayoutParams params;
    private TextView title;
    private boolean hasTitle;
    private String titleContent;
    private float titleTextSize = 20;
    private int titleTextColor = Color.BLACK;

    private Context mContext;

    public mToolBar(Context context) {
        super(context, null);
        this.mContext = context;
    }

    public mToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setContentInsetsAbsolute(0,0);
        this.mContext = context;
    }

    public mToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    /**
     * 获取applicationName
     */
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = mContext.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }


    /**
     * 是否有标题,默认没有
     *
     * @param hasTitle
     */
    public void hasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        if (hasTitle) {
            title = new TextView(getContext());
            title.setMaxLines(1);
            setTitleTextSize(this.titleTextSize);
            setTitleTextColor(this.titleTextColor);
            params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            setTitleContent(getApplicationName());
            addView(title, params);
        }
    }

    /**
     * 设置标题的位置
     *
     * @param titlePostion 标题位置
     */
    public void setTitlePositon(int titlePostion) {
        if (titlePostion == mToolBar.MIDDLE) {
            params.gravity = Gravity.CENTER;
        } else if (titlePostion == mToolBar.LEFT) {
            params.gravity = Gravity.LEFT;
        } else if (titlePostion == mToolBar.RIGHT) {
            params.gravity = Gravity.RIGHT;
        }
    }

    /**
     * 设置标题内容,默认是appName
     *
     * @param titleContent
     */
    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
        title.setText(titleContent);
    }

    /**
     * 获取标题内容
     *
     * @return 标题内容
     */
    public String getTitleContent() {
        return this.titleContent;
    }

    /**
     * 设置标题尺寸,默认20sp
     *
     * @param titleTextSize
     */
    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
        title.setTextSize(titleTextSize);
    }

    /**
     * 设置标题颜色,默认是黑色
     *
     * @param titleTextColor
     */
    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        title.setTextColor(titleTextColor);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}