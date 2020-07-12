package com.e.ecospendbeerlist.Models;

public class Beer {

    //An item has a title, a tagline and image of the item(image_url).
    private String imageURL;
    private String title;
    private String tagLine;
    private String description;
    private String brewersTips;

    public Beer() {
        this.imageURL = "";
        this.title = "";
        this.tagLine = "";
        description ="";
        brewersTips = "";
    }



    public Beer(String imageURL, String title, String tagLine, String description, String brewersTips) {
        this.imageURL = imageURL;
        this.title = title;
        this.tagLine = tagLine;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }
}
