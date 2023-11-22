package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.dbConnection;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    private GameStateManager gsm;
    private static byte[] hash;
    private JLabel signupLbl;
    private JLabel unameLbl, passLbl, confpassLbl, ageLbl;
    private JTextField unameTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private JButton signupBtn, loginBtn;

    private String uname, password, age;
    static String generatedSecuredPasswordHash = null;
    static String passSalt, passHash;
    private Date created_date;
    private int uid;
//    Random random = new Random();
//    long number = Math.abs(Math.floor(random.nextLong() % 10000000000L)* 9000000000L) + 1000000000L);


    public Signup(GameStateManager gsm){
        this.gsm = gsm;
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
        setSize(GamePanel.width, GamePanel.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        setContentPane(new SpriteSheet.ImagePanel(background));



        signupLbl = new JLabel("SIGN UP");
        signupLbl.setBounds((GamePanel.width - signupLbl.getWidth())/3,60, GamePanel.width,100);

        unameLbl = new JLabel("Username");
        unameLbl.setBounds((GamePanel.width - unameLbl.getWidth())/6,210, 1000,60);

        passLbl = new JLabel("Password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,280, 1000,60);

        confpassLbl = new JLabel("Confirm Password");
        confpassLbl.setBounds((GamePanel.width - confpassLbl.getWidth())/6,350, 1000,60);

        ageLbl = new JLabel("Age");
        ageLbl.setBounds((GamePanel.width - ageLbl.getWidth())/6,420, 1000,60);

        signupBtn = new JButton("Sign up");
        signupBtn.setBounds((GamePanel.width / 2) - 100, 525, 200, 50);
        signupBtn.setBackground(new Color(67, 124, 23));
        signupBtn.setFont(new Font("Courier New", Font.BOLD, 30));
        signupBtn.setForeground(Color.WHITE);
        signupBtn.addActionListener(this);

        loginBtn = new JButton("Already have an account? Login");
        loginBtn.setBounds((GamePanel.width / 2) - 175, 585, 350, 30);
        loginBtn.setFont(new Font("Courier New", Font.BOLD, 15));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(this);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            signupLbl.setFont(font.deriveFont(Font.BOLD, 150));
            unameLbl.setFont(font.deriveFont(Font.BOLD, 50));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));
            confpassLbl.setFont(font.deriveFont(Font.BOLD, 50));
            ageLbl.setFont(font.deriveFont(Font.BOLD, 50));

            unameTF.setFont(font.deriveFont(Font.BOLD, 40));
            passPF.setFont(font.deriveFont(Font.BOLD, 40));
            confpassPF.setFont(font.deriveFont(Font.BOLD, 40));
            ageTF.setFont(font.deriveFont(Font.BOLD, 40));

//            signupBtn.setFont(font.deriveFont(Font.BOLD, 40));
//            loginBtn.setFont(font.deriveFont(Font.BOLD, 40));


        } catch (Exception e) {}

        add(signupLbl);
        add(unameLbl);
        add(passLbl);
        add(confpassLbl);
        add(ageLbl);

        add(signupBtn);
        add(loginBtn);

        input();
    }

    public void input(){
        unameTF = new JTextField();
        unameTF.setBounds(GamePanel.width/2,210, GamePanel.width/2 - GamePanel.width/6,50);
        unameTF.setOpaque(false);
        unameTF.setMargin(new Insets(0, 10,0,0));
        unameTF.setBorder(new LineBorder(Color.white,3));
        unameTF.setFont(new Font("Courier New", Font.PLAIN, 20));

        passPF = new JPasswordField();
        passPF.setBounds(GamePanel.width/2,280, GamePanel.width/2 - GamePanel.width/6,50);
        passPF.setOpaque(false);
        passPF.setMargin(new Insets(0, 10,0,0));
        passPF.setBorder(new LineBorder(Color.white,3));
        passPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        confpassPF = new JPasswordField();
        confpassPF.setBounds(GamePanel.width/2,350, GamePanel.width/2 - GamePanel.width/6,50);
        confpassPF.setOpaque(false);
        confpassPF.setMargin(new Insets(0, 10,0,0));
        confpassPF.setBorder(new LineBorder(Color.white,3));
        confpassPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        ageTF = new JTextField();
        ageTF.setBounds(GamePanel.width/2,420, GamePanel.width/2 - GamePanel.width/6,50);
        ageTF.setOpaque(false);
        ageTF.setMargin(new Insets(0, 10,0,0));
        ageTF.setBorder(new LineBorder(Color.white,3));
        ageTF.setFont(new Font("Courier New", Font.PLAIN, 20));


        this.add(unameTF);
        this.add(passPF);
        this.add(confpassPF);
        this.add(ageTF);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signupBtn) {
            uname = unameTF.getText();


            if (Arrays.equals(passPF.getPassword(), confpassPF.getPassword())) {
//                password = String.valueOf(passPF.getPassword());
                password = "pass";
                try {
                    generatedSecuredPasswordHash = doHashing(password);
                    passSalt = toHex(getSalt());
                    passHash= toHex(hash);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }

                try{
                    if(unameTF.getText().equals("") || passPF.getPassword().equals("") || confpassPF.getPassword().equals("") || ageTF.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                    }else{
//                    dbConnection con1 = new dbConnection();
                        String q = "INSERT INTO userTable VALUES (" + uid + ", '" + uname + "', 'null', " + age + ", '" + passHash + "', '" + created_date + "', NULL, FALSE, '" + passSalt +"');" ;
                        System.out.println(q);
//                        con1.s.executeUpdate(q);

//                    this.dispose();
//                    new Login(gsm).setVisible(true);
                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }
//                System.out.println(generatedSecuredPasswordHash);

//                Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
//
//                String encodedPassword = encoder.encode(password);
//                System.out.println(encodedPassword);
//
//                String validPassword = encoder.matches(myPassword, encodedPassword);
//                System.out.println(validPassword);
            } else if (!Arrays.equals(passPF.getPassword(), confpassPF.getPassword())){
                System.out.println("Passwords do not match");
            }

            age = ageTF.getText();
            uid = (int) generate();
            created_date = new java.sql.Date(System.currentTimeMillis());


//            System.out.println(uid);
//            System.out.println(created_date);
//
//            System.out.println(uname);
//            System.out.println(generatedSecuredPasswordHash);
//            System.out.println(age);
        }
        if(e.getSource() == loginBtn){
            this.dispose();
            new Login(gsm).setVisible(true);
        }
    }

    public String doHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{

        int iterations = 1000; //  number of times that the password is hashed
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        this.hash = skf.generateSecret(spec).getEncoded();


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

//    private static long last = 0;
//
//    public static long getID() {
//        // 10 digits.
//        long id = System.currentTimeMillis() % 10000000000L;
//        if ( id <= last ) {
//            id = (last + 1) % 10000000000L;
//        }
//        return last = id;
//    }

}
