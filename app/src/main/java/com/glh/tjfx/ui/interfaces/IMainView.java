package com.glh.tjfx.ui.interfaces;

import com.glh.tjfx.base.IBaseView;
import com.glh.tjfx.bean.QueryConditionCoalEntity;
import com.glh.tjfx.bean.QueryConditionSiteEntity;
import com.glh.tjfx.bean.QueryConditionWellEntity;

import java.util.List;

/**
 * Created by Yang on 2017/10/24.
 */

public interface IMainView extends IBaseView {
    void setQueryConditionWell(List<QueryConditionWellEntity> wells);
    void setQueryConditionSite(List<QueryConditionSiteEntity> sites);
    void setQueryConditionCoal(List<QueryConditionCoalEntity> coals);
}
