package com.glh.tjfx.rx;

import android.content.DialogInterface;

import com.glh.tjfx.base.IBaseView;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * RxJava 自定义Subscriber
 *
 * @param <T>
 * @author Hunter
 */
public abstract class JsonResponseSubscriber<T> extends Subscriber<T> {
    private static final String TAG = "ResponseSubscriber";
    private IBaseView mBaseView;

    public JsonResponseSubscriber(IBaseView baseView) {
        mBaseView = baseView;
        mBaseView.setProgressCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                JsonResponseSubscriber.this.unsubscribe();
            }
        });

    }

    @Override
    public void onStart() {
        mBaseView.showProgress("加载中...");
    }

    @Override
    public void onCompleted() {
        mBaseView.hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.hideProgress();
        mBaseView.showToast(e.getMessage());
        Logger.e(TAG, e.getMessage());
    }
}
