package com.glh.tjfx.bean.pie;

import java.io.Serializable;

/**
 * 饼图实体
 */

public class EmphasisEntity implements Serializable {
    private int shadowBlur;
    private int shadowOffsetX;
    private String shadowColor;

    public int getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(int shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }
}
