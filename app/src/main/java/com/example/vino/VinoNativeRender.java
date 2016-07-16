package com.example.vino;

import com.example.vino.transfer.FrustrumModel;
import com.example.vino.transfer.InteractionType;
import com.example.vino.transferadapter.DataPacketModel;
import com.example.vino.vinoglobal.MotionData;
import com.example.vino.vinoglobal.StandardCameraPosition;
import com.example.vino.vinoglobal.ViewPerspective;
import com.example.vino.vinoglobal.ViewResolution;

public class VinoNativeRender {

    static {
        System.loadLibrary("nativeRender");
    }

    public native static void setGlobe(ViewResolution[] ress, ViewPerspective psp, StandardCameraPosition pos, InteractionType interactionmode);

    public native static void setData(DataPacketModel data);

    public native static void onCreate(String[] srcs);

    public native static void onInit();

    public native static void onUpdate(MotionData md);

    public native static void onFrame();

    public native static void onProcess();

    public native static void onDestory();

    public native static void SetCamera(StandardCameraPosition pos);//����Camera

    public native static void getCamera(StandardCameraPosition cam);   ///��ȡCamera

    public native static void setFrustrum(FrustrumModel fm, int projmode);//���ü��ÿռ�
}
