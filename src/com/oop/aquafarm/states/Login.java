package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.CRUD;
import com.oop.aquafarm.util.dbConnection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JFrame  implements ActionListener {
    private JLabel loginLbl;
    private JLabel unameLbl, passLbl, notifLbl;
    private JTextField unameTF;
    private JPasswordField passPF;
    private JButton signupBtn, loginBtn, backBtn;

    private static String uname;
    private int iterations = CRUD.getIterations();
    dbConnection con1 = new dbConnection();
    public static boolean loggedIn;


    ImageIcon backBtnIcon = new ImageIcon("res/menubutton/arrow back.png");
    GameStateManager gsm;

    public Login(GameStateManager gsm){
        this.gsm = gsm;
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
        setSize(GamePanel.width, GamePanel.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/hand/cursor.open.png").getImage(), new Point(0,0),"custom"));

        setResizable(false);
        setLocationRelativeTo(null);
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        setContentPane(new SpriteSheet.ImagePanel(background));

        loggedIn = false;


        loginLbl = new JLabel("LOGIN");
        loginLbl.setBounds((GamePanel.width - loginLbl.getWidth())/3,60, GamePanel.width,100);

        unameLbl = new JLabel("Username");
        unameLbl.setBounds((GamePanel.width - unameLbl.getWidth())/6,270, 1000,60);

        passLbl = new JLabel("Password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,360, 1000,60);

        notifLbl = new JLabel("",  SwingConstants.CENTER);
        notifLbl.setBounds(0,480, GamePanel.width,30);
        notifLbl.setForeground(Button.borderdarkred);


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

        backBtn = new JButton();
        backBtn.setIcon(backBtnIcon);
        backBtn.setBounds(GamePanel.width - 100, GamePanel.height - 100, 100, 50);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(this);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            loginLbl.setFont(font.deriveFont(Font.BOLD, 150));
            unameLbl.setFont(font.deriveFont(Font.BOLD, 50));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));
            notifLbl.setFont(font.deriveFont(Font.PLAIN, 30));


        } catch (Exception e) {}

        add(loginLbl);
        add(unameLbl);
        add(passLbl);
        add(notifLbl);

        add(loginBtn);
        add(signupBtn);
        add(backBtn);

        input();
    }

    public void input(){
        unameTF = new JTextField();
        unameTF.setBounds(GamePanel.width/2,270, GamePanel.width/2 - GamePanel.width/6,50);
        unameTF.setOpaque(false);
        unameTF.setMargin(new Insets(0, 10,0,0));
        unameTF.setBorder(new LineBorder(Color.white,3));
        unameTF.setFont(new Font("Courier New", Font.PLAIN, 20));

        passPF = new JPasswordField();
        passPF.setBounds(GamePanel.width/2,360, GamePanel.width/2 - GamePanel.width/6,50);
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
            if(unameTF.getText().isEmpty() || passPF.getPassword().length == 0 ){
                notifLbl.setText("Please   fill   all   the   required   fields");
                if(unameTF.getText().isEmpty()){
                    unameTF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    unameTF.setBorder(new LineBorder(Color.white,3));
                }

                if(passPF.getPassword().length == 0){
                    passPF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    passPF.setBorder(new LineBorder(Color.white,3));
                }

            }else {
                // create connection
                try {

                    uname = unameTF.getText();
                    String qUser = "SELECT * FROM userTable WHERE user_Name = '" + uname + "';";
                    ResultSet rs = con1.s.executeQuery(qUser);
                    if (rs.next()) {
                        String pSalt = rs.getString("pass_salt");
                        String pHash = rs.getString("pass_hash");

                        try {
                            boolean matched = CRUD.validatePassword(Arrays.toString(passPF.getPassword()), iterations + ":" + pSalt + ":" + pHash);
                            System.out.println(matched);

                            if (matched){
                                loggedIn = true;
                                CRUD.logIn(con1, uname, true);
                                if (gsm.isStateActive(GameStateManager.PLAY)){
                                    GameStateManager.pop(GameStateManager.PLAY);
                                }else{
                                    gsm.add(GameStateManager.PLAY);
                                    GameStateManager.pop(GameStateManager.TITLE);
                                }
                                CRUD.getFish(con1, uname);
                                Window.window.setVisible(true);
                                this.dispose();
                            }else if (!matched){
                                System.out.println("Password does not match");
                                notifLbl.setText("Username   and   password   does   not   seem   to   match");
                                unameTF.setBorder(new LineBorder(Button.borderdarkred,3));
                                passPF.setBorder(new LineBorder(Button.borderdarkred,3));
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else{
                        notifLbl.setText("No   such   user   in   database");
                        unameTF.setBorder(new LineBorder(Button.borderdarkred,3));
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        }
        if(e.getSource() == signupBtn){
            this.dispose();
            new Signup(gsm).setVisible(true);
        }
        if(e.getSource() == backBtn){
            if (gsm.isStateActive(GameStateManager.TITLE)){
                GameStateManager.pop(GameStateManager.TITLE);

            }else{
                gsm.add(GameStateManager.TITLE);
                Window.window.setVisible(true);
                this.dispose();
            }
        }

    }

    public static void setUname(String uname){
        Login.uname = uname;
    }
    public static String getUname(){
        return uname;
    }


}