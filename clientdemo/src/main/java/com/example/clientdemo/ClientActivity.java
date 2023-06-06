package com.example.clientdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testgradle.IShopAidlInterface;

public class ClientActivity extends Activity {

    private String TAG = "ClientActivity";

    private IShopAidlInterface iShopAidlInterface;

    private Messenger mClientMessenger;

    private Handler mReceiveHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            String cont = data.getString("cont");
            Log.i(TAG, "Client received:" + cont);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent serviceIntent = new Intent("com.example.testgradle.ShopService");
        serviceIntent.setPackage("com.example.testgradle");
        bindService(serviceIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("ClientActivity", "onServiceConnected() service=" + service);
                iShopAidlInterface = IShopAidlInterface.Stub.asInterface(service);
                try {
                    iShopAidlInterface.getProductInfo(1221);
                    iShopAidlInterface.buyProduct1(9988, "大连市甘井子区");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

        Intent messengerIntent = new Intent("com.example.testgradle.MessengerService");
        messengerIntent.setPackage("com.example.testgradle");
        bindService(messengerIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mClientMessenger = new Messenger(service);
                sendMessageToServer();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

        findViewById(R.id.btn_getInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iShopAidlInterface.getProductInfo(1221);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iShopAidlInterface.buyProduct1(9988, "大连市西岗区");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_send_msg_to_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToServer();
            }
        });
    }

    private void sendMessageToServer() {
        Log.i(TAG, "send msg to server...(sky)");
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("obj", "sky");
        bundle.putInt("code", 1);
        msg.setData(bundle);

        Log.i(TAG, "pass the client's Messenger to Server!");
        msg.replyTo = new Messenger(mReceiveHandler);

        try {
            mClientMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
