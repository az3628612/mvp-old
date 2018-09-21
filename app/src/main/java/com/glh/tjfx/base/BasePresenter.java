package com.glh.tjfx.base;




import com.glh.tjfx.app.ServiceManager;
import com.glh.tjfx.bean.DataEntity;
import com.glh.tjfx.exception.ApiException;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Presenter基类
 *
 * @author Hunter
 */
public abstract class BasePresenter<E> {
    /**
     * 内存不足时释放内存
     */
    protected WeakReference<E> mViewRef;

    public void attachView(E view) {
        mViewRef = new WeakReference<E>(view);
    }

    ;

    //用于在activity销毁时释放资源
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    ;

    protected E getView() {
        return mViewRef.get();
    }

    public BasePresenter() {
        initService();
    }

    protected abstract void initService();

    public <T> T getService(Class<T> clazz) {
        ServiceManager serviceManager = ServiceManager.getInstance();
        return serviceManager.getService(clazz);
    }

    public <T> void subscribe(IBaseView view, Observable<T> observable, Subscriber<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.<T>bind())
                .subscribe(subscriber);
    }
    public <T> void subscribeMap(final IBaseView view, Observable<JsonResponse<T>> observable, Subscriber<T> subscriber) {
        observable
                .map(new Func1<JsonResponse<T>, T>() {
                    @Override
                    public T call(JsonResponse<T> jsonresponse) {
                        if (jsonresponse == null) return null;

                        if (!jsonresponse.getStatus().equals("200") ){
                            throw new ApiException(jsonresponse);
                        }

                        return jsonresponse.getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.<T>bind())
                .subscribe(subscriber);
    }

    public void subscribeMap1(final IBaseView view, Observable<DataEntity> observable, Subscriber subscriber) {
        observable
                .map(new Func1<DataEntity, DataEntity>() {
                    @Override
                    public DataEntity call(DataEntity str) {
                        if (str == null) return null;

                        return str;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.bind())
                .subscribe(subscriber);
    }

}
