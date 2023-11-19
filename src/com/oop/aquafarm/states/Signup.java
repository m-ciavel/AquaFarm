package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Signup extends JFrame {
//    protected GameStateManager gsm;
    private JLabel signupLbl;
    private JLabel unameLbl, passLbl, confpassLbl, emailLbl, ageLbl;
    private JTextField unameTF, emailTF, ageTF;
    private JPasswordField passPF, confpassPF;
    private JButton signupBtn, clearBtn, loginBtn;

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
        signupLbl.setBounds((GamePanel.width - signupLbl.getWidth())/3,GamePanel.height/7, GamePanel.width,GamePanel.height/8);

        unameLbl = new JLabel("Username");
        unameLbl.setBounds((GamePanel.width - unameLbl.getWidth())/6,230, 1000,60);

        passLbl = new JLabel("Password");
        passLbl.setBounds((GamePanel.width - passLbl.getWidth())/6,295, 1000,60);

        confpassLbl = new JLabel("Confirm Password");
        confpassLbl.setBounds((GamePanel.width - confpassLbl.getWidth())/6,360, 1000,60);

        ageLbl = new JLabel("Age");
        ageLbl.setBounds((GamePanel.width - ageLbl.getWidth())/6,425, 1000,60);

        emailLbl = new JLabel("Email");
        emailLbl.setBounds((GamePanel.width - emailLbl.getWidth())/6,490, 1000,60);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/pixelated.ttf"));

            signupLbl.setFont(font.deriveFont(Font.BOLD, 150));
            unameLbl.setFont(font.deriveFont(Font.BOLD, 50));
            passLbl.setFont(font.deriveFont(Font.BOLD, 50));
            confpassLbl.setFont(font.deriveFont(Font.BOLD, 50));
            ageLbl.setFont(font.deriveFont(Font.BOLD, 50));
            emailLbl.setFont(font.deriveFont(Font.BOLD, 50));

            unameTF.setFont(font.deriveFont(Font.BOLD, 45));
            passPF.setFont(font.deriveFont(Font.BOLD, 45));
            confpassPF.setFont(font.deriveFont(Font.BOLD, 45));
            ageTF.setFont(font.deriveFont(Font.BOLD, 45));
            emailTF.setFont(font.deriveFont(Font.BOLD, 45));

        } catch (Exception e) {}

        add(signupLbl);
        add(unameLbl);
        add(passLbl);
        add(confpassLbl);
        add(ageLbl);
        add(emailLbl);

        input();
    }

    public void input(){
        unameTF = new JTextField();
        unameTF.setBounds(GamePanel.width/2,230, GamePanel.width/2 - GamePanel.width/6,50);
        unameTF.setOpaque(false);
        unameTF.setMargin(new Insets(0, 10,0,0));
        unameTF.setBorder(new LineBorder(Color.white,3));

        passPF = new JPasswordField();
        passPF.setBounds(GamePanel.width/2,295, GamePanel.width/2 - GamePanel.width/6,50);
        passPF.setOpaque(false);
        passPF.setMargin(new Insets(0, 10,0,0));
        passPF.setBorder(new LineBorder(Color.white,3));

        confpassPF = new JPasswordField();
        confpassPF.setBounds(GamePanel.width/2,360, GamePanel.width/2 - GamePanel.width/6,50);
        confpassPF.setOpaque(false);
        confpassPF.setMargin(new Insets(0, 10,0,0));
        confpassPF.setBorder(new LineBorder(Color.white,3));

        ageTF = new JTextField();
        ageTF.setBounds(GamePanel.width/2,425, GamePanel.width/2 - GamePanel.width/6,50);
        ageTF.setOpaque(false);
        ageTF.setMargin(new Insets(0, 10,0,0));
        ageTF.setBorder(new LineBorder(Color.white,3));

        emailTF = new JTextField();
        emailTF.setBounds(GamePanel.width/2,490, GamePanel.width/2 - GamePanel.width/6,50);
        emailTF.setOpaque(false);
        emailTF.setMargin(new Insets(0, 10,0,0));
        emailTF.setBorder(new LineBorder(Color.white,3));

        this.add(unameTF);
        this.add(passPF);
        this.add(confpassPF);
        this.add(ageTF);
        this.add(emailTF);



    }


//    public void update(double time){
//
//    };
//    public void input(MouseHandler mouseIn, KeyHandler keyh){
//
//    };
//    public void render(Graphics2D g){
//        BufferedImage background  = null;
//        background = SpriteSheet.paintbg(background);
//        g.drawImage(background, 0, 0, null);
//    };
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
