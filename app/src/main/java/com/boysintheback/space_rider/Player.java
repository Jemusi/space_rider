package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;

import java.util.ArrayList;

public class Player {

    private boolean progress;
    private float x;
    private float y;
    private double speed = 30;
    public Bitmap image;
    private int angle = 0 ;
    private ArrayList<GameObject> objects;
    private Point sizeOfScreen;
    private boolean dead;
    private int fuelPercent;


    public Player(Bitmap bmp, Point p) {
        Bitmap resizedBMP = Bitmap.createScaledBitmap(bmp, 128, 128, false);
        image = resizedBMP;
        this.x = p.x/2;
        this.y = p.y/2;
        progress = true;
        sizeOfScreen = p;
        dead = false;
        fuelPercent = 100;
    }

    public void updateObjects(ArrayList<GameObject> objects){
        this.objects = objects;
    }

    public void draw(Canvas c) {

      //  c.drawBitmap(image, x, y, null);
    }

    public void update(boolean left, boolean holding) {
        x += speed * Math.sin(Math.toRadians(angle));
        if (left && holding){
            if (angle != -60){
                angle -= 5;
                if (y < sizeOfScreen.y*3/4) {
                    y += 3;
                }
            }
        } else if (holding){
            if (angle != 60){
                angle += 5;
                if (y < sizeOfScreen.y*3/4) {
                    y += 3;
                }
            }
        } else if (y > sizeOfScreen.y/2){
            y-=5;
        }
        if (x < -100 || x > sizeOfScreen.x + 100 || fuelPercent <= 0) {
            dead = true;
        }

    }

//    public void turnRight() {
//        if (angle > 79) {
//        } else {
//            angle += 20;
//        }
//    }
//
//    public void turnLeft() {
//        if (angle < -79 ) {
//        } else {
//            angle -= 20;
//        }
//    }

    public void render(Canvas c) {
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, image.getWidth()/2, image.getHeight()/2);
        c.translate(x, y) ;
        c.drawBitmap(image, matrix, null);
        c.translate(-x,-y);
    }


    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean returnStatus() {
        return dead;
    }

    public void decFuel() {
        fuelPercent -= 10;
    }

    public int getFuel() {
        return fuelPercent;
    }

}
