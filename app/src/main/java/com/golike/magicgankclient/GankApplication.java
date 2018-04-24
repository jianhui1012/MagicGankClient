package com.golike.magicgankclient;

import android.app.Application;

import com.bugtags.library.Bugtags;

/**
 * Created by Administrator on 2018/4/24.
 */

public class GankApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        Bugtags.start("1a62eabc435a64aff960f2c524e99b7d", this, Bugtags.BTGInvocationEventBubble);
    }
}
