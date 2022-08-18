package com.demo.jdbc;

import com.demo.jdbc.model.Child;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DemoJDBC {

    public static List<Child> getChildren() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_schema",
                "admin",
                "Welcome@123");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from child");

        List<Child> result = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            result.add(new Child(id, name));
        }
        connection.close();
        return result;
    }
}
