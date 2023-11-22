package com.oop.aquafarm.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class dbConnection {

    Connection con;
    Statement s;
    public dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Connection");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquafarm", "root", "rootpass");
            s = con.createStatement();
//            System.out.println(con);

        } catch (Exception e){
            System.out.print(e);
//            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
