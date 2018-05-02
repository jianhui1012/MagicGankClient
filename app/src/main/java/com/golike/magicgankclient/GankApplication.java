package com.golike.magicgankclient;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/4/24.
 */

public class GankApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        Bugtags.start("1a62eabc435a64aff960f2c524e99b7d", this, Bugtags.BTGInvocationEventBubble);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
