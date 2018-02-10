package com.alin.lib.bannerlib.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;


/**
 * @创建者 hailin
 * @创建时间 2017/8/21 18:17
 * @描述 ${}.
 */
public class AppUtil {
    private static final AppUtil INSTANCE = new AppUtil();
    private static final String START_MODEL  = "START_MODEL";
    private Context mAppContext;

    public static AppUtil getInstance(){
        return INSTANCE;
    }
//    判断当前进场是否是主进程
    public static boolean isMainProcess(Context context){
       String processName = getProcessName(context);
        return processName != null && processName.equals(context.getPackageName());
    }

    private static String getProcessName(Context context) {
        String processName = null;
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = manager.getRunningAppProcesses();
        if (appProcesses != null && !appProcesses.isEmpty()) {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo process : appProcesses) {
                if (process.pid == myPid) {
                    processName = process.processName;
                    return processName;
                }
            }
        }
        return processName;
    }

/***
 * 前台进程：onCreate、onStart、onResume、onPause
 * 非前台进程：onStop、onDestory
 * 判断当前进程是否是前台进程
 * */
    public static boolean isAppForeground(Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
            if (infos != null && !infos.isEmpty()) {
                for (ActivityManager.RunningAppProcessInfo info : infos) {
                    if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {   //前台进程
                        return info.processName.equals(context.getPackageName());
                    }
                }
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean hideStatusBarIfSupported(Activity activity){
        boolean hasHide = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            hasHide = true;
        }

        return hasHide;
    }

//    获取最底层的rootView
    public View getContentView(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * @deprecated
     *  对传入参数是否相等校验
     */
    public static void checkIsEqual(int arg0,int arg1,String errorInfo) {
        if (arg0 != arg1)
            throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数值相等！" : errorInfo);
    }

    /**
     * @deprecated
     *  对传入参数判空校验
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T t) {
        return  checkNotNull(t,null);
    }

    public static <T> T checkNotNull(T t, @NonNull String errorInfo) {
        if (t == null)
            throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数异常！" : errorInfo);
        return t;
    }


    /**
     * @deprecated  校验list是否为空
     * @param list
     * @return
     */
    public static List checkListNotNull(List list) {
       return checkListNotNull(list,null);
    }

    public static List checkListNotNull(List list,String errorInfo) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入list==null或者list.size() = 0" : errorInfo);
        return list;
    }

    /**
     * @deprecated  校验传入参数是否> 0
     * @param number
     * @return
     */
    public static Number checkNotZero(@Nullable Number number) {
        return checkNotZero(number,null);
    }

    public static Number checkNotZero(@Nullable Number number, String errorInfo) {
        if (number == null){
            throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数==null！" : errorInfo);
        }else if (number instanceof Integer){
            if (number.intValue() < 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof Double){
            if (number.doubleValue() < 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof  Float){
            if (number.floatValue() < 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof Long){
            if (number.longValue() < 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }
        return number;
    }

//    大于0
    public static Number checkLargeThanZero(Number number) {
        return  checkLargeThanZero(number,null);
    }

    public static Number checkLargeThanZero(Number number, String errorInfo) {
        if (number == null){
            throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数==null！" : errorInfo);
        }else if (number instanceof Integer){
            if (number.intValue() <= 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof Double){
            if (number.doubleValue() <= 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof  Float){
            if (number.floatValue() <= 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }else if (number instanceof Long){
            if (number.longValue() <= 0) {
                throw new IllegalArgumentException(TextUtils.isEmpty(errorInfo) ? "传入参数<=0！" : errorInfo);
            }
        }

        return number;
    }

    /**
     * @deprecated  获取版本号
     * @param context
     * @return
     */
    public int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        }catch (Throwable t){
            t.printStackTrace();
        }

        return versionCode;
    }

    //获取meta值
    public static int getStartModel(Context context){
        ApplicationInfo applicationInfo = getPackageInfo(context);
        return (int) applicationInfo.metaData.get(START_MODEL);
    }

    private static ApplicationInfo getPackageInfo(Context context) {
        checkNotNull(context,"context == null,in getPackageInfo()");
        ApplicationInfo applicationInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        }catch (PackageManager.NameNotFoundException t){
            t.printStackTrace();
        }
        return applicationInfo;
    }

}
