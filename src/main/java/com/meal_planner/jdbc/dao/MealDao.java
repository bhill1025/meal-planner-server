package com.meal_planner.jdbc.dao;

import com.meal_planner.jdbc.factory.MealObjectFactory;
import com.meal_planner.jdbc.model.Meal;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class MealDao extends AbstractDao<Meal>{

    private static String GET_MEALS = "select \n" +
            "BIN_TO_UUID(id) as id, name, description, calories, servings, prep_time, cook_time \n" +
            "from meal;";

    private static String GET_MEAL_BY_ID = "select \n" +
            "BIN_TO_UUID(id) as id, name, description, calories, servings, prep_time, cook_time \n" +
            "from meal " +
            "WHERE id = UUID_TO_BIN(?);";

    @Override
    public Optional<Meal> get(String id) {
        createConnection();
        createStatement(GET_MEAL_BY_ID);
        setString(1, id);
        ResultSet result = executeQuery();
        Optional<Meal> meal = Optional.ofNullable(MealObjectFactory.buildMeals(result).get(0));
        closeConnection();
        return meal;
    }

    @Override
    public Optional<List<Meal>> get() {
        createConnection();
        createStatement(GET_MEALS);
        ResultSet result = executeQuery();
        Optional<List<Meal>> meals = Optional.ofNullable(MealObjectFactory.buildMeals(result));
        closeConnection();
        return meals;
    }
}
