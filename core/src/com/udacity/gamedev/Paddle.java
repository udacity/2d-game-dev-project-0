package com.udacity.gamedev;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Paddle {
    static String TAG = Paddle.class.toString();

    //For the game to work correctly, the position vector must represent the Paddle's bottom left
    //corner.
    Vector2 position;
    Viewport viewport;
    boolean isComputerPaddle;

    public Paddle(Viewport viewport, boolean isComputerPaddle) {
        this.position = new Vector2(0,0);
        this.viewport = viewport;
        this.isComputerPaddle = isComputerPaddle;
    }

    // TODO Implement render method
    //Render the Paddle with the bottom left corner at the position vector, and with the width,
    //height, and appropriate color value from the Constants class. See the .render() method of
    //the Ball class for inspiration.

    // TODO Implement update method
    //Update the position of the paddle, taking into account user input.
    //HINT: you can use the static method Gdx.input.isKeyPressed() to determine whether a key on the keyboard is
    //pressed.  As an argument, it takes an instance of the Keys enum.
    //For example, this method call would return true when the up arrow key is pressed:
    //Gdx.input.isKeyPressed(Keys.UP)
    //More info: https://github.com/libgdx/libgdx/wiki/Input-handling

    // TODO Implement doCollisions method
    //Detect collision with the ball, and have the ball respond appropriately.
    //HINT: there are methods in the Ball class for reversing its direction.

}
