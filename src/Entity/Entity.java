package Entity;

import java.awt.image.BufferedImage;
import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int x , y, initialX, initialY;
    public int dragX, dragY;
    public BufferedImage hand1, hand2, fish_left, fish_right, food_left, food_right ;
    public boolean clicked, dragged;
    public String direction;
    public String fish_type;
    public String add_fishes;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
}
