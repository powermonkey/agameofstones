package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Rod on 4/17/2018.
 */

public class Grid {

    private NinePatchDrawable patchDrawableStoneGray, patchDrawableStoneSquare, patchDrawableStoneGreen, patchDrawableStoneRed, patchDrawableStonePink;
    private BitmapFont gameFont;
    final AGameOfStones game;
    private Table rootTable, table;
    private Stage stage;
    private Label tiles [][];
    private Label.LabelStyle tileRedStyle, tileGreenStyle;
    private Controls controls;
    private Constants constants;

    public Grid(AGameOfStones gam) {
        this.game = gam;
        constants = new Constants();
        initTable();
        loadAssets();
        loadGrid();
    }

    private void initTable() {
        rootTable = new Table();
        rootTable.setFillParent(true);
        table = new Table();
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT), game.batch);
        tiles = new Label[constants.SIZE_W][constants.SIZE_H];
    }

    private void loadAssets() {
        TextureAtlas.AtlasRegion stoneGray = GameAssetLoader.stoneGray;
        TextureAtlas.AtlasRegion stoneGraySquare = GameAssetLoader.stonesGraySquare;
        TextureAtlas.AtlasRegion stoneGreen = GameAssetLoader.stoneGreen;
        TextureAtlas.AtlasRegion stoneRed = GameAssetLoader.stoneRed;
        TextureAtlas.AtlasRegion stonePink = GameAssetLoader.stonePink;
        gameFont = GameAssetLoader.gameFont;

        NinePatch patchStoneGray = new NinePatch(stoneGray, 4 ,4 ,4 ,4);
        NinePatch patchStoneGraySquare = new NinePatch(stoneGraySquare, 13 ,13 ,12 ,13);
        NinePatch patchStoneGreen = new NinePatch(stoneGreen, 6 ,6 ,6 ,6);
        NinePatch patchStoneRed = new NinePatch(stoneRed, 6 ,6 ,6 ,6);
        NinePatch patchStonePink = new NinePatch(stonePink, 4 ,4 ,4 ,4);

        patchDrawableStoneGray = new NinePatchDrawable(patchStoneGray);
        patchDrawableStoneSquare = new NinePatchDrawable(patchStoneGraySquare);
        patchDrawableStoneGreen = new NinePatchDrawable(patchStoneGreen);
        patchDrawableStoneRed = new NinePatchDrawable(patchStoneRed);
        patchDrawableStonePink = new NinePatchDrawable(patchStonePink);

        tileRedStyle = new Label.LabelStyle();
        tileRedStyle.background = patchDrawableStoneRed;
        tileRedStyle.font = gameFont;

        tileGreenStyle = new Label.LabelStyle();
        tileGreenStyle.background = patchDrawableStoneGreen;
        tileGreenStyle.font = gameFont;
    }

    private void loadGrid() {
        controls = new Controls(tiles, tileRedStyle, patchDrawableStoneGreen);
        for(int tileY = (constants.SIZE_H - 1); tileY >= 0; tileY--) {
            for(int tileX = 0; tileX < constants.SIZE_W; tileX++) {
                final int xTile = tileX;
                final int yTile = tileY;
                tiles[xTile][yTile] = new Label(" ", tileRedStyle);
                controls.addFlip(xTile, yTile);
                table.add(tiles[xTile][yTile]).center().width(constants.TILE_W).height(constants.TILE_H);
            }
            table.row();
        }
        table.pad(10);
        table.setBackground(patchDrawableStoneSquare);

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
        gameFont.dispose();
    }
}
