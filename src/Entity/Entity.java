package Entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;


    public BufferedImage hand1, hand2, fish_left, fish_right ;
    public boolean clicked;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }
}
