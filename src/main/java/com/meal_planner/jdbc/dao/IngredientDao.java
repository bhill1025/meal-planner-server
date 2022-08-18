package com.meal_planner.jdbc.dao;

import com.meal_planner.jdbc.factory.MealObjectFactory;
import com.meal_planner.jdbc.model.Ingredient;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class IngredientDao extends AbstractDao<Ingredient> {

    private static String GET_INGREDIENTS_FOR_MEAL =
            "select ingredient.id, name, im.quantity, im.unit, im.count, food_group\n" +
            "from ingredient\n" +
            "join ingredient_measure as im on ingredient.id = im.ingredient_id\n" +
            "where im.meal_id = UUID_TO_BIN(?);";

    @Override
    public Optional<Ingredient> get(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Ingredient>> get() {
        return Optional.empty();
    }

    public Optional<List<Ingredient>> getByMealId(String mealId) {
        createConnection();
        createStatement(GET_INGREDIENTS_FOR_MEAL);
        setString(1, mealId);
        ResultSet result = executeQuery();
        Optional<List<Ingredient>> ingredients = Optional.ofNullable(MealObjectFactory.buildIngredients(result));
        closeConnection();
        return ingredients;
    }
}
