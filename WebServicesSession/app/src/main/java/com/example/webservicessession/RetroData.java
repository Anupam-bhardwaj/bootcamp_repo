package com.example.webservicessession;

import com.google.gson.annotations.SerializedName;

public class RetroData {

    @SerializedName("name")
    private String name;

    @SerializedName("message")
    private String message;

    @SerializedName("profileImage")
    private String profileImage;

    public RetroData(String name, String message, String profileImage) {
        this.name = name;
        this.message = message;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
