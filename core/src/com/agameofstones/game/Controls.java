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
    private final Label.LabelStyle unFlippedStyle;
    private final NinePatchDrawable flippedStyle;
    private final Label[][] gridTiles;
    private Constants constants;

    public Controls(Label[][] tiles, Label.LabelStyle unflipped, NinePatchDrawable flipped){
        gridTiles = tiles;
        unFlippedStyle = unflipped;
        flippedStyle = flipped;
        constants = new Constants();
    }

    public void addFlip(int x, int y) {
        final Label tile = gridTiles[x][y];
        final int xTile = x;
        final int yTile = y;
        tile.setAlignment(Align.center);
        tile.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                flip(tile);
                flipTilesNear(xTile, yTile);
                return true;
            }
        });
    }

    private void flipTilesNear(int x, int y) {
        flip(tileAt(x, y - 1));      // S
        flip(tileAt(x - 1, y));      // W
        flip(tileAt(x + 1, y));      // E
        flip(tileAt(x, y + 1));      // N
    }

    private Label tileAt(int x, int y) {System.out.println(x+" "+y);
        if(x >= 0 && x < constants.SIZE_W && y >= 0 && y < constants.SIZE_H){
            return gridTiles[x][y];
        }else{
            if(x < 0) {
                return gridTiles[x + (constants.SIZE_W)][y];
            } else if (x >= constants.SIZE_W) {
                return gridTiles[x - (constants.SIZE_W)][y];
            } else if(y >= constants.SIZE_H) {
                return gridTiles[x][y - (constants.SIZE_H)];
            } else {
                return gridTiles[x][y + (constants.SIZE_H)];
            }
        }
    }

    private void flip(Label tile) {
        tile.setStyle(new Label.LabelStyle(unFlippedStyle));
        tile.getStyle().background = flippedStyle;
    }
}
