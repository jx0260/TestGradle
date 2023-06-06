package com.example.testgradle;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {

    private String TAG = "MessengerService";

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            if (data.getInt("code") == 1) {
                String objStr = data.getString("obj");
                Log.i(TAG, "Server: receive " + objStr + " - is blue!");


                Messenger clientMsger = msg.replyTo;
                if (clientMsger != null) {
                    Message receiptMsg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("cont", "I'm server, I received: sky is blue.");
                    receiptMsg.setData(bundle);

                    try {
                        Log.i(TAG, "Server send back received msg.");
                        clientMsger.send(receiptMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
