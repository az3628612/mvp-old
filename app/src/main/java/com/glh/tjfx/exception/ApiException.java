package com.glh.tjfx.exception;


import com.glh.tjfx.base.JsonResponse;

/**
 * 自定义异常
 *
 * @author Hunter
 */
public class ApiException extends RuntimeException {
    private JsonResponse tJsonResponse;

    public ApiException(JsonResponse jsonResponse) {
        super(jsonResponse.getMessage());
    }

    public void setJsonResponse(JsonResponse jsonResponse) {
        tJsonResponse = jsonResponse;
    }

    public JsonResponse gettJsonResponse() {
        return tJsonResponse;
    }
}
