package com.glh.tjfx.bean.pie;

import java.io.Serializable;

/**
 * 饼图实体
 */

public class ItemStyleEntity implements Serializable {
    private ItemStyleEntity emphasis;

    public ItemStyleEntity getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(ItemStyleEntity emphasis) {
        this.emphasis = emphasis;
    }
}
