package com.meal_planner.rest.controller;

import com.meal_planner.jdbc.model.Meal;
import com.meal_planner.jdbc.model.MealDisplayCard;
import com.meal_planner.rest.handler.MealRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MealPlannerRestController {

    private static final Logger logger = LoggerFactory.getLogger(MealPlannerRestController.class);

    @GetMapping("/meals")
    public HashMap getAllMeals() {
        HashMap response = new HashMap<String, String>();
        List<Meal> meals = MealRequestHandler.getAllMeals();
        response.put("meals", meals);
        return response;
    }

    @GetMapping("/meals/{id}")
    public HashMap getMealById(@PathVariable String id) {
        HashMap response = new HashMap<String, String>();
        List<Meal> meals = MealRequestHandler.getMealById(id);
        response.put("meals", meals);
//        try {
//            Thread.sleep(Duration.ofSeconds(3).toMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return response;
    }

    @GetMapping("/meals/displays")
    public HashMap getAllMealDisplays() {
        HashMap response = new HashMap<String, String>();
        List<MealDisplayCard> displayCards = MealRequestHandler.getAllMealDisplays();
        response.put("mealDisplays", displayCards);
        return response;
    }
}
