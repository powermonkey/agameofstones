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
    public Controls(){

    }

    public void addFlip(Label gridTile, Label.LabelStyle unflipped, NinePatchDrawable flipped) {
        final Label tile = gridTile;
        final Label.LabelStyle unFlippedStyle = unflipped;
        final NinePatchDrawable flippedStyle = flipped;
        tile.setAlignment(Align.center);
        tile.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tile.setStyle(new Label.LabelStyle(unFlippedStyle));
                tile.getStyle().background = flippedStyle;
                return true;
            }
        });
    }
}
