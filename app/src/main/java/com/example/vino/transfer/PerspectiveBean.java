package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class PerspectiveBean {

    private float _fov;
    private float _aspect;
    private float _near;
    private float _far;
    private int SIZE = 16;

    public PerspectiveBean() {
    }

    public PerspectiveBean(float _fov, float _aspect, float _near, float _far, int SIZE) {
        this._fov = _fov;
        this._aspect = _aspect;
        this._near = _near;
        this._far = _far;
        this.SIZE = SIZE;
    }

    public float get_fov() {
        return _fov;
    }

    public void set_fov(float _fov) {
        this._fov = _fov;
    }

    public float get_aspect() {
        return _aspect;
    }

    public void set_aspect(float _aspect) {
        this._aspect = _aspect;
    }

    public float get_near() {
        return _near;
    }

    public void set_near(float _near) {
        this._near = _near;
    }

    public float get_far() {
        return _far;
    }

    public void set_far(float _far) {
        this._far = _far;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }
}
