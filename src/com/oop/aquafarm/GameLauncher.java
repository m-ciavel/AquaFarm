package com.oop.aquafarm;

import java.sql.Connection;
import java.sql.DriverManager;

public class GameLauncher {

//    static Connection con;

    public GameLauncher(){
        new Window();
    }

    public static void main(String[] args){
        new GameLauncher();
//        try {
////            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AquaFarm", "root", "rootpass");
//            System.out.println(con);
//
//        } catch (Exception e){
//            System.out.print(e);
////            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
