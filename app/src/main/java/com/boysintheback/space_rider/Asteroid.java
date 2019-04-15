package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Random;

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
        Matrix matrix = new Matrix();
        matrix.postRotate(1);
        this.image = Bitmap.createBitmap(this.image,0,0, this.image.getWidth(), this.image.getHeight(), matrix, true);
        y += speed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
