package com.example.testgradle;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String name;
    private int no;

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.no);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.no = source.readInt();
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.name = in.readString();
        this.no = in.readInt();
    }


}
