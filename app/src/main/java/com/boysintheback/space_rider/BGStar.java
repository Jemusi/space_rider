package com.boysintheback.space_rider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BGStar extends GameObject {

    public BGStar(int x, int y, int speed, int pos) {
        super(x,y,speed);
    }

    public void render(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawCircle(x, y, 5, p);
    }

    public void update() {

    }
}
