package com.example.webservicessession.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RetroData implements Serializable {

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

    @NonNull
    @Override
    public String toString() {
        return
                "PostsItem{" +
                "name = '" + name + '\'' +
                ",profileImage = '" + profileImage + '\'' +
                ",message = '" + message + '\'' +
                "}";
    }
}
