package com.udacity.gamedev;

import com.badlogic.gdx.Game;

public class PongGame extends Game {

	@Override
	public void create() {
		setScreen(new PongScreen());
	}
}
