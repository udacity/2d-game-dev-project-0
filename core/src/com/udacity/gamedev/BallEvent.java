package com.udacity.gamedev;

import com.badlogic.gdx.math.Vector2;

public enum BallEvent {
    OFFSCREEN(new Vector2(0,0));

    private Vector2 position;

    BallEvent(Vector2 vec) {
        this.position = vec;
    }

    public void setPosition(Vector2 vec) {
        this.position = vec;
    }

    public Vector2 position() {
        return position;
    }

}
