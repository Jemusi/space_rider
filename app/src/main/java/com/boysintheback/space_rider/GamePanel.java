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
    public Point size;
    public int seconds = 0;
    long startTime;
    public boolean alreadyDone = false;
    public boolean alreadyDoneInitial = false;
    public boolean alreadyDoneIncrementingSpeed = false;
    public boolean alreadyDroppedFuel = false;
    public boolean doneReset = false;
    public int scale = 0;
    public int scaleStar = 0;
    private boolean left;
    private boolean holding;
    private MainThread thread;
    public Player spaceship;

    public Handler handler;

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
        spaceship = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.spaceship), size);
        handler = new Handler(spaceship);
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
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_DOWN ){
            holding = true;
            if (event.getX() < size.x/2){
                left = true;
            }
            else left = false;
        }
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP){
            holding = false;
            left = false;
        }
//        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
//            if (event.getX() < size.x/2) {
//                spaceship.turnLeft();
//            } else {
//                spaceship.turnRight();
//            }
//        }
        return true;
}

public void update() {
        // Update movement
        spaceship.updateObjects(handler.getObjects());
        spaceship.update(left,holding);

        // Generate fuel drops
        if (seconds % 3 == 0) {
            if (!alreadyDroppedFuel) {
                Random x = new Random();
                int nextObject = x.nextInt(size.x);
                handler.addObject(new Fuel(nextObject, 0, 20 + scale, BitmapFactory.decodeResource(getResources(), R.drawable.fuel)));
                alreadyDroppedFuel = true;
            }
        }

        // Increase speed of asteroids
        if (seconds % 10 == 0 && seconds != 0 && !alreadyDoneIncrementingSpeed) {
            if (scale < 30) {
                scale = scale + 5;
            }
            if (scaleStar < 20)
                scaleStar = scaleStar + 1;
                updateCurrentStars(5+scaleStar);
                alreadyDoneIncrementingSpeed = true;
        }

        // update for scoreboard

            if (seconds == 0) {
                if (!alreadyDoneInitial) {
                    for (int i = 0; i < 25; ++i) {
                        Random r = new Random();
                        int nextPosX = r.nextInt(size.x);
                        int nextPosY = r.nextInt(size.y);
                        handler.addObject(new BGStar(nextPosX, nextPosY, 5));
                        alreadyDoneInitial = true;
                    }
                }
            }
            if (seconds % 10 == 0) {
            if (!doneReset) {
                startTime = System.nanoTime();
                doneReset = true;
            }
            }

            // Star generating part
            if ((System.nanoTime() - startTime)%13 == 0) {
                Random r = new Random();
                int nextStar = r.nextInt(size.x);
                handler.addObject(new BGStar(nextStar, 0, 5+scaleStar));
            }
            if ((System.nanoTime() - startTime)%13 == 0) {
                handler.updateScore();
            }

            // Asteroid generating part
            Random r = new Random();
            int asteroidFreq = r.nextInt(3) + 3;
            if (seconds % asteroidFreq == 0){
                if (alreadyDone == false) {
                    Random a = new Random();
                    int nextObject = a.nextInt(size.x);
                    handler.addObject(new Asteroid(nextObject, 0, 20 + scale, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_m)));
                    alreadyDone = true;
                }
            }
            handler.updateObjects(this);
}

public void updateCurrentStars(int speed) {
        handler.updateCurrentStars(speed);
}

public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null){
            if (spaceship.returnStatus()) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gameover),size.x/40 - 40, size.y/4, null);
            } else {
                canvas.drawColor(Color.BLACK);
                spaceship.draw(canvas);
                handler.renderObjects(canvas);
            }
        }
}
}
