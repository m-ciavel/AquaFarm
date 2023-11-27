package com.oop.aquafarm.util;

import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.states.Login;
import com.oop.aquafarm.states.PlayState;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CRUD {

    private static final int iterations = 1000; //  number of times that the password is hashed

    public static void createUser(int dbID, int uid, String uname, int age, String passSalt, String passHash, Date created_date) throws SQLException {
        dbConnection con1 = new dbConnection();
        String q0 = "INSERT INTO users VALUES ( " + dbID + ", '" + uid + "', TRUE );";
        String q1 = "INSERT INTO userTable VALUES (" + uid + ", '" + uname + "', " + age + ", '" + passSalt + "', '" + passHash + "', '" + created_date + "', NULL, FALSE" + ");" ;
        System.out.println(q0); System.out.println(q1);
        con1.s.executeUpdate(q0);
        con1.s.executeUpdate(q1);
        System.out.println("Successfully created account");
    }

    public static void logIn(String uname, boolean loggedIn) throws SQLException {
        dbConnection con1 = new dbConnection();
        String log;
        if(loggedIn){
            log = "in";
        }else {
            log = "out";
        }
        Date loggedInDate = new java.sql.Date(System.currentTimeMillis());
            String qLog = "UPDATE userTable SET logged_in = " + loggedIn + " WHERE user_Name = '" + uname + "';";
//            System.out.println(qLog);
            con1.s.executeUpdate(qLog);
            System.out.println("logged " + log + " @ " + loggedInDate);
    }


    public static void addFish(Fish fish) throws SQLException {
        dbConnection con1 = new dbConnection();
        int fishID = 0;
        int fishType = 0;
        Date fishDay = new java.sql.Date(System.currentTimeMillis());
        String qfID = "SELECT * FROM userFishInvTbl WHERE userFishID=(SELECT max(userFishID) FROM userFishInvTbl);";
        String qfishtype = "SELECT fish_ID AS fishType FROM fishtbl WHERE fish_type = '" + fish.getFishtype() + "';";
        ResultSet rsfID = con1.s.executeQuery(qfID);
        String fishname = fish.getFishName();
        if(rsfID.next()){
            fishID = rsfID.getInt("userFishID");
            fishID++;
            fishname = fish.getFishName() + fishID;
        }

        ResultSet rsFtype = con1.s.executeQuery(qfishtype);
        if(rsFtype.next()){
            fishType = rsFtype.getInt("fishType");
        }

        String qFishInv = "INSERT INTO userFishInvTbl VALUES (" + fishID + ", '" + Login.getUname() + "', " + fishType + ", '" + fishDay + "');";
        String qFishStat = "INSERT INTO userFishStatusTbl VALUES (" + fishID + ", '" + fishname + "', '" + fish.getFishGender() + "', " + fish.getFishsize() + ");";
        System.out.println(qFishInv);  System.out.println(qFishStat);
        con1.s.executeUpdate(qFishInv);
        con1.s.executeUpdate(qFishStat);
        System.out.println("Added new fish in inv");
    }

    public static void getFish() throws SQLException {
        dbConnection con1 = new dbConnection();
        String qUFishes = "SELECT userFishInvTbl.userFishID, fishTbl.fish_type, userFishStatusTbl.fish_name, userFishStatusTbl.fish_gender, userFishStatusTbl.fish_size FROM userfishinvtbl JOIN fishtbl ON userfishinvtbl.fish_ID = fishtbl.fish_ID LEFT JOIN userFishStatusTbl ON userFishInvTbl.userFishID = userFishStatusTbl.userFishID WHERE user_Name = '" + Login.getUname() + "' ORDER BY userFishID;";
        ResultSet rsUFishes = con1.s.executeQuery(qUFishes);

        while (rsUFishes.next()) {
            String fish_type = rsUFishes.getString("fish_type");
            String fish_name = rsUFishes.getString("fish_name");
            String gender = rsUFishes.getString("fish_gender");
            int fish_size = rsUFishes.getInt("fish_size");
//            System.out.println("Fish fish = new Fish(origin, \"" + fish_type + "\", " + fish_name + ", " + gender + ", " + fish_size + ");");

            Fish fish = new Fish(PlayState.origin, fish_type, fish_name, gender, fish_size);
            PlayState.addFishToArray(fish);
        }

    }


//    public static void logIn(String uname, boolean loggedIn) throws SQLException {
//        dbConnection con1 = new dbConnection();
//        Date loggedInDate = new java.sql.Date(System.currentTimeMillis());
//        Date expireDate =  new java.sql.Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
//        long sessionID = generatesessionID();
//        boolean sidExists = false;
//        String qsid = "SELECT * FROM sessionTbl WHERE session_ID = '" + sessionID + "';";
//        ResultSet rssid = con1.s.executeQuery(qsid);
//        if (rssid.next()){
//            do{
//                sessionID = (int) generatesessionID();
//                rssid = con1.s.executeQuery(qsid);
//                sidExists = rssid.next();
//            }while(sidExists);
//            if(!sidExists){
//                String qLog = "UPDATE userTable SET logged_in = " + loggedIn + " WHERE user_Name = '" + uname + "';";
////                String qSession = "INSERT INTO sessionTbl(" + sessionID + "', '" + loggedInDate + "', '" + expireDate + "');";
////                System.out.println(qLog); System.out.println(qSession);
//        con1.s.executeUpdate(qLog);
//                System.out.println("logged in @ " + loggedInDate);
//                System.out.println("session expires at @ " + expireDate);
//            }
//        }else{
//            String qLog = "UPDATE userTable SET logged_in = " + loggedIn + " WHERE user_Name = '" + uname + "';";
//            String qSession = "INSERT INTO sessionTbl(" + sessionID + "', '" + loggedInDate + "', '" + expireDate + "');";
//            System.out.println(qLog); System.out.println(qSession);
//        con1.s.executeUpdate(qLog);
//            System.out.println("logged in @ " + loggedInDate);
//            System.out.println("session expires at @ " + expireDate);
//        }
//
//
//    }



    public static long generateUID() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            int l = secureRandom.nextInt();
            return Math.abs(1000000000 + l);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static long generatesessionID() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            long l = secureRandom.nextLong();
            return Math.abs(100000000000000L + l * 900000000000000L);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static int getIterations(){
        return iterations;
    }

    public static String doHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{


        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();

        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
//        return toHex(hash);
    }
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    public static boolean validatePassword(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
