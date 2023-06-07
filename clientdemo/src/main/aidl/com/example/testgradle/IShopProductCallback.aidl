// IShopProductCallback.aidl
package com.example.testgradle;
import com.example.testgradle.Product;

// Declare any non-default types here with import statements

interface IShopProductCallback {
    oneway void onProductChanged(in Product product);
}