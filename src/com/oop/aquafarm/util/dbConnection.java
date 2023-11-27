package com.oop.aquafarm.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class dbConnection {


    Connection con;
    public Statement s;
    public dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquafarm", "root", "rootpass");
            s = con.createStatement();

        } catch (Exception e){
            System.out.print(e);
        }
    }

}
