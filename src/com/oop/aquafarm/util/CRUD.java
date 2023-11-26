package com.oop.aquafarm.util;

import com.oop.aquafarm.states.Signup;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
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

    public static long generate() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            int l = secureRandom.nextInt();
            int abs = Math.abs(1000000000 + l);
            return abs;
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
