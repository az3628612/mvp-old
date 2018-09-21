package com.glh.tjfx.ui.interfaces;

import com.glh.tjfx.base.IBaseView;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;

/**
 * Created by Yang on 2017/9/29.
 */

public interface IWellView extends IBaseView {
    void setSameContent(CurrentLineEntity entity, String type);

    void setLineContent(CurrentLineEntity entity, String type);

    void setPieContent(CurrentPieEntity entity, String type);
}
