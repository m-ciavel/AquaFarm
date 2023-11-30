package com.oop.aquafarm.util;

import com.oop.aquafarm.entity.Fish;

public class AABB {

    private Vector2f pos;
    private float w;
    private float h;

    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public Vector2f getPos() { return pos; }
    public float getWidth() { return w; }
    public float getHeight() { return h; }

    public boolean inside(int xp, int yp) {
        if(xp == -1 || yp == - 1) return false;

        int wTemp = (int) this.w;
        int hTemp = (int) this.h;
        int x = (int) this.pos.x;
        int y = (int) this.pos.y;

        if(xp < x || yp < y) {
            return false;
        }

        wTemp += x;
        hTemp += y;
        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
    }


}
