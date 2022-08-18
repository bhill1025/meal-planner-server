package com.demo.jdbc.dao;

import com.demo.jdbc.model.Child;
import com.meal_planner.jdbc.dao.AbstractDao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class ChildDao extends AbstractDao<Child> {

    public static String GET_CHILD =
            "SELECT * FROM demo_schema.child WHERE id = ?";

    public static String GET_CHILDREN =
            "SELECT * FROM child";

    public static String GET_BOOKS =
            "SELECT id, title,  FROM book_with_uuid";

    public static String GET_BOOK_WITH_ID =
            "SELECT * FROM book_with_uuid WHERE id = ?";
;

    @Override
    public Optional<Child> get(String id) {
        createConnection();
        String[] params = new String[] {id};
        createStatement(GET_CHILD, params);
        ResultSet result = executeQuery();
        Optional<Child> toReturn = Optional.ofNullable(Child.createChild(result));
        closeConnection();
        return toReturn;
    }

    @Override
    public Optional<List<Child>> get() {
        createConnection();
        createStatement(GET_CHILDREN);
        ResultSet result = executeQuery();
        closeConnection();
        return Optional.ofNullable(Child.createChildren(result));
    }

    public ResultSet testUuid() {
        createConnection();
        createStatement(GET_BOOKS);
        ResultSet result = executeQuery();
        return result;
    }

    public ResultSet getBlob(Blob id) {
        createConnection();
        String[] params = new String[] {String.valueOf(id)};
        createStatement(GET_BOOKS);
        ResultSet result = executeQuery();
        return result;
    }
}
