package com.meal_planner.jdbc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Meal {
    @JsonProperty
    private UUID id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private int calories;
    @JsonProperty
    private int servings;
    @JsonProperty
    private int prepTime;
    @JsonProperty
    private int cookTime;
    @JsonProperty
    private List<Ingredient> ingredients;
    @JsonProperty
    private List<RecipeInstruction> instructions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileImage = null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bannerImage = null;

    private Meal (UUID id, String name, String description, int calories, int servings, int prepTime, int cookTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.servings = servings;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeInstruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<RecipeInstruction> instructions) {
        this.instructions = instructions;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public static Meal create(UUID id,
                              String name,
                              String description,
                              int calories,
                              int servings,
                              int prepTime,
                              int cookTime) {
        return new Meal(id, name, description, calories, servings, prepTime, cookTime);
    }
}
