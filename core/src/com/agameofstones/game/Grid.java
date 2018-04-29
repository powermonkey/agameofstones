package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Rod on 4/17/2018.
 */

public class Grid {
    private NinePatchDrawable stoneGreen, stoneSquare;
    final AGameOfStones game;
    private Table rootTable, table, winRootTable, winTable;
    private Stage stage;
    private Label tiles [][];
    private Label.LabelStyle tileRedStyle, tileGreenStyle;
    private boolean gridField [][];
    private Controls controls;
    private Constants constants;
//    private TextButton okayWinButton;
    private TextButton.TextButtonStyle okayWinButtonStyle;
    private BitmapFont gameFont;

    public Grid(AGameOfStones gam) {
        this.game = gam;
        constants = new Constants();
        gridField = new boolean[constants.SIZE_W][constants.SIZE_H];
        initTables();
        getAssets();
        loadGrid();
        loadWinGrid();
    }

    private void initTables() {
        rootTable = new Table();
        rootTable.setFillParent(true);
        winRootTable = new Table();
        winRootTable.setFillParent(true);
        winTable = new Table();
        table = new Table();
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT), game.batch);
        tiles = new Label[constants.SIZE_W][constants.SIZE_H];
    }

    private void getAssets() {
        stoneGreen = GameAssetLoader.patchDrawableStoneGreen;
        stoneSquare = GameAssetLoader.patchDrawableStoneSquare;
        tileRedStyle = GameAssetLoader.tileRedStyle;
        tileGreenStyle = GameAssetLoader.tileGreenStyle;
        okayWinButtonStyle = GameAssetLoader.okayWinButtonStyle;
        gameFont = GameAssetLoader.gameFont;
    }

    private void loadGrid() {
        controls = new Controls(gridField, tiles);
        for(int tileY = (constants.SIZE_H - 1); tileY >= 0; tileY--) {
            for(int tileX = 0; tileX < constants.SIZE_W; tileX++) {
                final int xTile = tileX;
                final int yTile = tileY;

                //testing grid
//                if((xTile == 6 && yTile == 2) || (xTile == 5 && yTile == 1) || (xTile == 6 && yTile == 1) || (xTile == 7 && yTile == 1) || (xTile == 6 && yTile == 0)) {
//                    tiles[xTile][yTile] = new Label(" ", tileRedStyle);
//                    gridField[xTile][yTile] = false; //flipped tile
//                } else {
//                    tiles[xTile][yTile] = new Label(" ", tileGreenStyle);
//                    gridField[xTile][yTile] = true; //unflipped tile
//                }

                tiles[xTile][yTile] = new Label(" ", tileRedStyle);
                gridField[xTile][yTile] = false; //unflipped tile
                controls.addFlipListener(xTile, yTile, winRootTable);
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

    private void loadWinGrid() {
        TextButton okayWinButton = new TextButton("OKAY", okayWinButtonStyle);
        Label.LabelStyle winMessageStyle = new Label.LabelStyle(gameFont, null);
        Label winMessage = new Label("Congratulations! You've solved the puzzle!", winMessageStyle);
        winMessage.setWrap(true);
        winMessage.setWidth(400);

        winTable.add(winMessage).pad(5).width(400);
        winTable.row();
        winTable.add(okayWinButton).pad(10);
        winTable.row();
        winTable.setBackground(stoneSquare);
        winTable.setVisible(true);
        winRootTable.add(winTable).center().padBottom(100).center();
        winRootTable.row();
        winRootTable.setVisible(false);
        stage.addActor(winRootTable);
    }

    public void render(float delta){
        stage.act(delta);
        stage.draw();
    }

    private void dispose(){
        stage.dispose();
    }
}
