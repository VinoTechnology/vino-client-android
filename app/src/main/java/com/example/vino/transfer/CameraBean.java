package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class CameraBean {

    private float _eyex;
    private float _eyey;
    private float _eyez;
    private float _centerx;
    private float _centery;
    private float _centerz;
    private float _upx;
    private float _upy;
    private float _upz;
    private int SIZE = 36;

    public CameraBean() {
    }

    public CameraBean(float _eyex, float _eyey, float _eyez, float _centerx, float _centery, float _centerz, float _upx, float _upy, float _upz, int SIZE) {
        this._eyex = _eyex;
        this._eyey = _eyey;
        this._eyez = _eyez;
        this._centerx = _centerx;
        this._centery = _centery;
        this._centerz = _centerz;
        this._upx = _upx;
        this._upy = _upy;
        this._upz = _upz;
        this.SIZE = SIZE;
    }

    public float get_eyex() {
        return _eyex;
    }

    public void set_eyex(float _eyex) {
        this._eyex = _eyex;
    }

    public float get_eyey() {
        return _eyey;
    }

    public void set_eyey(float _eyey) {
        this._eyey = _eyey;
    }

    public float get_eyez() {
        return _eyez;
    }

    public void set_eyez(float _eyez) {
        this._eyez = _eyez;
    }

    public float get_centerx() {
        return _centerx;
    }

    public void set_centerx(float _centerx) {
        this._centerx = _centerx;
    }

    public float get_centery() {
        return _centery;
    }

    public void set_centery(float _centery) {
        this._centery = _centery;
    }

    public float get_centerz() {
        return _centerz;
    }

    public void set_centerz(float _centerz) {
        this._centerz = _centerz;
    }

    public float get_upx() {
        return _upx;
    }

    public void set_upx(float _upx) {
        this._upx = _upx;
    }

    public float get_upy() {
        return _upy;
    }

    public void set_upy(float _upy) {
        this._upy = _upy;
    }

    public float get_upz() {
        return _upz;
    }

    public void set_upz(float _upz) {
        this._upz = _upz;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }
}
