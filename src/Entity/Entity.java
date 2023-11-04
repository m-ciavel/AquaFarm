package Entity;

import java.awt.image.BufferedImage;
import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int x , y, initialX, initialY;
    public int dragX, dragY;
    public BufferedImage hand1, hand2, fish_left, fish_right ;
    public boolean clicked; 
    public String direction;
    public String fish_type;
    public String add_fishes;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
}
