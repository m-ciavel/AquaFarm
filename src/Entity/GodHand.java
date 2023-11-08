package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.MouseInput;
import main.UtilityTool;

public class GodHand extends Entity {
    private final MouseInput mouseIn;


    public GodHand(MouseInput mouseIn, GamePanel gp) {
        super(gp);
        this.mouseIn = mouseIn;
        this.gp = gp;
        setDefaultValues();
        loadGodHandImages();
    }

    public void setDefaultValues() {
        clicked = false;
    }

    private void loadGodHandImages() {
        hand1 = setup("hand.open");
        hand2 = setup("hand.close");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage HandImage = null;

        try {

            HandImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hand/" + imageName + ".png")));
            HandImage = uTool.scaledImage(HandImage, gp.tilesize, gp.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HandImage;
    }

    public void update() {
        clicked = mouseIn.mousePressed;
    }

    public void draw(Graphics2D g2) {
        int imageX = mouseIn.x - gp.tilesize / 2;
        int imageY = mouseIn.y - gp.tilesize / 2;

        BufferedImage image = clicked ? hand2 : hand1;

        if (image != null) {
            g2.drawImage(image, imageX, imageY, null);
        }
    }
}
