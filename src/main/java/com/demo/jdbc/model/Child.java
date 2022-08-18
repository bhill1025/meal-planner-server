package com.demo.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Child {

    @JsonProperty
    String id;
    @JsonProperty
    String name;

    public Child(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Child createChild(ResultSet resultSet) {
        return createChildren(resultSet).get(0);
    }

    public static List<Child> createChildren(ResultSet resultSet) {
        List<Child> children = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                children.add(new Child(id, name));
            }
        } catch (SQLException e) {
            return null;
        }
        return children;
    }
}
