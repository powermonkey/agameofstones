package com.agameofstones.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AGameOfStones extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "AGameOfStones";
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		GameAssetLoader.load();
		GameAssetLoader.getLoadedAssets();
        //TODO: set to main menu screen
        this.setScreen(new GameScreen(this));
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
