package com.udacity.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ball {
    public static String TAG = Ball.class.toString();

    enum Direction {
        SE(new Vector2(-1.33f,-1f)),
        SW(new Vector2(1.33f, -1f)),
        NE(new Vector2(-1.33f, 1f)),
        NW(new Vector2(1.33f, 1f));

        private final Vector2 change;

        Direction(Vector2 change) {
            this.change = change;
        }

        public Vector2 value() {return change;}

        /**
         * Get a random direction.
         * @return  Random direction vector.
         */
        static Direction getRandomDirection() {
            int num = (int)Math.floor(Math.random() * Direction.values().length);
            return Direction.values()[num];
        }
    }

    Vector2 position;
    Direction direction;
    Viewport viewport;
    BallEventListener listener;

    public Ball(Viewport viewport) {
        position = new Vector2(0, 0);
        direction = Direction.getRandomDirection();
        this.viewport = viewport;
        Gdx.app.log(TAG, direction.toString());
    }

    /**
     * Draws the ball.
     * @param renderer  ShapeRenderer to draw ball with.
     */
    void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.BALL_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.BALL_RADIUS, 30);
    }

    /**
     * Updates the position of the ball and responds correctly if the ball hits the edge of the
     * screen.
     * @param delta  Time since the previous frame in seconds.
     */
    void update(float delta) {
        checkWallCollisions();
        position.x += (Constants.BALL_SPEED * direction.change.x * delta);
        position.y += (Constants.BALL_SPEED * direction.change.y * delta);
    }

    /**
     * Resets the ball to its initial position.
     */
    void resetPosition() {
        position = new Vector2(0, 0);
        direction = Direction.getRandomDirection();
    }

    /**
     * Reverses the ball's vertical movement.
     */
    void flipNorthSouth() {
        String directionCode = this.direction.name();

        if (directionCode.charAt(0) == 'N') {
            this.direction = Direction.valueOf("S" + directionCode.charAt(1));
        } else {
            this.direction = Direction.valueOf("N" + directionCode.charAt(1));
        }
    }

    /**
     * Reverses the ball's lateral movement.
     */
    void flipEastWest() {
        String directionCode = this.direction.name();

        if (directionCode.charAt(1) == 'E') {
            this.direction = Direction.valueOf(""+directionCode.charAt(0) +"W");
        } else {
            this.direction = Direction.valueOf(""+directionCode.charAt(0) +"E");
        }
    }

    /**
     * Check to see if the ball has hit the screen's bounds, and respond appropriately.
     */
    void checkWallCollisions() {
        int extremity = viewport.getScreenWidth() / 2;

        if ( position.y > viewport.getScreenHeight() / 2f - Constants.BALL_RADIUS)
            flipNorthSouth();

        if (position.y < viewport.getScreenHeight() / -2f + Constants.BALL_RADIUS)
            flipNorthSouth();

        if (position.x < -extremity || position.x > extremity) {
            BallEvent event = BallEvent.OFFSCREEN;
            event.setPosition(this.position);
            listener.handleBallEvent(event);

        }
    }

    void setEventListener(BallEventListener listener) {
        this.listener = listener;
    }

}
