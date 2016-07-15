package com.vinotech.vino;

import android.util.Log;

import com.vinotech.vino.cppwraper.IntegerRef;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public class VinoRenderImpl {

    private static VinoRenderImpl mVinoRender;

    public static void setGlobe(ViewResolution[] ress, ViewPerspective psp, StandardCameraPosition pos, int interactionmode) {
        //int _mode=mode;
        mVinoRender.setInteractionMode(interactionmode); //gVinoRender.setInteractionMode(_mode);
        mVinoRender.createInteractionMode(); //gVinoRender.createInteractionMode();
        int i;
        for(i = 0; i < 4; ++i)
        {
            ViewResolution tempRes = ress[i]; //jobject tmpRes = env->GetObjectArrayElement(ress, i);
            int tmpW, tmpH;
            IntegerRef wRef, hRef; //jViewResolution::getInstance(env).get(env, tmpRes, tmpW, tmpH);
            JavaViewResolutionImpl.getInstance().get(tempRes, wRef, hRef) //jViewResolution::getInstance(env).get(env, tmpRes, tmpW, tmpH);
            tmpW = wRef.getValue(); //jViewResolution::getInstance(env).get(env, tmpRes, tmpW, tmpH);
            tmpH = hRef.getValue(); //jViewResolution::getInstance(env).get(env, tmpRes, tmpW, tmpH);
            switch(i)
            {
                case 0:
                    Log.i("JNI Java Impl", String.format("show resolution is : %d*%d\n", tmpW, tmpH));
                    GlobalInfo::getInstance().setShowRes(tmpW, tmpH);
                    break;
                case 1:
                    LOGI("color resolution is : %d*%d\n", tmpW, tmpH);
                    GlobalInfo::getInstance().setColorRes(tmpW, tmpH);
                    break;
                case 2:
                    //���ͼ�ķֱ���
                    LOGI("depth resolution is : %d*%d\n", tmpW, tmpH);
                    GlobalInfo::getInstance().setDepthRes(tmpW, tmpH);
                    break;
                case 3:
                    LOGI("screen resolution is : %d*%d\n", tmpW, tmpH);
                    GlobalInfo::getInstance().setScreeResolution(tmpW, tmpH);
                    break;
            }
        }
        //�ӿ���Ϣ
        float fov, aspect, near, far;
        jViewPerspective::getInstance(env).get(env, perp, fov, aspect, near, far);
        GlobalInfo::getInstance().setPerspective(fov, aspect, near, far);
        //�ӵ��ʼ��Ϣ
        osg::Vec3f eye, center, up;
        jstdCamPos::getInstance(env).get(env, pos, eye, center, up);
        GlobalInfo::getInstance().setStdCamPos(eye, center, up);
    }

    public static void setData(DataPacket data) {

    }

    public static void onCreate(String[] srcs) {

    }

    public static void onInit() {

    }

    public static void onUpdate(MotionData md) {

    }

    public static void onFrame() {

    }

    public static void onProcess() {

    }

    public static void onDestory() {

    }

    public static void SetCamera(StandardCameraPosition pos) {

    }

    public static void getCamera(StandardCameraPosition cam) {

    }

    public static void setFrustrum(Frustrum fm, int projmode) {

    }
}
