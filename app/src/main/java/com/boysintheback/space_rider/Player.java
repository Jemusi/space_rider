package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    private int x;
    private int y;
    private static int speed = 10;
    private Bitmap image;


    public Player(Bitmap bmp, int x, int y) {
        image = Bitmap.createScaledBitmap(bmp,72,72, true);
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas c) {
        c.drawBitmap(image, x, y, null);
    }

    public void update() {

    }
}
