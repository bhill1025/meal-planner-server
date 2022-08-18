package com.meal_planner.rest.handler;

import com.meal_planner.jdbc.dao.IngredientDao;
import com.meal_planner.jdbc.dao.MealDao;
import com.meal_planner.jdbc.dao.MealDisplayCardDao;
import com.meal_planner.jdbc.dao.RecipeInstructionDao;
import com.meal_planner.jdbc.model.Ingredient;
import com.meal_planner.jdbc.model.Meal;
import com.meal_planner.jdbc.model.MealDisplayCard;
import com.meal_planner.jdbc.model.RecipeInstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MealRequestHandler {

    private static final MealDao mealDao = new MealDao();
    private static final IngredientDao ingredientDao = new IngredientDao();
    private static final RecipeInstructionDao recipeInstructionDao = new RecipeInstructionDao();
    private static final MealDisplayCardDao mealDisplayCardDao = new MealDisplayCardDao();

    public static List<Meal> getAllMeals() {
        Optional<List<Meal>> optionalMeals = mealDao.get();
        if (optionalMeals.isEmpty()) {
           return new ArrayList<>();
        }
        List<Meal> mealList = optionalMeals.get();
        pairMealWithIngredients(mealList);
        pairMealWithInstructions(mealList);
        return mealList;
    }

    public static List<Meal> getMealById(String id) {
        Optional<Meal> optionalMeal = mealDao.get(id);
        if (optionalMeal.isEmpty()) {
            return null;
        }
        List<Meal> mealList = List.of(optionalMeal.get());
        pairMealWithIngredients(mealList);
        pairMealWithInstructions(mealList);
        pairMealWithImages(mealList);
        return mealList;
    }

    public static List<MealDisplayCard> getAllMealDisplays() {
        Optional<List<MealDisplayCard>> optionalMeals = mealDisplayCardDao.get();
        if (optionalMeals.isEmpty()) {
            return new ArrayList<>();
        }
        List<MealDisplayCard> mealCardList = optionalMeals.get();
        return mealCardList;
    }

    private static void pairMealWithIngredients(List<Meal> mealList) {
        for (Meal meal : mealList) {
            UUID mealId = meal.getId();
            Optional<List<Ingredient>> optionalIngredients = ingredientDao.getByMealId(mealId.toString());
            meal.setIngredients(optionalIngredients.orElseGet(ArrayList::new));
        }
    }

    private static void pairMealWithInstructions(List<Meal> mealList) {
        for (Meal meal : mealList) {
            UUID mealId = meal.getId();
            Optional<List<RecipeInstruction>> optionalInstructions = recipeInstructionDao.getByMealId(mealId.toString());
            meal.setInstructions(optionalInstructions.orElseGet(ArrayList::new));
        }
    }

    private static void pairMealWithImages(List<Meal> mealList) {
        for (Meal meal : mealList) {
            UUID mealId = meal.getId();
            Optional<List<MealDisplayCard>> optionalDisplayInfo = mealDisplayCardDao.getByMealId(mealId.toString());
            if (optionalDisplayInfo.isPresent() && optionalDisplayInfo.get().size() > 0) {
                MealDisplayCard displayCard = optionalDisplayInfo.get().get(0);
                meal.setProfileImage(displayCard.getMealImageUrl());
                meal.setBannerImage(displayCard.getMealBannerUrl());
            }
        }
    }
}
