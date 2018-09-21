package com.glh.tjfx.presenter;

/**
 * 数据列表 fragment 网络请求业务接口
 */

public interface IDataPresenter {
    void queryContent(int start, int length, String selectTimeType, String coalunit, String wellhead, String coalLevel);
}
