package com.oop.aquafarm;

import java.sql.Connection;
import java.sql.DriverManager;

public class GameLauncher {


    public GameLauncher(){
        new Window();
    }

    public static void main(String[] args){
        new GameLauncher();
    }

}
