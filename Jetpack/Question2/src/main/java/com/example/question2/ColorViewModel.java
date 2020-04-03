package com.example.question2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class ColorViewModel extends ViewModel {

    public int color;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor(){
        return color;
    }

}
