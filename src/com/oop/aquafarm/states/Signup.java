package com.oop.aquafarm.states;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.dbConnection;


//import javax.swing.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class Signup extends JFrame implements ActionListener {
    private GameStateManager gsm;
    private JLabel signupLbl;
    private JLabel unameLbl, passLbl, confpassLbl, ageLbl;
    private JTextField unameTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private JButton signupBtn, loginBtn;

    private static int iterations = 1000; //  number of times that the password is hashed
    private String uname, password;
    static String generatedSecuredPasswordHash = null;
    static String passSalt, passHash;
    private Date created_date;
    private int uid, age, dbID;



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

//            unameTF.setFont(font.deriveFont(Font.BOLD, 40));
//            passPF.setFont(font.deriveFont(Font.BOLD, 40));
//            confpassPF.setFont(font.deriveFont(Font.BOLD, 40));
//            ageTF.setFont(font.deriveFont(Font.BOLD, 40));

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
            if(unameTF.getText().equals("") || passPF.getPassword().equals("") || confpassPF.getPassword().equals("") || ageTF.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                if(unameTF.getText().equals("")){
                    unameTF.setBorder(new LineBorder(Color.red,3));
                }
                if(passPF.getPassword().equals("")){
                    passPF.setBorder(new LineBorder(Color.red,3));
                }
                if(confpassPF.getPassword().equals("")){
                    confpassPF.setBorder(new LineBorder(Color.red,3));
                }
                if(passPF.getPassword().equals("")){
                    unameTF.setBorder(new LineBorder(Color.red,3));
                }
                if(ageTF.getText().equals("")){
                    ageTF.setBorder(new LineBorder(Color.red,3));
                }
            } else {
                if (Arrays.equals(passPF.getPassword(), confpassPF.getPassword())) {
                    // create connection
                    try{
                        dbConnection con1 = new dbConnection();
                        String qID = "SELECT * FROM users WHERE id=(SELECT max(ID) FROM users)";
                        ResultSet rs = con1.s.executeQuery(qID);
                        if(rs.next()){
                            dbID = Integer.parseInt(rs.getString("ID"));
//                            System.out.println(dbID);
                        }


                        // get values
                        try {
                            // password
                            try {

                                generatedSecuredPasswordHash = doHashing(Arrays.toString(passPF.getPassword()));
//                                String[] parts = doHashing(Arrays.toString(passPF.getPassword())).split(":");
                                String[] parts = generatedSecuredPasswordHash.split(":");
                                passSalt = parts[1];
                                passHash = parts[2];
                                System.out.println(generatedSecuredPasswordHash);
                                System.out.println(passSalt);
                                System.out.println(passHash);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                throw new RuntimeException(ex);
                            }
                            dbID++;
                            age = Integer.parseInt(ageTF.getText());
                            uname = unameTF.getText();
                            uid = (int) generate();
                            created_date = new java.sql.Date(System.currentTimeMillis());

                            String q0 = "INSERT INTO users VALUES ( " + dbID + ", '" + uid + "', TRUE );";
                            String q1 = "INSERT INTO userTable VALUES (" + uid + ", '" + uname + "', " + age + ", '" + passSalt + "', '" + passHash + "', '" + created_date + "', NULL, FALSE" + ");" ;
                            System.out.println(q0); System.out.println(q1);
                            con1.s.executeUpdate(q0);
                            con1.s.executeUpdate(q1);
                            System.out.println("Succesfully created account");

                            this.dispose();
                            new Login(gsm).setVisible(true);

                        } catch (NumberFormatException nfe){
                            ageTF.setText("");
                            JOptionPane.showMessageDialog(null, "Please enter a valid number for age");
                        }
//                        catch (SQLException ex) {
//                            throw new RuntimeException(ex);
//                        }
                        // end of inserting values
                    } catch (HeadlessException | SQLException ex) {
                        throw new RuntimeException(ex);
                    } // end of connection


                } else if (!Arrays.equals(passPF.getPassword(), confpassPF.getPassword())){
                    System.out.println("Passwords do not match");
                }
            } // blank textfields

        }

        if(e.getSource() == loginBtn){
            this.dispose();
            new Login(gsm).setVisible(true);
        }
    }

    public String doHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{


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

}
