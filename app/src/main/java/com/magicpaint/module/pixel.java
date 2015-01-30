package com.magicpaint.module;

import java.io.Serializable;

/**
 * Created by apple on 15/1/27.
 */
public class pixel implements Serializable{
    public  static float pixelx=0;
    public  static float pixely=0;

    float x,y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
