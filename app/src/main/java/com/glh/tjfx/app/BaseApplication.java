package com.glh.tjfx.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 自定义应用入口
 *
 * @author Hunter
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        // 图片加载框架初始化
        Fresco.initialize(this);
    }

}
