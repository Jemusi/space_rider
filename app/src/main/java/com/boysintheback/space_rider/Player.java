package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;

import java.util.ArrayList;

public class Player {

    private boolean progress;
    private int x;
    private int y;
    private static int speed = 30;
    public Bitmap image;
    private int angle = 0 ;
    private ArrayList<GameObject> objects;
    private Point sizeOfScreen;



    public Player(Bitmap bmp, Point p) {
        Bitmap resizedBMP = Bitmap.createScaledBitmap(bmp, 128, 128, false);
        image = resizedBMP;
        this.x = p.x/2;
        this.y = p.y/2;
        progress = true;
        sizeOfScreen = p;
    }

    public void updateObjects(ArrayList<GameObject> objects){
        this.objects = objects;
    }

    public void draw(Canvas c) {
        c.drawBitmap(image, x, y, null);
    }

    public void update(boolean left, boolean holding) {
        if (left && holding){
            this.x -= speed;
            if (angle != -180){
                angle -= 5;
                y += 3;
            }
            for (GameObject o: objects){
                o.slowDown();
            }
        } else if (holding){
            this.x += speed;
            if (angle != 180){
                angle += 5;
                y += 5;
            }
            for (GameObject o: objects){
                o.slowDown();
            }
        } else if (y > sizeOfScreen.y/2){
            y-=15;
        };

    }

    public void render(Canvas c) {
    }
}
