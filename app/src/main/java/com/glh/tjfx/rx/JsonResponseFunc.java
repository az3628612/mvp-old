package com.glh.tjfx.rx;




import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.exception.ApiException;

import rx.functions.Func1;

/**
 * RxJava map转换
 *
 * @param <T>
 * @author Hunter
 */
public class JsonResponseFunc<T> implements Func1<JsonResponse<T>, T> {
    @Override
    public T call(JsonResponse<T> tJsonResponse) {
        if (tJsonResponse == null) return null;

        if (tJsonResponse.getStatus() .equals("200") ) {
            throw new ApiException(tJsonResponse);
        }

        return tJsonResponse.getData();
    }
}
