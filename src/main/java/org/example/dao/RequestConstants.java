package org.example.dao;

public class RequestConstants {
    private RequestConstants() {}
    public static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGSERIAL PRIMARY KEY," +
            "name VARCHAR(255), " +
            "lastName VARCHAR(255), " +
            "age INT, " +
            "email VARCHAR(255))";
    public static final String GET_ALL_USERS_HQL = "from User";
}