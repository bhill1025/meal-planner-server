package com.meal_planner.jdbc.dao;

import com.meal_planner.jdbc.factory.MealObjectFactory;
import com.meal_planner.jdbc.model.RecipeInstruction;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class RecipeInstructionDao extends AbstractDao<RecipeInstruction> {

    private static String GET_INSTRUCTIONS_FOR_MEAL =
            "select sequence, description \n" +
                    "from recipe_instruction as ri\n" +
                    "where ri.meal_id = UUID_TO_BIN(?)\n" +
                    "order by sequence asc;";

    @Override
    public Optional<RecipeInstruction> get(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<RecipeInstruction>> get() {
        return Optional.empty();
    }

    public Optional<List<RecipeInstruction>> getByMealId(String mealId) {
        createConnection();
        createStatement(GET_INSTRUCTIONS_FOR_MEAL);
        setString(1, mealId);
        ResultSet result = executeQuery();
        Optional<List<RecipeInstruction>> instructions =
                Optional.ofNullable(MealObjectFactory.buildRecipeInstructions(result));
        closeConnection();
        return instructions;
    }
}
