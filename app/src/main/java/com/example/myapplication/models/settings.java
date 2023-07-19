package com.example.myapplication.models;


public class settings {
    String UpdatePackageName, img, title, message, adsType, nativeType;
    boolean suspended;


    public String getUpdatePackageName() {
        return UpdatePackageName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public String getAdsType() {
        return adsType;
    }

    public String getNativeType() {
        return nativeType;
    }

    public boolean isSuspended() {
        return suspended;
    }


}
