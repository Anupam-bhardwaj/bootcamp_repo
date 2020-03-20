package com.example.webservicessession.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("posts")
    private List<RetroData> retroDataList;

    public List<RetroData> getRetroDataList() {
        return retroDataList;
    }

    public void setRetroDataList(List<RetroData> retroDataList) {
        this.retroDataList = retroDataList;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "Data{" +
                        "posts = '" + retroDataList + '\'' +
                        "}";
    }
}
