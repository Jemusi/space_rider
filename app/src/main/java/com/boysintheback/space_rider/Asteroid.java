package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Asteroid extends GameObject {

    private Bitmap image;


    public Asteroid(int x, int y, int speed, Bitmap bmp) {
        super(x,y,speed, "Asteroid");
        image = bmp;
    }

    public void render(Canvas c) {
        c.drawBitmap(image,x,y,null);
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

    public String getType() {
        return this.type;
    }
}
