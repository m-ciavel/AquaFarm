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

public class Account extends JFrame implements ActionListener {

    GameStateManager gsm;
    private int iterations = CRUD.getIterations();
    static String generatedSecuredPasswordHash = null;
    static String passSalt, passHash;
    dbConnection con1 = new dbConnection();
    private JLabel securityLbl;
    private JLabel passLbl, newpassLbl, confnewpassLbl, notifLbl;
    private JPasswordField passPF, newpassPF, confnewpassPF;
    private JButton confirmBtn, backBtn, deleteBtn;

    ImageIcon backBtnIcon = new ImageIcon("res/menubutton/arrow back.png");

    public Account(GameStateManager gsm){
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

        securityLbl = new JLabel("SECURITY");
        securityLbl.setBounds((GamePanel.width - securityLbl.getWidth())/3,60, GamePanel.width,100);

        passLbl = new JLabel("Old password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,210, 1000,60);

        newpassLbl = new JLabel("New password");
        newpassLbl.setBounds((GamePanel.width - newpassLbl.getWidth())/6,350, 1000,60);

        confnewpassLbl = new JLabel("Confirm password");
        confnewpassLbl.setBounds((GamePanel.width - confnewpassLbl.getWidth())/6,420, 1000,60);

        notifLbl = new JLabel("",  SwingConstants.CENTER);
        notifLbl.setBounds(0,480, GamePanel.width,30);
        notifLbl.setForeground(Button.borderdarkred);

        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds((GamePanel.width / 2) - 100, 525, 200, 50);
        confirmBtn.setBackground(new Color(67, 124, 23));
        confirmBtn.setFont(new Font("Courier New", Font.BOLD, 30));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.addActionListener(this);

        deleteBtn = new JButton("Delete Account");
        deleteBtn.setBounds((GamePanel.width / 2) - 175, 585, 350, 30);
        deleteBtn.setFont(new Font("Courier New", Font.BOLD, 15));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.addActionListener(this);

        backBtn = new JButton();
        backBtn.setIcon(backBtnIcon);
        backBtn.setBounds(GamePanel.width - 100, GamePanel.height - 100, 100, 50);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(this);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            securityLbl.setFont(font.deriveFont(Font.BOLD, 150));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));
            newpassLbl.setFont(font.deriveFont(Font.BOLD, 50));
            confnewpassLbl.setFont(font.deriveFont(Font.BOLD, 50));
            notifLbl.setFont(font.deriveFont(Font.PLAIN, 30));

        } catch (Exception e) {}

        add(securityLbl);
        add(passLbl);
        add(newpassLbl);
        add(confnewpassLbl);
        add(notifLbl);

        add(confirmBtn);
        add(deleteBtn);
        add(backBtn);

        input();

    }

    public void input(){
        passPF = new JPasswordField();
        passPF.setBounds(GamePanel.width/2,210, GamePanel.width/2 - GamePanel.width/6,50);
        passPF.setOpaque(false);
        passPF.setMargin(new Insets(0, 10,0,0));
        passPF.setBorder(new LineBorder(Color.white,3));
        passPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        newpassPF = new JPasswordField();
        newpassPF.setBounds(GamePanel.width/2,350, GamePanel.width/2 - GamePanel.width/6,50);
        newpassPF.setOpaque(false);
        newpassPF.setMargin(new Insets(0, 10,0,0));
        newpassPF.setBorder(new LineBorder(Color.white,3));
        newpassPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        confnewpassPF = new JPasswordField();
        confnewpassPF.setBounds(GamePanel.width/2,420, GamePanel.width/2 - GamePanel.width/6,50);
        confnewpassPF.setOpaque(false);
        confnewpassPF.setMargin(new Insets(0, 10,0,0));
        confnewpassPF.setBorder(new LineBorder(Color.white,3));
        confnewpassPF.setFont(new Font("Courier New", Font.PLAIN, 20));

        this.add(passPF);
        this.add(newpassPF);
        this.add(confnewpassPF);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn){
            if( passPF.getPassword().length == 0 || newpassPF.getPassword().length == 0 || confnewpassPF.getPassword().length == 0){
                notifLbl.setText("Please   fill   all   the   required   fields");

                if(passPF.getPassword().length == 0){
                    passPF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    passPF.setBorder(new LineBorder(Color.white,3));
                }

                if(newpassPF.getPassword().length == 0){
                    newpassPF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    newpassPF.setBorder(new LineBorder(Color.white,3));
                }

                if(confnewpassPF.getPassword().length == 0){
                    confnewpassPF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    confnewpassPF.setBorder(new LineBorder(Color.white,3));
                }

            }else {
                try {
                    String qUser = "SELECT * FROM userTable WHERE user_Name = '" + Login.getUname() + "';";
                    ResultSet rs = con1.s.executeQuery(qUser);
                    if (rs.next()) {
                        String pSalt = rs.getString("pass_salt");
                        String pHash = rs.getString("pass_hash");

                        try {
                            boolean matched = CRUD.validatePassword(Arrays.toString(passPF.getPassword()), iterations + ":" + pSalt + ":" + pHash);
                            System.out.println(matched);

                            if (matched){
                                if(Arrays.equals(newpassPF.getPassword(), confnewpassPF.getPassword())){
                                    // new password
                                    newpassPF.setBorder(new LineBorder(Color.white,3));
                                    confnewpassPF.setBorder(new LineBorder(Color.white,3));
                                    try {
                                        generatedSecuredPasswordHash = CRUD.doHashing(Arrays.toString(newpassPF.getPassword()));
                                        String[] parts = generatedSecuredPasswordHash.split(":");
                                        passSalt = parts[1];
                                        passHash = parts[2];
                                    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    CRUD.updatePassword(con1, Login.getUname(), passSalt, passHash);
                                    JOptionPane.showConfirmDialog(null, "Please login again", "Login", JOptionPane.DEFAULT_OPTION);
                                    Login.loggedIn = false;
                                    Login.setUname("");
                                    try {
                                        CRUD.logIn(con1, Login.getUname(), false);
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    if (gsm.isStateActive(GameStateManager.TITLE)){
                                        GameStateManager.pop(GameStateManager.TITLE);
                                    }else{
                                        gsm.add(GameStateManager.TITLE);
                                    }
                                    Window.window.setVisible(true);
                                    this.dispose();
                                }else if (!Arrays.equals(newpassPF.getPassword(), confnewpassPF.getPassword())){
                                    System.out.println("Passwords do not match");
                                    notifLbl.setText("Passwords   do   not   match");
                                    newpassPF.setBorder(new LineBorder(Button.borderdarkred,3));
                                    confnewpassPF.setBorder(new LineBorder(Button.borderdarkred,3));
                                }
                            }else if (!matched){
                                System.out.println("Password does not match");
                                notifLbl.setText("Password   does   not   seem   to   match   password   in   database");
                                passPF.setBorder(new LineBorder(Button.borderdarkred,3));
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else{
                        notifLbl.setText("Can't   find   user   in   database");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        }
        if(e.getSource() == deleteBtn){
            if(passPF.getPassword().length == 0){
                notifLbl.setText("Please   input   your   password");
                passPF.setBorder(new LineBorder(Button.borderdarkred,3));
            }else{
                try{
                    String qUser = "SELECT * FROM userTable WHERE user_Name = '" + Login.getUname() + "';";
                    ResultSet rs = con1.s.executeQuery(qUser);
                    if (rs.next()) {
                        String pSalt = rs.getString("pass_salt");
                        String pHash = rs.getString("pass_hash");

                        try {
                            boolean matched = CRUD.validatePassword(Arrays.toString(passPF.getPassword()), iterations + ":" + pSalt + ":" + pHash);
                            System.out.println(matched);

                            if (matched){
                                JOptionPane.showConfirmDialog(null, "Sea you soon T^T", "Delete Account", JOptionPane.DEFAULT_OPTION);
                                CRUD.deleteUser(con1, Login.getUname());
                                Login.setUname(null);
                                Login.loggedIn = false;
                                if (gsm.isStateActive(GameStateManager.TITLE)){
                                    GameStateManager.pop(GameStateManager.TITLE);
                                }else{
                                    gsm.add(GameStateManager.TITLE);
                                    Window.window.setVisible(true);
                                }
                                this.dispose();
                            }else if (!matched){
                                System.out.println("Password does not match");
                                notifLbl.setText("Password   does   not   seem   to   match   password   in   database");
                                passPF.setBorder(new LineBorder(Button.borderdarkred,3));
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else{
                        notifLbl.setText("Can't   find   user   in   database");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
        if(e.getSource() == backBtn){
            if (gsm.isStateActive(GameStateManager.SETTINGS)){
                GameStateManager.pop(GameStateManager.SETTINGS);

            }else{
                gsm.add(GameStateManager.SETTINGS);
                Window.window.setVisible(true);
                this.dispose();
            }
        }
    }
}
