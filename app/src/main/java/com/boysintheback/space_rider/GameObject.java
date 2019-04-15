package com.boysintheback.space_rider;

import android.graphics.Canvas;

public abstract class GameObject {
    int x;
    int y;
    int speed;

    public GameObject(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public abstract void render(Canvas c);

    public abstract void update();

    public abstract int getX();

    public abstract int getY();
}
