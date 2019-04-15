package com.boysintheback.space_rider;

import android.graphics.Canvas;

public abstract class GameObject {
    int x;
    int y;
    int speed;
    String type;

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
}
