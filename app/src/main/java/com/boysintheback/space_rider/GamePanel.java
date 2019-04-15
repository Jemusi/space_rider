package com.boysintheback.space_rider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public int screenX = 1000;
    public int seconds = 0;
    public Handler handler = new Handler();

    private MainThread thread;

    public GamePanel(Context context) {
        super(context);

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
        handler.updateObjects();
        if (seconds % 1 == 0) {
            Random r = new Random();
            handler.addObject(new BGStar(1050, 500, 500, 500));
        }
}

public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null){
            canvas.drawColor(Color.BLACK);
            handler.renderObjects(canvas);
        }
}
}
