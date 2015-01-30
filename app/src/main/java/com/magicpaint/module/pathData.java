package com.magicpaint.module;

import java.io.Serializable;

/**
 * Created by apple on 15/1/27.
 */
public class pathData implements Serializable{
    public final static int ACTION_DOWN=1;
    public final static int ACTION_MOVE=2;
    public final static int ACTION_UP=3;
    float preX;
    float preY;
    float x;
    float y;
    int action;

    public float getPreX() {
        return preX;
    }

    public void setPreX(float preX) {
        this.preX = preX;
    }

    public float getPreY() {
        return preY;
    }

    public void setPreY(float preY) {
        this.preY = preY;
    }

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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
