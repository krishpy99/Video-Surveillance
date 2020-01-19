package com.example.surveillance;

public class Blog {

    private  String title;
    private  String desc;
    private Integer lat;
    private Integer lon;
    private  String image;

    public Blog(String title, String desc, String image, Integer lat, Integer lon) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getlat() {
        return lat;
    }

    public Integer getlon(){
        return lon;
    }



    public Blog(){

    }

}
