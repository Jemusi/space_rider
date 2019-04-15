package com.boysintheback.space_rider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BGStar extends GameObject {

    public BGStar(int x, int y, int speed) {
        super(x,y,speed, "Star");
    }

    public void render(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawCircle(x, y, 5, p);
    }

    public void update() {
        y += speed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void incSpeed(int speed) {
        this.speed = speed;
    }

    public String getType() {
        return this.type;
    }
}
