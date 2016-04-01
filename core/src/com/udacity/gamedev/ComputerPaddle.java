package com.udacity.gamedev;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ComputerPaddle extends Paddle {

    //Percentage of the right side of the screen that the computer player can "see," determines
    //the position of the viewClippingLine.  Make this value smaller to make the computer player
    //easier, and make it larger to make the computer player harder.
    static float viewClippingRatio = 0.75f;

    Ball ball;
    //The clipping line represents how far past the center line the ball must be before the computer
    //paddle "sees" it and starts moving towards it.
    private float viewClippingLine;

    public ComputerPaddle(Viewport viewport) {
        super(viewport, true);
    }

    // TODO Uncomment @Override when you finish the Paddle class.
    //@Override
    void update(float delta) {
        if (isComputerPaddle) {
            Vector2 ballPosition = ball.position;

            int random = (int)Math.floor(Math.random() * 10);

            //The computer only moves on certain frames.  Make upper bound on random larger to make
            //the computer easier by moving on fewer frames, and make the upper bound smaller to
            //make the computer harder by moving on more frames.
            if (random < 5)
                return;

            float targetY = (ballPosition.x > viewClippingLine) ? ballPosition.y : 0;
            targetY -= Constants.PADDLE_HEIGHT / 2;

            float motion = delta * Constants.PADDLE_SPEED;
            motion *= (targetY >= this.position.y) ? 1 : -1;
            position.y = Math.abs(targetY - position.y) < Math.abs(motion) ? targetY : position.y + motion;
            position.y = Math.min(position.y, viewport.getWorldHeight() / 2 - Constants.PADDLE_HEIGHT);
            position.y = Math.max(position.y, -viewport.getWorldHeight() / 2);
        }
    }

    /**
     * Updates the position of the view clipping line based on the width of the screen and the
     * view clipping ratio.
     */
    public void updateClippingLine() {
        viewClippingLine =  (1 - viewClippingRatio) * viewport.getWorldWidth() / 2;
    }

}
