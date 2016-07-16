package com.example.vino.cppimpl;

import com.example.vino.Vector3;
import com.example.vino.transfer.FrustrumModel;
import com.example.vino.vinoglobal.StandardCameraPosition;
import com.example.vino.vinoglobal.ViewPerspective;
import com.example.vino.vinoglobal.ViewResolution;

public class GlobalInfo {

    private static GlobalInfo instance;

    private ViewResolution mShowRes;
    private ViewResolution mScreen;
    private ViewResolution mColorRes;
    private ViewResolution mDepthRes;
    private ViewPerspective mPersp;
    private StandardCameraPosition mCamPos;
    private DepthImageBase mBase;
    private FrustrumModel mf;

    public static GlobalInfo getInstance() {
        if (instance == null) {
            instance = new GlobalInfo();
        }
        return instance;
    }

    private GlobalInfo() {

    }

    public void setScreeResolution(int width, int height) {
        mScreen._width = width;
        mScreen._height = height;

    }

    public ViewResolution getScreenResolution() {
        return mScreen;
    }

    public void setShowRes(int width, int height) {
        mShowRes._width = width;
        mShowRes._height = height;
    }

    public void setColorRes(int width, int height) {
        mColorRes._width = width;
        mColorRes._height = height;
    }

    public void setDepthRes(int width, int height) {
        mDepthRes._width = width;
        mDepthRes._height = height;
    }

    public void setPerspective(float fov, float aspect, float near, float far) {
        mPersp._fov = fov;
        mPersp._aspect = aspect;
        mPersp._near = near;
        mPersp._far = far;
    }

    public void setFrustrum(float left, float right, float up, float bottom, float near, float far) {
        mf.left = left;
        mf.right = right;

        mf.up = up;
        mf.bottom = bottom;

        mf.near = near;
        mf.far = far;
    }

    public void setStdCamPos(Vector3 eye, Vector3 center, Vector3 up) {
        mCamPos._eye = eye;
        mCamPos._center = center;
        mCamPos._up = up;
    }

    public DepthImageBase getDepthImageBase() {
        return mBase;
    }

    public ViewResolution getShowRes() {
        return mShowRes;
    }

    public ViewResolution getColorRes() {
        return mColorRes;
    }

    public ViewResolution getDepthRes() {
        return mDepthRes;
    }

    public ViewPerspective getPerspective() {
        return mPersp;
    }

    public FrustrumModel getFrustrum() {
        return mf;
    }

    public StandardCameraPosition getStdCamPos() {
        return mCamPos;
    }
}
