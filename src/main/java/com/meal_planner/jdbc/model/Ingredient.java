package com.meal_planner.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private float measureQuantity;
    @JsonProperty
    private String measureUnit;
    @JsonProperty
    private int count;
    @JsonProperty
    private String foodGroup;

    private Ingredient(int id, String name, float measureQuantity, String measureUnit, int count, String foodGroup) {
        this.id = id;
        this.name = name;
        this.measureQuantity = measureQuantity;
        this.measureUnit = measureUnit;
        this.count = count;
        this.foodGroup = foodGroup;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMeasureQuantity() {
        return measureQuantity;
    }

    public void setMeasureQuantity(float measureQuantity) {
        this.measureQuantity = measureQuantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public static Ingredient create(int id,
                                    String name,
                                    float measureQuantity,
                                    String measureUnit,
                                    int count,
                                    String foodGroup) {
        return new Ingredient(id, name, measureQuantity, measureUnit, count, foodGroup);
    }
}
