package com.boysintheback.space_rider;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public int screenX = 1050;
    public int screenY = 2500;
    public int seconds = 0;
    long startTime;
    public boolean alreadyDone = false;

    private Player spaceship = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.spaceship), screenX/2, 1500);

    public Handler handler = new Handler(spaceship);

    private MainThread thread;


    public GamePanel(Context context) {
        super(context);
        startTime = System.nanoTime();

    getHolder().addCallback(this);

    thread = new MainThread(getHolder(), this);

    setFocusable(true);
}
@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

}

@Override
public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
//        spaceship = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.spaceship), screenX/2, 1500);
}

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
}

@Override
public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
}

public void update() {
        if ((System.nanoTime() - startTime)%0.75 == 0) {
            Random r = new Random();
            int nextStar = r.nextInt(screenX);
            handler.addObject(new BGStar(nextStar, 0, 5));

        }
        if (seconds % 10 == 0){
            if (alreadyDone == false) {
                Random r = new Random();
                int nextObject = r.nextInt(screenX);
                handler.addObject(new Asteroid(nextObject, 0, 10, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_m)));
                alreadyDone = true;
            }
        }
    handler.updateObjects();
}

public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null){
            canvas.drawColor(Color.BLACK);
            spaceship.draw(canvas);
            handler.renderObjects(canvas);
        }


}
}
