package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Rod on 4/17/2018.
 */

public class GameScreen implements Screen {
    final AGameOfStones game;
    Grid grid;
    OrthographicCamera cam;

    public GameScreen(final AGameOfStones gam) {
        this.game = gam;
        grid = new Grid(game);
        cam = new OrthographicCamera();
        cam.setToOrtho(false,AGameOfStones.WIDTH, AGameOfStones.HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(cam.combined);
        grid.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
