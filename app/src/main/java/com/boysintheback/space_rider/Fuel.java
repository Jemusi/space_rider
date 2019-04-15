package com.boysintheback.space_rider;

import android.graphics.Canvas;

public class Fuel extends GameObject {

    public Fuel(int x, int y, int speed) {
        super(x, y, speed, "Fuel");
    }

    public void render(Canvas c) {

    }

    public void update() {

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getType() {
        return this.type;
    }
}
