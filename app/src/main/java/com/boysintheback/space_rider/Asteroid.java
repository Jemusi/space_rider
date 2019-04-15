package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Random;

public class Asteroid extends GameObject {

    private Bitmap image;
    private int angle = 0;

    public Asteroid(int x, int y, int speed, Bitmap bmp) {
        super(x,y,speed);
        image = bmp;

    }

    public void render(Canvas c) {
        angle+=5;
        if (angle > 360) {
            angle = 0;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, image.getWidth()/2, image.getHeight()/2);
        c.translate(x,y);
        c.drawBitmap(image, matrix,null);
        c.translate(-x,-y);

    }

    public void update() {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(1);
//        this.image = Bitmap.createBitmap(this.image,0,0, this.image.getWidth(), this.image.getHeight(), matrix, true);
        y += speed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
