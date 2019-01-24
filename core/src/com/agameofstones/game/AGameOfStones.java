package com.agameofstones.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AGameOfStones extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "A Game Of Stones";
	SpriteBatch batch;
	public enum Options {
		RANDOM,
		NORMAL
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		GameAssetLoader.loadAssets();
		GameAssetLoader.setAssets();
        //TODO: set to main menu screen
        this.setScreen(new GameScreen(this, Options.NORMAL));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
