package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    private boolean progress;
    private int x;
    private int y;
    private static int speed = 10;
    private Bitmap image;


    public Player(Bitmap bmp, int x, int y) {
        Bitmap resizedBMP = Bitmap.createScaledBitmap(bmp, 128, 128, false);
        image = resizedBMP;
        this.x = x;
        this.y = y;
        progress = true;
    }

    public void draw(Canvas c) {
        c.drawBitmap(image, x, y, null);
    }

    public void update() {

    }
}
