package com.alin.lib.bannerlib.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


/**
 * @创建者 hailin
 * @创建时间 2017/8/21 10:56
 * @描述 ${}.
 */

public class CommonUtil {
    private static Context sContext;
    private static InputMethodManager mManager;
    private static final long SHOW_KEY_BOARD_DEFAULT = 200L;

    public static int dp2px(Context context, int pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        float px = pxValue * density;
        int pxInt = (int) px;
        return pxInt == 0 ? pxInt : pxInt + 1;
    }

    public static int dp2px(Context context, float pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        float px = pxValue * density;
        int pxInt = (int) px;
        return pxInt == 0 ? pxInt : pxInt + 1;
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        float dp = pxValue / scale;
        int dpInt = (int)dp;
        return dp == dpInt? dpInt: dpInt + 1;
    }

    /**
     * 获取ContentView
     * @param activity
     * @return
     */
    public static View getContentView(@Nullable Activity activity){
        return ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * @param show
     *  true 弹出键盘
     */
    public static void showOrHideKeyboard(Activity activity,boolean show) {
        if (mManager == null) {
            mManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        }

        View focusView = activity.getCurrentFocus();
        if (show) {
            if (focusView != null) {
                mManager.showSoftInput(focusView,InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }else {
                mManager.toggleSoftInput(InputMethodManager.RESULT_SHOWN,InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        }else {
            if (focusView != null) {
                mManager.hideSoftInputFromInputMethod(focusView.getWindowToken(),InputMethodManager.RESULT_SHOWN);
            }
        }
    }
}
