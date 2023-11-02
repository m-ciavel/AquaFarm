package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.MouseInput;

public class GodHand extends Entity {
	
	

    GamePanel gp;
    MouseInput mouseIn;
    BufferedImage hand1, hand2;
    

    public GodHand(MouseInput mouseIn, GamePanel gp) {
        this.mouseIn = mouseIn;
        this.gp = gp;
        setDefaultValues();
        getGodHandImage();
        loadGodHandImages();
    }

    private void loadGodHandImages() {
        try {
            hand1 = ImageIO.read(getClass().getResourceAsStream("/hand/hand.open.png"));
            hand2 = ImageIO.read(getClass().getResourceAsStream("/hand/hand.close.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void setDefaultValues() {
        clicked = "no";
    }

    public void getGodHandImage() {
        try {
            hand1 = ImageIO.read(getClass().getResourceAsStream("/hand/hand.open.png"));
            hand2 = ImageIO.read(getClass().getResourceAsStream("/hand/hand.close.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (mouseIn.mouseClicked) {
            clicked = "yes";
            
        } else {
            clicked = "no";
        }
        if (!mouseIn.mouseClicked) {
            clicked = "no";
        }

    }

    public void draw(Graphics2D g2) {
        int imageX = mouseIn.x - gp.tilesize / 2;
        int imageY = mouseIn.y - gp.tilesize / 2;

        BufferedImage image = null;

        switch (clicked) {
            case "yes":
                image = hand2;
                break;
            case "no":
                image = hand1;
                break;
                
        }

        if (image != null) {
            g2.drawImage (image, imageX, imageY, gp.tilesize, gp.tilesize,null );
        }
    }
}