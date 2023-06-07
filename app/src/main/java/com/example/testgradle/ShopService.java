package com.example.testgradle;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ShopService extends Service {
    private String TAG = "ShopService";
    private IShopProductCallback mCallback;

    Handler mHandler = new Handler();

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

        @Override
        public void registerCallback(IShopProductCallback callback) throws RemoteException {
            Log.i(TAG, "Server: registerCallback() this callback obj is a proxy!");
            mCallback = callback;

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Product product = new Product();
                    product.setNo("No.0001");
                    product.setName("banana");
                    try {
                        Log.i(TAG, "Server: the proxy obj invoke onProductChanged, pass the product to the client!");
                        mCallback.onProductChanged(product);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }, 20000);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}