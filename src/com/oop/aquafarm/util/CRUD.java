package com.oop.aquafarm.util;

import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.entity.Finance;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.states.Login;
import com.oop.aquafarm.states.PlayState;
import com.oop.aquafarm.ui.Button;

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

    public static void createUser(dbConnection con1, int dbID, int uid, String uname, int age, String passSalt, String passHash) throws SQLException {
        Date created_date = new java.sql.Date(System.currentTimeMillis());
        String q0 = "INSERT INTO users VALUES ( " + dbID + ", '" + uid + "', TRUE );";
        String q1 = "INSERT INTO userTable VALUES (" + uid + ", '" + uname + "', " + age + ", '" + passSalt + "', '" + passHash + "', '" + created_date + "', NULL, FALSE" + ");" ;
        String qInvID = "SELECT * FROM userInvTbl WHERE itemID=(SELECT max(itemID) FROM userInvTbl);";
        int invID = 0;
        ResultSet rsInvID = con1.s.executeQuery(qInvID);
        if(rsInvID.next()){
            invID = rsInvID.getInt("itemID");
            invID++;
        }
        String q2 = "INSERT INTO userInvTbl VALUES (" + invID + ", '" + uname + "', " + Finance.money + ", null);";
        String qBtn = "INSERT INTO userBtntbl VALUES(" + dbID + ", '" + uname + "', TRUE, FALSE, FALSE, FALSE, FALSE, FALSE);" ;
        con1.s.executeUpdate(q0);
        con1.s.executeUpdate(q1);
        con1.s.executeUpdate(q2);
        con1.s.executeUpdate(qBtn);
        System.out.println("Successfully created account");
    }

    public static void updatePassword(dbConnection con1, String uname, String passSalt, String passHash) throws SQLException {
        Date updated_date = new java.sql.Date(System.currentTimeMillis());
        String qpass = "UPDATE userTable SET pass_salt = '" + passSalt + "', pass_hash = '" + passHash + "', updated_date = '" + updated_date + "' WHERE user_Name = '" + uname + "';";
        con1.s.executeUpdate(qpass);
        System.out.println("Successfully changed password");
    }

    public static void logIn(dbConnection con1, String uname, boolean loggedIn) throws SQLException {
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


    public static void addFish(dbConnection con1, Fish fish) throws SQLException {
        if (PlayState.fishCount <20) {
            Music.playSummonSound();
            int fishID = 0;
            int fishType = 0;
            Date fishDay = new java.sql.Date(System.currentTimeMillis());
            String qfID = "SELECT * FROM userFishInvTbl WHERE userFishID=(SELECT max(userFishID) FROM userFishInvTbl);";
            String qfishtype = "SELECT fish_ID AS fishType FROM fishtbl WHERE fish_type = '" + fish.getFishtype() + "';";
            ResultSet rsfID = con1.s.executeQuery(qfID);
            String fishname = fish.getFishName();
            if (rsfID.next()) {
                fishID = rsfID.getInt("userFishID");
                fishID++;
                fishname = fish.getFishName() + fishID;
            }

            ResultSet rsFtype = con1.s.executeQuery(qfishtype);
            if (rsFtype.next()) {
                fishType = rsFtype.getInt("fishType");
            }

            String qFishInv = "INSERT INTO userFishInvTbl VALUES (" + fishID + ", '" + Login.getUname() + "', " + fishType + ", '" + fishDay + "');";
            String qFishStat = "INSERT INTO userFishStatusTbl VALUES (" + fishID + ", '" + fishname + "', '" + fish.getFishGender() + "', " + fish.getFishsize() + ");";
            PlayState.addFishToArray(fish);
            String qInvMoney = "UPDATE userInvTbl SET money =" + Finance.money + " WHERE user_Name = '" + Login.getUname() + "';";
            String qTotalfish ="UPDATE userInvTbl SET fishnum = (SELECT COUNT(user_Name) FROM userFishInvTbl WHERE user_Name = '" + Login.getUname() + "') WHERE user_Name = '" + Login.getUname() + "';";

            con1.s.executeUpdate(qFishInv);
            con1.s.executeUpdate(qFishStat);
            con1.s.executeUpdate(qInvMoney);
            con1.s.executeUpdate(qTotalfish);

            System.out.println("Added new fish in inv");
        }
    }
    public static void removeFish(dbConnection con1, Fish fish) throws SQLException {
        String qdFish = "DELETE tinv FROM userfishinvtbl tinv INNER JOIN userFishstatusTbl tstat ON tinv.userFishid = tstat.userFishid WHERE fish_name = '" + fish.getFishName() + "';";
        String qInvMoney = "UPDATE userInvTbl SET money =" + Finance.money + " WHERE user_Name = '" + Login.getUname() + "';";
        String qTotalfish ="UPDATE userInvTbl SET fishnum = (SELECT COUNT(user_Name) FROM userFishInvTbl WHERE user_Name = '" + Login.getUname() + "') WHERE user_Name = '" + Login.getUname() + "';";

        con1.s.executeUpdate(qdFish);
        con1.s.executeUpdate(qInvMoney);
        con1.s.executeUpdate(qTotalfish);
//        System.out.println(qdFish);
//        System.out.println(qInvMoney);
        PlayState.removeFishFromArray(fish);
        System.out.println("Sold the fish for grandeur");
    }

    public static void updateFish(dbConnection con1, Fish fish) throws SQLException {
        String qudFish = "UPDATE userFishstatusTbl tstat INNER JOIN userfishinvtbl tinv ON tstat.userFishid = tinv.userFishid SET fish_size = " + fish.getFishsize() + " WHERE tinv.user_Name = '" + Login.getUname() + "' AND tstat.fish_name = '" + fish.getFishName() + "';";
        con1.s.executeUpdate(qudFish);
    }

    public static void getFish(dbConnection con1, String uname) throws SQLException {
        String qUFishes = "SELECT userFishInvTbl.userFishID, fishTbl.fish_type, userFishStatusTbl.fish_name, userFishStatusTbl.fish_gender, userFishStatusTbl.fish_size FROM userfishinvtbl JOIN fishtbl ON userfishinvtbl.fish_ID = fishtbl.fish_ID LEFT JOIN userFishStatusTbl ON userFishInvTbl.userFishID = userFishStatusTbl.userFishID WHERE user_Name = '" + uname + "' ORDER BY userFishID;";
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

    public static void getfPrice(dbConnection con1, Fish fish) throws SQLException {
        String qfprice = "SELECT fish_Price from fishTbl WHERE fish_Type = '" + fish.getFishtype() + "';";
        ResultSet rsfPrice = con1.s.executeQuery(qfprice);
        if(rsfPrice.next()){
            PlayState.price = rsfPrice.getInt("fish_Price");
        }
    }

    public static void spendMoney(dbConnection con1) throws SQLException {
        String qInvMoney = "UPDATE userInvTbl SET money =" + Finance.money + " WHERE user_Name = '" + Login.getUname() + "';";
        con1.s.executeUpdate(qInvMoney);
    }


    public static int getMoney(dbConnection con1) throws SQLException {
        String qMoney = "SELECT money FROM userInvTbl WHERE user_Name = '" + Login.getUname() + "';";
        ResultSet rsMoney = con1.s.executeQuery(qMoney);
        if (rsMoney.next()){
            Finance.money = rsMoney.getInt("money");
        }
        return Finance.money;
    }

    public static void deleteUser(dbConnection con1, String uname) throws SQLException {
        String qdelUser = "UPDATE users tallu INNER JOIN userTable tuser ON tallu.user_ID = tuser.user_ID SET is_active = FALSE WHERE tuser.user_Name = '" + uname + "';";
        String qdel = "DELETE FROM userTable WHERE user_Name = '" + uname + "';";
        con1.s.executeUpdate(qdelUser);
        con1.s.executeUpdate(qdel);
    }


    public static boolean getBtnEnabled(dbConnection con1, Button btn) throws SQLException {
        boolean enabled = false;
        String sBtn = "btnFish1";
        if (btn == PlayState.btnFish1){
            sBtn = "btnFish1";
        }else if(btn == PlayState.btnFish2){
            sBtn = "btnFish2";
        }else if(btn == PlayState.btnFish3){
            sBtn = "btnFish3";
        }else if(btn == PlayState.btnFish4){
            sBtn = "btnFish4";
        }else if(btn == PlayState.btnFish5){
            sBtn = "btnFish5";
        }else if(btn == PlayState.btnFish6){
            sBtn = "btnFish6";
        }
        String qbtn = "SELECT " + sBtn + " FROM userBtntbl WHERE user_Name = '" + Login.getUname() + "';";
        ResultSet rsbtn = con1.s.executeQuery(qbtn);
        while(rsbtn.next()){
            enabled = rsbtn.getBoolean(sBtn);
        }
        System.out.println(qbtn);
        System.out.println(sBtn + " = " + enabled);

        btn.enabled = true;

        return enabled;
    }


    public static int getFishCount(dbConnection con1, String fish_type) throws SQLException {

        String qfishCount = "SELECT COUNT(userFishInvTbl.fish_ID) FROM userfishinvtbl JOIN fishtbl ON userfishinvtbl.fish_ID = fishtbl.fish_ID WHERE user_Name = '" + Login.getUname() + "' AND fishtbl.fish_type = '" + fish_type + "';";
        int fishCount = 0;
        ResultSet rsbtn = con1.s.executeQuery(qfishCount);
        if(rsbtn.next()){
            fishCount = rsbtn.getInt("COUNT(userFishInvTbl.fish_ID)");
        }
        return fishCount;
    }

    public static void setEnabled(dbConnection con1, Button btn) throws SQLException {
        String sBtn = "btnFish1";
        if (btn == PlayState.btnFish1){
            sBtn = "btnFish1";
        }else if(btn == PlayState.btnFish2){
            sBtn = "btnFish2";
        }else if(btn == PlayState.btnFish3){
            sBtn = "btnFish3";
        }else if(btn == PlayState.btnFish4){
            sBtn = "btnFish4";
        }else if(btn == PlayState.btnFish5){
            sBtn = "btnFish5";
        }else if(btn == PlayState.btnFish6){
            sBtn = "btnFish6";
        }
        String qbtn = "UPDATE userBtntbl SET " + sBtn + " = true WHERE user_Name = '" + Login.getUname() + "';";
        con1.s.executeUpdate(qbtn);
    }

    public static long generateUID() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            int l = secureRandom.nextInt();
            return Math.abs(1000000000 + l);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
//    public static long generatesessionID() {
//        try {
//            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//            long l = secureRandom.nextLong();
//            return Math.abs(100000000000000L + l * 900000000000000L);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }


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
