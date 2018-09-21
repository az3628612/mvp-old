package com.glh.tjfx.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Activity基类
 *
 * @author Hunter
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends RxAppCompatActivity implements IBaseView {
    private Toast toast;
    private ProgressDialog mProgressDialog;
    public T mPresenter;

    /**
     * 初始化控件
     */
    public abstract void initView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResId());
        initView();
        mPresenter = getPresenter();
        //关联view
        mPresenter.attachView((V) this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解关联view
        mPresenter.detachView();
    }

    //具体的presenter由子类返回
    protected abstract T getPresenter();

    protected abstract int getContentResId();

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void setProgressCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (mProgressDialog != null) {
            mProgressDialog.setOnCancelListener(onCancelListener);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            if (toast == null) {
                toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }

            toast.show();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bind() {
        return bindToLifecycle();
    }
}
