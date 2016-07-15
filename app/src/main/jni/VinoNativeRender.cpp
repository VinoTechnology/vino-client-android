/*
*���ش�����Java��Ľӿ���
*������ϵͳ�Զ����ɵ�ͷ�ļ�com_example_vino_VinoNativeRender.h�нӿ�ʵ��
*������ݣ�2013��6��
*���ߣ�Jing
*/

#include "com_example_vino_VinoNativeRender.h"
#include <map>
#include "VinoRender.h"
#include "classUtility.h"

VinoRender gVinoRender;

//��Java���л�ȡȫ�ֲ������洢�����ش���
void Java_com_example_vino_VinoNativeRender_setGlobe
	(JNIEnv *env, jclass ths, jobjectArray ress, jobject perp, jobject pos, jint mode)
{
	int _mode=mode;
	gVinoRender.setInteractionMode(_mode);
	gVinoRender.createInteractionMode();
	int i;
	for(i = 0; i < 4; ++i)
	{
		jobject tmpRes = env->GetObjectArrayElement(ress, i);
		int tmpW, tmpH;
		jViewResolution::getInstance(env).get(env, tmpRes, tmpW, tmpH);
		switch(i)
		{
		case 0:
			//ϵͳ�߼��ϵ���ʾ�ֱ���
			LOGI("show resolution is : %d*%d\n", tmpW, tmpH);
			GlobalInfo::getInstance().setShowRes(tmpW, tmpH);
			break;
		case 1:
			//�ο�֡����ʾ�ֱ��ʣ��տ�ʼ�������ϲ��������������ͼ�ֱ��ʷֿ����ã�
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

void Java_com_example_vino_VinoNativeRender_SetCamera
  (JNIEnv * env, jclass jc, jobject pos)
{
	if(gVinoRender._ic.mode==jInteraction::USER_DEFINE)
	{
		LOGI("setLookAt : \n");
		osg::Vec3f eye, center, up;
		jstdCamPos::getInstance(env).get(env, pos, eye, center, up);
		gVinoRender.setLookAt(eye,center,up);
	}
}


void Java_com_example_vino_VinoNativeRender_getCamera
  (JNIEnv * env, jclass js, jobject jo)
{
	if(gVinoRender._ic.mode==jInteraction::USER_DEFINE)
	{
		LOGI("getLookAt : \n");
		osg::Vec3f eye, center, up;
		gVinoRender.getCamera(eye,center, up);
		jstdCamPos::getInstance(env).set(env,jo,eye,center,up);
	}

}
void Java_com_example_vino_VinoNativeRender_setFrustrum
  (JNIEnv * env, jclass js, jobject jo, jint ji)
{
	LOGI("setFrustrum : \n");
	gVinoRender.setProjectionMode(ji);
	float left, right, up, bottom, near,far;

	jFrustrum::getInstance(env).get(env,jo,left,right,up,bottom,near,far);
	GlobalInfo::getInstance().setFrustrum(left,right,up,bottom,near,far);

	LOGI("setFrustrum : %f %f %f %f %f %f\n",left,right,up,bottom,near,far);

}
/*jobject Java_com_example_vino_VinoNativeRender_getCamera
  (JNIEnv *, jclass pos)
{
		LOGI("getLookAt : \n");
		osg::Vec3f eye, center, up;
		gVinoRender.getCamera(eye,center, up);

}*/

//ÿ��java����յ��ο�֡����ʱ�����͵�NDK����н�ѹ����
void Java_com_example_vino_VinoNativeRender_setData
	(JNIEnv *env, jclass ths, jobject pack)
{
	jfloatArray matrix;
	jDataPacket::getInstance(env).getMatrixData(env, pack, matrix);

	jbyteArray color;
	jDataPacket::getInstance(env).getColorData(env, pack, color);

	jbyteArray depth;
	jint dstLen;
	jDataPacket::getInstance(env).getDepthData(env, pack, depth, dstLen);

	//����ع�����Ⱦ�߳��У�������Ҫ������
	pthread_mutex_t mutex = GlobalInfo::getInstance().getDepthImageBase().getMutex();

	pthread_mutex_lock(&mutex);
	//��ȡ�ο�֡��Ӧ���ӵ����
	jfloat* tmpMatrix = env->GetFloatArrayElements(matrix, 0);
	if(tmpMatrix != NULL)
	{
		GlobalInfo::getInstance().getDepthImageBase().setMatrix(tmpMatrix);

		env->ReleaseFloatArrayElements(matrix, tmpMatrix, 0);
	}

	//��ȡ�ο�֡����ɫͼ�������

	//ͳ����ɫͼ���ѹʱ��
	double time1 = VinoRender::getCurrentTime();

	unsigned long srcColorSize = (unsigned long)env->GetArrayLength(color);
	jbyte * srcColorData =(jbyte *) env->GetByteArrayElements(color, NULL);
	if(srcColorData != NULL)
	{
		unsigned char* ucSrcColorData = (unsigned char*)srcColorData;
		GlobalInfo::getInstance().getDepthImageBase().setColorData(ucSrcColorData, srcColorSize);

		env->ReleaseByteArrayElements(color, srcColorData, 0);
	}
	double time2 = VinoRender::getCurrentTime();

	//LOGI("JPEG process time is %f", time2 - time1);

	//��ȡ�ο�֡�����ͼ�����ݣ��߲��������ʽ��
	jbyte *srcDepthData = env->GetByteArrayElements(depth, NULL);
	if(srcDepthData != NULL)
	{
		char* cSrcDepthData = (char*)srcDepthData;

		GlobalInfo::getInstance().getDepthImageBase().setDepthData(cSrcDepthData, dstLen);

		env->ReleaseByteArrayElements(depth, srcDepthData, 0);
	}
	
	double time3 = VinoRender::getCurrentTime();
	//LOGI("DEPTH process time is %f", time3 - time2);


	pthread_mutex_unlock(&mutex);
}

//�ڴ������ػ���ʱ����shader�ļ�����
//�ļ��ɶԳ��֣�����vertex shader ��  fragment shader
//shader��һ��Ϊע��Ϊ���ܽ��ܣ��ں����ļ���������Ϊ��ͬshader�ı�ʾ������mapӳ��
//��://smooth��˵����shader�Ĺ����ǽ���ƽ������
void Java_com_example_vino_VinoNativeRender_onCreate
	(JNIEnv *env, jclass ths, jobjectArray srcs)
{
	jMotionData::getInstance(env);

	int srcCnt = env->GetArrayLength(srcs) / 2;
	gVinoRender.createProgramSize(srcCnt);
	for(int i = 0; i < srcCnt; ++i)
	{
		//һ��shader������ڣ���дshaderʱ��Ҫ��ȷָ��
		jstring vert = (jstring) env->GetObjectArrayElement(srcs, 2*i);
		jstring frag = (jstring) env->GetObjectArrayElement(srcs, 2*i + 1);
		
		//vertex shader(������ɫ��)
		const int vLen = env->GetStringLength(vert);
		const char* vSrc = env->GetStringUTFChars(vert, 0);

		//fragment shader��ƬԪ��ɫ����
		const int fLen = env->GetStringLength(frag);
		const char* fSrc = env->GetStringUTFChars(frag, 0);
		gVinoRender.insertProgramSrc(i, vSrc, fSrc, vLen, fLen);

		env->ReleaseStringUTFChars(frag, fSrc);
		env->ReleaseStringUTFChars(vert, vSrc);
	}
}


//��ʼ������Render��������OpenGL ES 2.0������
//����ȫ�ֱ���gVinoRender�Ľӿ�
void Java_com_example_vino_VinoNativeRender_onInit
	(JNIEnv *env, jclass ths)
{
	gVinoRender.initRender();
}

//onUpdate�ӿ�ʵʱ����Java�����û�������ԭʼ����
//MotionDataΪ���������в�������Ļ����
//һ����ͨ���˽ӿڴ������ش��봦����һ�����Ը�������ʽ�����������ͬ��
void Java_com_example_vino_VinoNativeRender_onUpdate
	(JNIEnv *env, jclass ths, jobject md)
{
	MotionData tmp;
	tmp._type  = jMotionData::getInstance(env).getType(env, md);
	jMotionData::getInstance(env).getPoint1(env, md, tmp._x1, tmp._y1);
	jMotionData::getInstance(env).getPoint2(env, md, tmp._x2, tmp._y2);
	gVinoRender.update(tmp);
};


//������ʾ���ƣ�����֡�ο���3D Image Warping�ϳɽ��
void Java_com_example_vino_VinoNativeRender_onFrame
	(JNIEnv *env, jclass ths)
{
	double stt = VinoRender::getCurrentTime();
	gVinoRender.frame();
	double fsh = VinoRender::getCurrentTime();
	LOGI("Frame time is %f", fsh - stt);
}

//OpenGL ES 2.0�������ṩ����ع��Ľӿ�
//�˽ӿڹ�DepthImageBase����
void Java_com_example_vino_VinoNativeRender_onProcess
	(JNIEnv * env, jclass ths)
{
	

	if(GlobalInfo::getInstance().getDepthImageBase().isWithData())
	{
		double time1 = VinoRender::getCurrentTime();

		pthread_mutex_t mutex = GlobalInfo::getInstance().getDepthImageBase().getMutex();
		pthread_mutex_lock(&mutex);

		//����ɫͼ������OpenGL ES 2.0������ֱ�Ӵ�Ϊ����
		LOGI("process1~");
		unsigned char* color = GlobalInfo::getInstance().getDepthImageBase().getColorData();
		gVinoRender.setColorData(color);
		//�ο�֡�ӵ㣬֮�������shader
		LOGI("process2~");
		float* matrix = GlobalInfo::getInstance().getDepthImageBase().getMatixData();
		gVinoRender.setMatrixData(matrix);
		//���ͼ����
		LOGI("process3~");
		float* sample;
		int cnt;
		GlobalInfo::getInstance().getDepthImageBase().getDepthData(sample, cnt);
		gVinoRender.setDepthData(sample, cnt);

		//�������ݺ󣬸���DepthImageBase״̬��������
		LOGI("process4~");
		GlobalInfo::getInstance().getDepthImageBase().withoutData();

		pthread_mutex_unlock(&mutex);

		double time2 = VinoRender::getCurrentTime();

		LOGI("process time is %f", time2 - time1);
	}
	

	//Java_com_example_vino_VinoNativeRender_onFrame(env, ths);

	//double time3 = VinoRender::getCurrentTime();

	//LOGI("process duration is %f",time3 - time2);
}

//��onInit��Ӧ������OpenGL ES 2.0����
void Java_com_example_vino_VinoNativeRender_onDestory
	(JNIEnv *env, jclass ths)
{
	gVinoRender.destroyRender();
}
