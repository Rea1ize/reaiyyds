package com.hsae.customview;

public class Fruit {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private String name;
    private int imageId;

    public Fruit(int id, String name, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }
}
