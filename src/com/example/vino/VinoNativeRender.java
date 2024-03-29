package com.example.vino;

public class VinoNativeRender {
	
	static{
		System.loadLibrary("nativeRender");
	}
	
	public native static void setGlobe(ViewResolution[] ress, ViewPerspective psp, stdCamPos pos, int interactionmode);
	
	public native static void setData(DataPacket data);
	
	public native static void onCreate(String[] srcs);
	
	public native static void onInit();
	
	public native static void onUpdate(MotionData md);
	
	public native static void onFrame();
	
	public native static void onProcess();
	
	public native static void onDestory();
	
	public native static void SetCamera(stdCamPos pos);//设置Camera
	
	public native static void getCamera(stdCamPos cam);   ///获取Camera
	
	public native static void setFrustrum(Frustrum fm, int projmode);//设置剪裁空间
}
