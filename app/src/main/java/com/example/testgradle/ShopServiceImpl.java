package com.example.testgradle;

import android.os.IBinder;
import android.os.RemoteException;

public class ShopServiceImpl implements IShopAidlInterface{
    @Override
    public String getProductInfo(int productNo) throws RemoteException {
        return null;
    }

    @Override
    public void buyProduct1(int productNo, String address) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
