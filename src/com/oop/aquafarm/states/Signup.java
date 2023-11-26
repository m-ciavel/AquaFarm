package com.oop.aquafarm.states;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.CRUD;
import com.oop.aquafarm.util.dbConnection;



import javax.swing.*;
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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class Signup extends JFrame implements ActionListener {
    private final GameStateManager gsm;
    dbConnection con1;
    private final JLabel signupLbl;
    private final JLabel unameLbl;
    private final JLabel passLbl;
    private final JLabel confpassLbl;
    private final JLabel ageLbl;
    private final JLabel notifLbl;
    private JTextField unameTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private final JButton signupBtn;
    private final JButton loginBtn;
    private final JButton backBtn;

    private String uname;
    static String generatedSecuredPasswordHash = null;
    static String passSalt, passHash;
    private Date created_date;
    private int uid, age, dbID;

    ImageIcon backBtnIcon = new ImageIcon("res/menubutton/arrow back.png");

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

        notifLbl = new JLabel("",  SwingConstants.CENTER);
        notifLbl.setBounds(0,480, 1000,30);
        notifLbl.setForeground(Button.borderdarkred);

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

        backBtn = new JButton();
        backBtn.setIcon(backBtnIcon);
        backBtn.setBounds(GamePanel.width - 100, GamePanel.height - 100, 100, 50);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(this);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            signupLbl.setFont(font.deriveFont(Font.BOLD, 150));
            unameLbl.setFont(font.deriveFont(Font.BOLD, 50));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));
            confpassLbl.setFont(font.deriveFont(Font.BOLD, 50));
            ageLbl.setFont(font.deriveFont(Font.BOLD, 50));
            notifLbl.setFont(font.deriveFont(Font.PLAIN, 30));

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
        add(notifLbl);

        add(signupBtn);
        add(loginBtn);
        add(backBtn);

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
            if(unameTF.getText().isEmpty() || passPF.getPassword().length == 0 || passPF.getPassword().length == 0 || ageTF.getText().isEmpty()){
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

                if(confpassPF.getPassword().length == 0){
                    confpassPF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    confpassPF.setBorder(new LineBorder(Color.white,3));
                }

                if(ageTF.getText().isEmpty()){
                    ageTF.setBorder(new LineBorder(Button.borderdarkred,3));
                }else {
                    ageTF.setBorder(new LineBorder(Color.white,3));
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
                            dbID++;
//                            System.out.println(dbID);
                        }

                        // get values
                        try {
                            // password
                            try {
                                generatedSecuredPasswordHash = CRUD.doHashing(Arrays.toString(passPF.getPassword()));
//                                String[] parts = doHashing(Arrays.toString(passPF.getPassword())).split(":");
                                String[] parts = generatedSecuredPasswordHash.split(":");
                                passSalt = parts[1];
                                passHash = parts[2];
//                                System.out.println(generatedSecuredPasswordHash); System.out.println(passSalt); System.out.println(passHash);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                                throw new RuntimeException(ex);
                            }
                            uname = unameTF.getText();
                            String qUser = "SELECT * FROM userTable WHERE user_Name = '" + uname + "';";
                            ResultSet rsUser = con1.s.executeQuery(qUser);
                            if (rsUser.next()){
                                notifLbl.setText("Username   already   taken");
                                unameTF.setBorder(new LineBorder(Button.borderdarkred,3));
                            }else{
                                uid = (int) CRUD.generateUID();
                                boolean uidExists = false;
                                String quid = "SELECT * FROM users WHERE user_ID = '" + uid + "';";
                                ResultSet rsuid = con1.s.executeQuery(quid);
                                if (rsuid.next()){
                                    do{
                                        uid = (int) CRUD.generateUID();
                                        rsuid = con1.s.executeQuery(quid);
                                        uidExists = rsuid.next();
                                    }while(uidExists);
                                    if(!uidExists){
                                        unameTF.setBorder(new LineBorder(Color.white,3));
                                        age = Integer.parseInt(ageTF.getText());
                                        created_date = new java.sql.Date(System.currentTimeMillis());
                                        CRUD.createUser(dbID, uid, uname, age, passSalt, passHash, created_date);
                                    }
                                }else{
                                    unameTF.setBorder(new LineBorder(Color.white,3));
                                    age = Integer.parseInt(ageTF.getText());
                                    created_date = new java.sql.Date(System.currentTimeMillis());
                                    CRUD.createUser(dbID, uid, uname, age, passSalt, passHash, created_date);
                                    this.dispose();
                                    new Login(gsm).setVisible(true);
                                }

                            }


                        } catch (NumberFormatException nfe){
                            ageTF.setText("");
                            notifLbl.setText("Please   enter   a   valid   number   for   age");
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
                    notifLbl.setText("Passwords   do   not   match");
                }
            } // blank text fields

        }

        if(e.getSource() == loginBtn){
            this.dispose();
            new Login(gsm).setVisible(true);
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

}
