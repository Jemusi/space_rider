package com.boysintheback.space_rider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;

import static com.boysintheback.space_rider.MainThread.canvas;

public class Score {
    private int score = 0;
    Rect rect = new Rect();
    Paint paint = new Paint();

    public Score( ) {
        paint.setTextSize(150) ;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
    }


    public int getScore() {
        return score;
    }

    public void updateScore() {
        score++;
    }

    public void addToScore() {

    }

    public void render(Canvas c) {
        paint.getTextBounds(Integer.toString(score), 0, Integer.toString(score).length(), rect);
        canvas.drawText(Integer.toString(score), c.getWidth()/2 - rect.exactCenterX()/2, 400, paint);
    }

}
