package com.collegequera.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectquera?useSSL=false&allowPublicKeyRetrieval=true","root","Aashiv@12345");
        return cnn;
    }
}
