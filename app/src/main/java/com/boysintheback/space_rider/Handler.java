package com.boysintheback.space_rider;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Handler {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Player playerShip;

    public Handler(Player spaceship){
        this.playerShip = spaceship;
    }

    public void addObject(GameObject go) {
        objects.add(go);
    }

    public void renderObjects(Canvas c) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(c);
        }
    }

    public void updateObjects() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
    }
}
