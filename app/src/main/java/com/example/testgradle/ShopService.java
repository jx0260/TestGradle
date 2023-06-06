package com.example.testgradle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ShopService extends Service {
    private String TAG = "ShopService";

    public ShopService() {
    }

    private IShopAidlInterface.Stub mBinder = new IShopAidlInterface.Stub() {
        @Override
        public String getProductInfo(int productNo) throws RemoteException {
            Log.i(TAG, "SERVER: receive productNo: " + productNo);
            return "the productNo is: " + productNo + ", productName is LEGO!";
        }

        @Override
        public void buyProduct1(int productNo, String address) throws RemoteException {
            Log.i(TAG, "SERVER: receive productNo: " + productNo + ", address: " +address);
            Log.i(TAG, "YOU buy succeed!");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}