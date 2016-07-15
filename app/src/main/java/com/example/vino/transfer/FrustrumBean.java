package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class FrustrumBean {

    private float left = -1;
    private float right = 1;
    private float up = 1;
    private float bottom = -1;
    private float near = 10;
    private float far = 10000;

    public FrustrumBean() {
    }

    public FrustrumBean(float left, float right, float up, float bottom, float near, float far) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.bottom = bottom;
        this.near = near;
        this.far = far;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getUp() {
        return up;
    }

    public void setUp(float up) {
        this.up = up;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getNear() {
        return near;
    }

    public void setNear(float near) {
        this.near = near;
    }

    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        this.far = far;
    }
}
