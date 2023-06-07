package com.example.testgradle;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String no;
    private String name;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no);
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel source) {
        this.no = source.readString();
        this.name = source.readString();
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.no = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
