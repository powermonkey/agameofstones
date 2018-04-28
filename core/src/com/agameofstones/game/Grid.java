package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Rod on 4/17/2018.
 */

public class Grid {
    private NinePatchDrawable stoneGreen, stoneSquare;
    final AGameOfStones game;
    private Table rootTable, table;
    private Stage stage;
    private Label tiles [][];
    private Label.LabelStyle tileRedStyle;
    private boolean gridField [][];
    private Controls controls;
    private Constants constants;

    public Grid(AGameOfStones gam) {
        this.game = gam;
        constants = new Constants();
        gridField = new boolean[constants.SIZE_W][constants.SIZE_H];
        initTable();
        getAssets();
        loadGrid();
    }

    private void initTable() {
        rootTable = new Table();
        rootTable.setFillParent(true);
        table = new Table();
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT), game.batch);
        tiles = new Label[constants.SIZE_W][constants.SIZE_H];
    }

    private void getAssets() {
        stoneGreen = GameAssetLoader.patchDrawableStoneGreen;
        stoneSquare = GameAssetLoader.patchDrawableStoneSquare;
        tileRedStyle = GameAssetLoader.tileRedStyle;
    }

    private void loadGrid() {
        controls = new Controls(gridField, tiles);
        for(int tileY = (constants.SIZE_H - 1); tileY >= 0; tileY--) {
            for(int tileX = 0; tileX < constants.SIZE_W; tileX++) {
                final int xTile = tileX;
                final int yTile = tileY;
                tiles[xTile][yTile] = new Label(" ", tileRedStyle);
                gridField[xTile][yTile] = false; //unflipped tile
                controls.addFlipListener(xTile, yTile);
                table.add(tiles[xTile][yTile]).center().width(constants.TILE_W).height(constants.TILE_H);
            }
            table.row();
        }
        table.pad(10);
        table.setBackground(stoneSquare);

        rootTable.add(table);
        rootTable.row();
        rootTable.center().center().padBottom(50);
        stage.addActor(rootTable);
        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta){
        stage.act(delta);
        stage.draw();
    }

    private void dispose(){
        stage.dispose();
    }
}
