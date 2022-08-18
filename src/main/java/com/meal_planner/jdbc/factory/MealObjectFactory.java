package com.meal_planner.jdbc.factory;

import com.meal_planner.jdbc.model.Ingredient;
import com.meal_planner.jdbc.model.Meal;
import com.meal_planner.jdbc.model.MealDisplayCard;
import com.meal_planner.jdbc.model.RecipeInstruction;
import com.meal_planner.util.DateTimeFormatUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MealObjectFactory {

    public static List<Meal> buildMeals(ResultSet resultSet) {
        List<Meal> meals = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Meal meal;
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int calories = resultSet.getInt("calories");
                int servings = resultSet.getInt("servings");
                int prepTime = resultSet.getInt("prep_time");
                int cookTime = resultSet.getInt("cook_time");
                meal = Meal.create(id, name, description, calories, servings, prepTime, cookTime);
                meals.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return meals;
    }

    public static List<Ingredient> buildIngredients(ResultSet resultSet) {
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Ingredient ingredient;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float quantity = resultSet.getFloat("quantity");
                String unit = resultSet.getString("unit");
                int count = resultSet.getInt("count");
                String foodGroup = resultSet.getString("food_group");
                ingredient = Ingredient.create(id, name, quantity, unit, count, foodGroup);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            return null;
        }
        return ingredients;
    }

    public static List<RecipeInstruction> buildRecipeInstructions(ResultSet resultSet) {
        List<RecipeInstruction> instructions = new ArrayList<>();
        try {
            while (resultSet.next()) {
                RecipeInstruction instruction;
                int sequence = resultSet.getInt("sequence");
                String description = resultSet.getString("description");
                instruction = RecipeInstruction.create(sequence, description);
                instructions.add(instruction);
            }
        } catch (SQLException e) {
            return null;
        }
        return instructions;
    }

    public static  List<MealDisplayCard> buildMealDisplayCards(ResultSet resultSet) {
        List<MealDisplayCard> displayCards = new ArrayList<>();
        try {
            while (resultSet.next()) {
                MealDisplayCard card;
                UUID mealId = UUID.fromString(resultSet.getString("meal_id"));
                String mealName = resultSet.getString("meal_name");
                UUID creatorId = UUID.fromString(resultSet.getString("creator_id"));
                String creatorName = resultSet.getString("creator_name");
                Timestamp createTimestamp = resultSet.getTimestamp("create_date");
                String createDate = DateTimeFormatUtil.timestampToIsoFormat(createTimestamp);
                String mealImageUrl = resultSet.getString("profile_pic");
                String mealBannerUrl = resultSet.getString("banner_pic");
                card = MealDisplayCard.create(mealId, mealName, creatorId,
                        creatorName, createDate, mealImageUrl, mealBannerUrl);
                displayCards.add(card);
            }
        } catch (SQLException e) {
            return null;
        }
        return displayCards;
    }
}
