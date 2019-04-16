package com.boysintheback.space_rider;

import android.graphics.Canvas;

public abstract class GameObject {
    int x;
    int y;
    double speed;
    double recordedSpeed;
    String type;
    private boolean recordSpeed = true;

    public GameObject(int x, int y, int speed, String type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
    }

    public abstract void render(Canvas c);

    public abstract void update();

    public abstract int getX();

    public abstract int getY();

    public abstract String getType();

    public void slowDown(){
        if (recordSpeed){
            recordedSpeed = speed;
            recordSpeed = false;
        }
        speed = 0.8*speed;
    }

    public void speedUp(){
        if (speed < recordedSpeed){
            speed = 1.25*speed;
        } else recordSpeed = true;
    }

}
