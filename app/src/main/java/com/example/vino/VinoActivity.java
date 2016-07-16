package com.example.vino;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vino.nodeobject.ObjectGroup;
import com.example.vino.transfer.ApplicationType;
import com.example.vino.transfer.InteractionType;
import com.example.vino.transfer.MessageType;
import com.example.vino.transferadapter.TransferAdapter;
import com.example.vino.vinoglobal.StandardCameraPosition;
import com.example.vino.vinoglobal.ViewPerspective;
import com.example.vino.vinoglobal.ViewResolution;
import com.example.vinok.R;

import java.io.IOException;

public class VinoActivity extends Activity implements OnClickListener {

    //public static final String ADDRESS= "219.224.168.68";//68
    //public static final String ADDRESS= "192.168.1.100";//68
    public static final String ADDRESS = "219.224.168.69";//2015_4_1
    public static final int PORT = 5588;

    //public static final int _interactionmode=InteractionType.USER_DEFINE;
    public static final InteractionType _interactionmode = InteractionType.TRACK_BALL_MANIPULATOR;
    //public static final int _interactionmode=InteractionType.FIRSTPERSONMANIPULATOR;

    //public static final String _sceneName="armadillo";   //"paris"  "engine"  "cessna" "island"
    public static final String _sceneName = "engine";
    //public static final String _sceneName="paris";
//	public static final String _sceneName="island";
    //public static final String _sceneName="campus";

    //2014_7_28
    //public static final int ApplicationID=ApplicationType.DEFAULTVALUE;//Ĭ��ֵ
    public static final ApplicationType ApplicationID = ApplicationType.ENGINE_SHOW;//��������ά�ṹչʾӦ��
    public static final boolean isAppmode = false;//�Ƿ�ᷢ��APPLICATIONMS���͵���Ϣ��

    private TransferAdapter _adapter = null;
    private LocalInfoCapturer _capturer = null;
    private VinoSurfaceView _glView = null;
    private Handler _handler = null;

    private ObjectGroup ng = null;
    private LinearLayout mLinearLayout = null;//2014_8_20

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNodeObject();
        _adapter = new TransferAdapter();

        _capturer = new LocalInfoCapturer();
        //N4 768,1280
        //_capturer.setScreenRes(768,1080);
        _capturer.setScreenRes(1200, 1600);
        //_capturer.setDispalyRes(1200, 1600);
        //_capturer.setDispalyRes(600, 800);
        //_capturer.setDispalyRes(768, 1280);
        _capturer.setDispalyRes(600, 800);
        //_capturer.setDispalyRes(200, 200);
        /*
        巴黎
		city
        */
//        _capturer.setPerspective(29.1484f, 0.75f, 10, 20000);
//
//		_capturer.setUpsFactor(1.0f, 1.0f);

        //island

        //_capturer.setPerspective(29.1484f, 0.75f, 10, 2000);

        //_capturer.setUpsFactor(1.0f, 1.0f);

        //campus

        //_capturer.setPerspective(29.1484f, 0.75f, 10, 2000);

        //_capturer.setUpsFactor(1.0f, 1.0f);
		
		/*
		飞机
		air
		*/

        //_capturer.setPerspective(30f, 0.75f, 10, 200f);
        //_capturer.setUpsFactor(1.0f, 1.0f);

		/*
		arm
		 */

        //_capturer.setPerspective(30f, 0.75f, 10, 5000f);
        // _capturer.setUpsFactor(1.0f, 1.0f);
        // _capturer.setPerspective(45.0f, 0.75f, 50.0f, 500.0f);
        //_capturer.setUpsFactor(1.0f, 1.0f);

        //paris  campus
        _capturer.setPerspective(29.1484f, 0.75f, 10f, 20000f);
        _capturer.setUpsFactor(1.0f, 1.0f);

        //engine
        _capturer.setPerspective(29.1484f, 0.75f, 10000f, 100000f);

        _capturer.setUpsFactor(1.0f, 1.0f);

        _handler = new Handler();

        ViewResolution[] ress = new ViewResolution[4];
        ress[0] = _capturer.getResolution();
        ress[1] = _capturer.getRecvColorRes();
        ress[2] = _capturer.getRecvDepthRes();
        ress[3] = _capturer.getScreenRes();
        ViewPerspective psp = _capturer.getPerpective();
        StandardCameraPosition pos = _capturer.getCameraPos();

        VinoNativeRender.setGlobe(ress, psp, pos, _interactionmode);
        //
        //Frustrum mf=new Frustrum();
        //VinoNativeRender.setFrustrum(mf, 0);
        //
        Log.i("VINO", ".........................");
        Thread mConnect = new Thread(new ConnectThread(this));
        mConnect.start();
        Log.i("VINO", "~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // getSocket
        // setContentView(R.layout.activity_vino);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (_glView != null) _glView.onResume();
    }

    protected void onDestroy() {
        if (_adapter._isConnected) {
            _adapter.sendOnePacket(MessageType.CONTROL_STOP_MSG, 8);
        }
        super.onDestroy();
        Log.i("Destroy:", "Destroy the activity!!!!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (_glView != null) _glView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vino, menu);
        return true;
    }

    public void setupVinoView() {
        Log.i("VINO", "setVinoView");
        _glView = new VinoSurfaceView(this, _adapter);
        _glView.getRender().setNewData();

        if (this.ApplicationID == ApplicationType.ENGINE_SHOW) {
            UiButton(_glView);
        } else {
            setContentView(_glView);
        }
    }

    public TransferAdapter getAdapter() {
        return _adapter;
    }

    public LocalInfoCapturer getCapturer() {
        return _capturer;
    }

    public Handler getHandler() {
        return _handler;
    }

    public void initNodeObject() {
        //if(this.ApplicationID== ApplicationType.ENGINESHOW)
        //{
        try {

            ng = new ObjectGroup(this.getAssets().open("config.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Log.i("VINO_NODE_SIZE", ng.getNodeObjectSize()+"");

        //}
    }

    public void UiButton(VinoSurfaceView _glView) {
        //view=new View();
        // ����LinearLayout����
        mLinearLayout = new LinearLayout(this);

        // ����������ʽ��͸ߣ���Ӧxml�����У�
        // android:layout_width="fill_parent"
        // android:layout_height="fill_parent"
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        // ���÷��򣬶�Ӧxml�����У�
        // android:orientation="vertical"
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout mLinearLayoutD = new LinearLayout(this);
        mLinearLayoutD.setOrientation(LinearLayout.HORIZONTAL);

        mLinearLayoutD.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));


        Button mButtonl = new Button(this);
        mButtonl.setText("Prev");
        mButtonl.setId(0);
        mButtonl.setOnClickListener(this);
        //mButtonl.getLayoutParams().width=100;
        //LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mButtonl.getLayoutParams();
        //linearParams.width=10;
        //mButtonl.setLayoutParams(linearParams);
        Button mButtonr = new Button(this);
        mButtonr.setText("Next");
        mButtonr.setId(1);
        //mButtonr.getLayoutParams().width=100;
        mButtonr.setOnClickListener(this);

        LinearLayout.LayoutParams mLayoutParamsbutton = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);


        //LinearLayout.LayoutParams mLayoutParamsButton=new LinearLayout.LayoutParams(LayoutParams.);
        mLinearLayoutD.addView(mButtonl, mLayoutParamsbutton);
        mLinearLayoutD.addView(mButtonr, mLayoutParamsbutton);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

        mLinearLayout.addView(mLinearLayoutD, mLayoutParams);


        // ����TextView����
        //TextView mTextView = new TextView(this);
        // ��������

        //N4 950
        //_glView.setText("hello world");
        _glView.setMinimumHeight(1000);
        // Ϊ�佨��������ʽ����Ӧxml�����У�
        // android:layout_width="fill_parent"
        // android:layout_height="wrap_content"

        //LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
        //LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

        // �ڸ��಼��������������ʽ
        mLinearLayout.addView(_glView, mLayoutParams);
        //setContentView(R.layout.activity_ui);
        setContentView(mLinearLayout);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == 0) {
            if (ng != null)
                ng.prevStep();

            if (_glView != null) {
                _glView.requestRender();
            }
            Log.i("vino", "press left");
        } else if (v.getId() == 1) {
            if (ng != null)
                ng.nextStep();
            Log.i("vino", "press right");
            if (_glView != null) {
                _glView.requestRender();
            }

        }
    }
}  
