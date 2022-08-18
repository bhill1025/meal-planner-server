package com.meal_planner.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MealDisplayCard {
    @JsonProperty
    private UUID mealId;
    @JsonProperty
    private String mealName;
    @JsonProperty
    private UUID creatorId;
    @JsonProperty
    private String creatorName;
    @JsonProperty
    private String createDate;
    @JsonProperty
    private String mealImageUrl;
    @JsonProperty
    private String mealBannerUrl;

    public MealDisplayCard(UUID mealId,
                           String mealName,
                           UUID creatorId,
                           String creatorName,
                           String createDate,
                           String mealImageUrl,
                           String mealBannerUrl) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.createDate = createDate;
        this.mealImageUrl = mealImageUrl;
        this.mealBannerUrl = mealBannerUrl;
    }

    public UUID getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMealImageUrl() {
        return mealImageUrl;
    }

    public void setMealImageUrl(String mealImageUrl) {
        this.mealImageUrl = mealImageUrl;
    }

    public String getMealBannerUrl() {
        return mealBannerUrl;
    }

    public void setMealBannerUrl(String mealBannerUrl) {
        this.mealBannerUrl = mealBannerUrl;
    }

    public static MealDisplayCard create(UUID mealId,
                                         String mealName,
                                         UUID creatorId,
                                         String creatorName,
                                         String createDate,
                                         String mealImageUrl,
                                         String mealBannerUrl) {
        return new MealDisplayCard(mealId, mealName, creatorId, creatorName, createDate, mealImageUrl, mealBannerUrl);
    }
}
