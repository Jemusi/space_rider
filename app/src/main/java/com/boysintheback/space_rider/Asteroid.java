package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Asteroid extends GameObject {

    private Bitmap image;


    public Asteroid(int x, int y, int speed, Bitmap bmp) {
        super(x,y,speed);
        image = bmp;
    }

    public void render(Canvas c) {
        c.drawBitmap(image,x,y,null);
    }

    public void update() {
        y += speed;
    }
}
