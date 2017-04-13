package com.example.qudqj_000.sixth_week_application;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qudqj_000 on 2017-04-06.
 */
//
//맛집 이름
//        -전화
//        -메뉴배열(메뉴3개)
//        -홈페이지
//        -등록일
//        -카테고리번호
public class Restaurants implements Parcelable {
    private String name;
    private int category;
    private String phoneNum;
    private String homepage;
    private String menu1;
    private String menu2;
    private String menu3;

    public Restaurants(){}


    public Restaurants(String name, int category, String phoneNum,  String menu1, String menu2, String menu3, String homepage){
        this.name = name;
        this.category = category;
        this.phoneNum = phoneNum;
        this.homepage = homepage;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
    }

    protected Restaurants(Parcel in) {
        name = in.readString();
        category = in.readInt();
        phoneNum = in.readString();
        homepage = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(category);
        dest.writeString(phoneNum);
        dest.writeString(homepage);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurants> CREATOR = new Creator<Restaurants>() {
        @Override
        public Restaurants createFromParcel(Parcel in) {
            return new Restaurants(in);
        }

        @Override
        public Restaurants[] newArray(int size) {
            return new Restaurants[size];
        }
    };

    public String getName(){
        return name;
    }


    public int getCategory() {
        return category;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getMenu1() {
        return menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public String getMenu3() {
        return menu3;
    }

}
