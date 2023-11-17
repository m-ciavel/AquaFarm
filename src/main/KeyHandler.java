package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;


public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<Key>();

    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key(){
            keys.add(this);
        }

        public void toggle(boolean pressed){
            if(pressed !=down){
                down = pressed;
            }
            if(pressed){
                presses++;
            }
        }

        public void tick(){
            if(absorbs < presses){
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }

    }

    public Key enter = new Key();
    public Key escape = new Key();
    public Key p = new Key();
    public Key key1, key2, key3, key4, key5, key6 = new Key();

    public KeyHandler(GamePanel gamePanel){
        gamePanel.addKeyListener(this);
    }


    public void releaseAll(){
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick(){
        for (Key key : keys) {
            key.tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            enter.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            escape.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            p.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_1){
            key1.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            key2.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            key3.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
            key4.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
            key5.toggle(pressed);
        }
        if(e.getKeyCode() == KeyEvent.VK_6){
            key6.toggle(pressed);
        }


    }
    @Override
    public void keyTyped(KeyEvent e) {
        //nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }

}