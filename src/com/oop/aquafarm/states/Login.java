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

import static com.oop.aquafarm.states.Signup.generatedSecuredPasswordHash;

public class Login extends JFrame  implements ActionListener {
    private JLabel loginLbl;
    private JLabel unameLbl, passLbl, confpassLbl, emailLbl, ageLbl;
    private JTextField unameTF, emailTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private JButton signupBtn, loginBtn;

    private String uname, password, confpassword, email, age;

    public Login(){
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



        loginLbl = new JLabel("LOGIN");
        loginLbl.setBounds((GamePanel.width - loginLbl.getWidth())/3,60, GamePanel.width,100);

        unameLbl = new JLabel("Username");
        unameLbl.setBounds((GamePanel.width - unameLbl.getWidth())/6,190, 1000,60);

        passLbl = new JLabel("Password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,255, 1000,60);


        emailLbl = new JLabel("Email");
        emailLbl.setBounds((GamePanel.width - emailLbl.getWidth())/6,450, 1000,60);

        loginBtn = new JButton("Login");
        loginBtn.setBounds((GamePanel.width / 2) - 100, 525, 200, 50);
        loginBtn.setBackground(new Color(67, 124, 23));
        loginBtn.setFont(new Font("Courier New", Font.BOLD, 30));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);

        signupBtn = new JButton("Create Account");
        signupBtn.setBounds((GamePanel.width / 2) - 175, 585, 350, 30);
        signupBtn.setFont(new Font("Courier New", Font.BOLD, 15));
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setContentAreaFilled(false);
        signupBtn.setBorderPainted(false);
        signupBtn.addActionListener(this);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            loginLbl.setFont(font.deriveFont(Font.BOLD, 150));
            unameLbl.setFont(font.deriveFont(Font.BOLD, 50));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));

            unameTF.setFont(font.deriveFont(Font.BOLD, 40));
            passPF.setFont(font.deriveFont(Font.BOLD, 40));

//            signupBtn.setFont(font.deriveFont(Font.BOLD, 40));
//            loginBtn.setFont(font.deriveFont(Font.BOLD, 40));


        } catch (Exception e) {}

        add(loginLbl);
        add(unameLbl);
        add(passLbl);

        add(loginBtn);
        add(signupBtn);

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

        this.add(unameTF);
        this.add(passPF);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginBtn) {
            uname = unameTF.getText();
            password = String.valueOf(passPF.getPassword());
            generatedSecuredPasswordHash = "1000:e7f7b64e917b923dd6231910b7ff1b3e:10d881cdc89ae8c48b001f7c49eb62db26221177b70f62b143badecb33f677c0f182c37c25f0127eeeeb42dcf5ad979656d4a7c1d2f8f100d8df80a1b958f033";

            boolean matched = false;
            try {
                matched = validatePassword(password, generatedSecuredPasswordHash);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(matched);

//            String generatedSecuredPasswordHash = null;
//            if (Arrays.equals(passPF.getPassword(), confpassPF.getPassword())) {
//                password = String.valueOf(passPF.getPassword());
//                try {
//                    generatedSecuredPasswordHash = doHashing(password);
//                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
//                    throw new RuntimeException(ex);
//                }
//                System.out.println(generatedSecuredPasswordHash);

//                Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
//
//                String encodedPassword = encoder.encode(password);
//                System.out.println(encodedPassword);
//
//                String validPassword = encoder.matches(myPassword, encodedPassword);
//                System.out.println(validPassword);
//            } else if (!Arrays.equals(passPF.getPassword(), confpassPF.getPassword())){
//                System.out.println("Passwords do not match");
//            }


            System.out.println(uname);
        }
        if(e.getSource() == signupBtn){
            this.dispose();
            new Signup().setVisible(true);
        }
    }

    private static boolean validatePassword(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
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