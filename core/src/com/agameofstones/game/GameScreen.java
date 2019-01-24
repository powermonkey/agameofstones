package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Rod on 4/17/2018.
 */

public class GameScreen implements Screen {
    final AGameOfStones game;
    Grid grid;
    OrthographicCamera cam;
//    public static Array<TextureAtlas.AtlasRegion> bg;
    public TextureAtlas.AtlasRegion bg;

    public GameScreen(final AGameOfStones gam, AGameOfStones.Options optionSelect) {
        this.game = gam;
        bg = GameAssetLoader.bg;
        grid = new Grid(game, optionSelect);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 137, 89 );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, AGameOfStones.WIDTH, AGameOfStones.HEIGHT);
//        game.batch.draw(bg.get(24), 0, 0);
        game.batch.end();
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
