package com.agameofstones.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Rod on 4/28/2018.
 */

public class Controls {
    private Label.LabelStyle tileRedStyle;
    private NinePatchDrawable stoneGreen, stoneRed;
    private final Label[][] gridTiles;
    private final boolean[][] gridField;
    private Constants constants;

    public Controls(boolean[][] gfield, Label[][] tiles){
        gridTiles = tiles;
        gridField = gfield;
        constants = new Constants();
        getAssets();
    }

    private void getAssets() {
        tileRedStyle = GameAssetLoader.tileRedStyle;
        stoneGreen = GameAssetLoader.patchDrawableStoneGreen;
        stoneRed = GameAssetLoader.patchDrawableStoneRed;
    };

    public void addFlipListener(int x, int y) {
        final Label tile = gridTiles[x][y];
        final int xTile = x;
        final int yTile = y;
        tile.setAlignment(Align.center);
        tile.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                flipTile(xTile, yTile);
                flipTilesNear(xTile, yTile);
                return true;
            }
        });
    }

    private void flipTilesNear(int x, int y) {
        tileAt(x, y - 1);      // S
        tileAt(x - 1, y);      // W
        tileAt(x + 1, y);      // E
        tileAt(x, y + 1);      // N
    }

    private void tileAt(int x, int y) {
        if(x >= 0 && x < constants.SIZE_W && y >= 0 && y < constants.SIZE_H){
            flipTile(x, y);
        }else{
            if(x < 0) {
                flipTile(x + (constants.SIZE_W), y);
            } else if (x >= constants.SIZE_W) {
                flipTile(x - (constants.SIZE_W), y);
            } else if(y >= constants.SIZE_H) {
                flipTile(x, y - (constants.SIZE_H));
            } else {
                flipTile(x, y + (constants.SIZE_H));
            }
        }
    }

    private void flipTile(int x, int y) {
        gridTiles[x][y].setStyle(new Label.LabelStyle(tileRedStyle));
        toggleGridField(x, y);
        if(gridField[x][y] == false){
            gridTiles[x][y].getStyle().background = stoneRed;
        } else {
            gridTiles[x][y].getStyle().background = stoneGreen;
        }
    }

    private void toggleGridField(int x, int y) {
        gridField[x][y] = !gridField[x][y];
    }
}
