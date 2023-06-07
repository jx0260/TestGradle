// IShopAidlInterface.aidl
package com.example.testgradle;
import com.example.testgradle.IShopProductCallback;

// Declare any non-default types here with import statements

interface IShopAidlInterface {

    String getProductInfo(int productNo);

    void buyProduct1(int productNo, String address);

    oneway void registerCallback(in IShopProductCallback callback);

}