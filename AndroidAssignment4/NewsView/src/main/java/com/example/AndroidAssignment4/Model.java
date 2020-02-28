package com.example.AndroidAssignment4;


public class Model {

    public static final int BANNER_TYPE=0;
    public static final int SMALL_BANNER_TYPE=1;

    public int type;
    private int image;
    public String title;
    public String desc;

    public Model(int type, int image, String title, String desc) {
        this.type = type;
        this.setImage(image);
        this.title = title;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
