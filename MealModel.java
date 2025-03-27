
package com.example.meal;

public class MealModel {
    private String name;
    private int imageResId;

    public MealModel(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
