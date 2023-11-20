package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;

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

public class Signup extends JFrame implements ActionListener {
//    protected GameStateManager gsm;
    private JLabel signupLbl;
    private JLabel unameLbl, passLbl, confpassLbl, emailLbl, ageLbl;
    private JTextField unameTF, emailTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private JButton signupBtn, loginBtn;

    private String uname, password, confpassword, email, age;
    static String generatedSecuredPasswordHash = null;

    public Signup(){
//        this.gsm = gsm;
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
        setSize(GamePanel.width, GamePanel.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        setContentPane(new ImagePanel(background));



        signupLbl = new JLabel("SIGN UP");
        signupLbl.setBounds((GamePanel.width - signupLbl.getWidth())/3,60, GamePanel.width,100);

        unameLbl = new JLabel("Username");
        unameLbl.setBounds((GamePanel.width - unameLbl.getWidth())/6,190, 1000,60);

        passLbl = new JLabel("Password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,255, 1000,60);

        confpassLbl = new JLabel("Confirm Password");
        confpassLbl.setBounds((GamePanel.width - confpassLbl.getWidth())/6,320, 1000,60);

        ageLbl = new JLabel("Age");
        ageLbl.setBounds((GamePanel.width - ageLbl.getWidth())/6,375, 1000,60);

        emailLbl = new JLabel("Email");
        emailLbl.setBounds((GamePanel.width - emailLbl.getWidth())/6,450, 1000,60);

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
            emailLbl.setFont(font.deriveFont(Font.BOLD, 50));

            unameTF.setFont(font.deriveFont(Font.BOLD, 40));
            passPF.setFont(font.deriveFont(Font.BOLD, 40));
            confpassPF.setFont(font.deriveFont(Font.BOLD, 40));
            ageTF.setFont(font.deriveFont(Font.BOLD, 40));
            emailTF.setFont(font.deriveFont(Font.BOLD, 40));

//            signupBtn.setFont(font.deriveFont(Font.BOLD, 40));
//            loginBtn.setFont(font.deriveFont(Font.BOLD, 40));


        } catch (Exception e) {}

        add(signupLbl);
        add(unameLbl);
        add(passLbl);
        add(confpassLbl);
        add(ageLbl);
        add(emailLbl);

        add(signupBtn);
        add(loginBtn);

        input();
    }

    public void input(){
        unameTF = new JTextField();
        unameTF.setBounds(GamePanel.width/2,190, GamePanel.width/2 - GamePanel.width/6,50);
        unameTF.setOpaque(false);
        unameTF.setMargin(new Insets(0, 10,0,0));
        unameTF.setBorder(new LineBorder(Color.white,3));
        unameTF.setFont(new Font("Courier New", Font.PLAIN, 20));

        passPF = new JPasswordField();
        passPF.setBounds(GamePanel.width/2,255, GamePanel.width/2 - GamePanel.width/6,50);
        passPF.setOpaque(false);
        passPF.setMargin(new Insets(0, 10,0,0));
        passPF.setBorder(new LineBorder(Color.white,3));
        passPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        confpassPF = new JPasswordField();
        confpassPF.setBounds(GamePanel.width/2,320, GamePanel.width/2 - GamePanel.width/6,50);
        confpassPF.setOpaque(false);
        confpassPF.setMargin(new Insets(0, 10,0,0));
        confpassPF.setBorder(new LineBorder(Color.white,3));
        confpassPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        ageTF = new JTextField();
        ageTF.setBounds(GamePanel.width/2,385, GamePanel.width/2 - GamePanel.width/6,50);
        ageTF.setOpaque(false);
        ageTF.setMargin(new Insets(0, 10,0,0));
        ageTF.setBorder(new LineBorder(Color.white,3));
        ageTF.setFont(new Font("Courier New", Font.PLAIN, 20));

        emailTF = new JTextField();
        emailTF.setBounds(GamePanel.width/2,450, GamePanel.width/2 - GamePanel.width/6,50);
        emailTF.setOpaque(false);
        emailTF.setMargin(new Insets(0, 10,0,0));
        emailTF.setBorder(new LineBorder(Color.white,3));
        emailTF.setFont(new Font("Courier New", Font.PLAIN, 20));

        this.add(unameTF);
        this.add(passPF);
        this.add(confpassPF);
        this.add(ageTF);
        this.add(emailTF);


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
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
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
            email = emailTF.getText();

            System.out.println(uname);
            System.out.println(generatedSecuredPasswordHash);
            System.out.println(age);
            System.out.println(email);
        }
        if(e.getSource() == loginBtn){
            this.dispose();
            new Login().setVisible(true);
        }
    }

    public static String doHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{

        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
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

    class ImagePanel extends JComponent{
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }

    }
}
