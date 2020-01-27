package com.najera.examplesapp;

public class IntroScreenItem {

    String title, description;
    int imgScreen;


    public IntroScreenItem(String title, String description, int imgScreen) {
        this.title = title;
        this.description = description;
        this.imgScreen = imgScreen;
    }

    //Getter and setter for screen items
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgScreen() {
        return imgScreen;
    }

    public void setImgScreen(int imgScreen) {
        this.imgScreen = imgScreen;
    }
}
