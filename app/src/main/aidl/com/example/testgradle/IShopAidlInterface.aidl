// IShopAidlInterface.aidl
package com.example.testgradle;

// Declare any non-default types here with import statements

interface IShopAidlInterface {

    String getProductInfo(int productNo);

    void buyProduct1(int productNo, String address);

}