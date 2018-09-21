package com.glh.tjfx.ui.interfaces;

import com.glh.tjfx.base.IBaseView;
import com.glh.tjfx.bean.DataEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;

import java.util.Map;

/**
 * Created by Yang on 2017/9/29.
 */

public interface IDataView extends IBaseView {
    void setContent(DataEntity entity);
}
