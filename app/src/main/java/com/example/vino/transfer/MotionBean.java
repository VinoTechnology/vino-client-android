package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class MotionBean {

    private byte _type;
    private float _x1 = 0.0f;
    private float _y1 = 0.0f;
    private float _x2 = 0.0f;
    private float _y2 = 0.0f;
    private static final int SIZE = 17;

    public MotionBean() {
    }

    public MotionBean(byte _type, float _x1, float _y1, float _x2, float _y2) {

        this._type = _type;
        this._x1 = _x1;
        this._y1 = _y1;
        this._x2 = _x2;
        this._y2 = _y2;
    }

    public byte get_type() {
        return _type;
    }

    public void set_type(byte _type) {
        this._type = _type;
    }

    public float get_x1() {
        return _x1;
    }

    public void set_x1(float _x1) {
        this._x1 = _x1;
    }

    public float get_y1() {
        return _y1;
    }

    public void set_y1(float _y1) {
        this._y1 = _y1;
    }

    public float get_x2() {
        return _x2;
    }

    public void set_x2(float _x2) {
        this._x2 = _x2;
    }

    public float get_y2() {
        return _y2;
    }

    public void set_y2(float _y2) {
        this._y2 = _y2;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
