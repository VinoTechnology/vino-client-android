package com.example.vino;

import android.util.Log;

import com.example.vino.transferadapter.DataPacketModel;
import com.example.vino.transferadapter.TransferAdapter;

public class ReceiveThread implements Runnable {

    private TransferAdapter _adapter;
    private VinoSurfaceView _glView;

    private class BaseRunnable implements Runnable {
        public void run() {
            ReceiveThread.this._glView.getRender().setNewData();
        }
    }

    ReceiveThread(TransferAdapter adapter, VinoSurfaceView glView) {
        _adapter = adapter;
        _glView = glView;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            DataPacketModel rcData = _adapter.receiveOnePacket();

            long time1 = System.currentTimeMillis();

            VinoNativeRender.setData(rcData);

            long time2 = System.currentTimeMillis();

            Log.i("VINO", "Total decompression time:" + String.valueOf(time2 - time1) + "ms");

            _glView.queueEvent(new BaseRunnable());
            _glView.requestRender();
        }
    }

}

