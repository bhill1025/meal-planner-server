package com.meal_planner.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeInstruction {
    @JsonProperty
    private int sequence;
    @JsonProperty
    private String description;

    private RecipeInstruction(int sequence, String description) {
        this.sequence = sequence;
        this.description = description;
    }

    public static RecipeInstruction create(int sequence, String description) {
        return new RecipeInstruction(sequence, description);
    }
}
