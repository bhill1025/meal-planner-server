package com.meal_planner.jdbc.dao;

import com.meal_planner.jdbc.factory.MealObjectFactory;
import com.meal_planner.jdbc.model.MealDisplayCard;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class MealDisplayCardDao extends AbstractDao<MealDisplayCard> {

    private static String GET_DISPLAY_CARDS = "select \n" +
            "meal.name as meal_name, BIN_TO_UUID(meal.id) as meal_id, \n" +
            "user.username as creator_name, BIN_TO_UUID(user.id) as creator_id, \n" +
            "meal.date_created as create_date, meal_display.profile_pic, meal_display.banner_pic\n" +
            "from meal\n" +
            "join user on meal.creator_id = user.id\n" +
            "join meal_display on meal.id = meal_display.meal_id";

    private static String GET_DISPLAY_CARD_BY_MEAL = "select \n" +
            "meal.name as meal_name, BIN_TO_UUID(meal.id) as meal_id, \n" +
            "user.username as creator_name, BIN_TO_UUID(user.id) as creator_id, \n" +
            "meal.date_created as create_date, meal_display.profile_pic, meal_display.banner_pic\n" +
            "from meal\n" +
            "join user on meal.creator_id = user.id\n" +
            "join meal_display on meal.id = meal_display.meal_id\n" +
            "where meal.id = UUID_TO_BIN(?);";

    @Override
    public Optional<MealDisplayCard> get(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MealDisplayCard>> get() {
        createConnection();
        createStatement(GET_DISPLAY_CARDS);
        ResultSet result = executeQuery();
        Optional<List<MealDisplayCard>> displayCards =
                Optional.ofNullable(MealObjectFactory.buildMealDisplayCards(result));
        closeConnection();
        return displayCards;
    }

    public Optional<List<MealDisplayCard>> getByMealId(String mealId) {
        createConnection();
        createStatement(GET_DISPLAY_CARD_BY_MEAL);
        setString(1, mealId);
        ResultSet result = executeQuery();
        Optional<List<MealDisplayCard>> displayCards =
                Optional.ofNullable(MealObjectFactory.buildMealDisplayCards(result));
        closeConnection();
        return displayCards;
    }
}
