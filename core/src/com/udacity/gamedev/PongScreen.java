package com.udacity.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PongScreen implements Screen, BallEventListener {
    static String TAG = PongScreen.class.toString();

    // Scoring
    int humanScore = 0;
    int computerScore = 0;
    int pointsPlayed = 0;
    int maxPoints = 4;          // Play to best of 5
    boolean isGameOver = false;

    ScreenViewport viewport;
    SpriteBatch batch;
    ShapeRenderer renderer;
    BitmapFont font;
    BitmapFont gameOverFont;

    Ball ball;
    Paddle humanPaddle;
    ComputerPaddle computerPaddle;

    public PongScreen() {
        viewport = new ScreenViewport();
        renderer = new ShapeRenderer();

        ball = new Ball(viewport);
        ball.setEventListener(this);

        // TODO Instantiate the human paddle.
        // TODO Instantiate the computer paddle.
        // TODO Uncomment this line once you instantiate the computer paddle.
        //computerPaddle.ball = ball;

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("header.fnt"));
        gameOverFont = new BitmapFont(Gdx.files.internal("gameover.fnt"));

        renderer.setAutoShapeType(true);
    }

    /**
     * Checks for user input to reset the position of the ball or start a new game.
     */
    void handleInput() {
        if (Gdx.input.isKeyPressed(Keys.R))
            ball.resetPosition();
        if (Gdx.input.isKeyPressed(Keys.N)) {
            // New game
            isGameOver = false;
            humanScore = 0;
            computerScore = 0;
            pointsPlayed = 0;
            ball.resetPosition();
        }
    }

    @Override
    public void render(float delta) {
        // Handle input
        handleInput();

        // Do collision detection
        // TODO Do collisions between the ball and human paddle.
        // TODO Do collisions between the ball and computer paddle.
        //Hint: make the doCollisions Paddle method take a Ball parameter.

        // Update objects
        ball.update(delta);
        // TODO Update the human paddle.
        // TODO Update the computer paddle.

        Color bg = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin();

        // Divider line
        renderer.rect(0f, viewport.getScreenHeight(), 1f, -viewport.getScreenHeight() * 2);

        // Render objects
        ball.render(renderer);
        // TODO Render the human paddle.
        // TODO Render the computer paddle.

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Draw text
        font.draw(batch, "Human Score", -viewport.getScreenWidth()/4f - 100, 200f);
        font.draw(batch, ""+humanScore, -viewport.getScreenWidth()/4f - 50, 150f);
        font.draw(batch, "Computer Score", viewport.getScreenWidth()/4f - 75 , 200f);
        font.draw(batch, ""+computerScore, viewport.getScreenWidth()/4f - 25, 150f);

        // Check for game end
        if (isGameOver) {
            if (humanScore > computerScore)
                gameOverFont.draw(batch, "YOU WIN!", -150f, 0f);
            else gameOverFont.draw(batch, "YOU LOSE!", -150f, 0f);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        // TODO uncomment these lines once you instantiate the paddles.
        /*
        humanPaddle.position = new Vector2(-width / 2f, humanPaddle.position.y);
        computerPaddle.position = new Vector2(width / 2f - Constants.PADDLE_WIDTH, computerPaddle.position.y);
        computerPaddle.updateClippingLine();
        */
    }

    @Override
    public void show() { }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
    }

    @Override
    public void handleBallEvent(BallEvent event) {
        if (pointsPlayed == maxPoints) {
            isGameOver = true;
            return;
        }

        Vector2 pos = event.position();

        if (pos.x < 0) {
            computerScore++;
        } else {
            humanScore++;
        }
        pointsPlayed++;
        ball.resetPosition();
    }

    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

}
