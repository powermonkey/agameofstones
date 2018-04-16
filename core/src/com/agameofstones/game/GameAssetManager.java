package com.agameofstones.game;

/**
 * Created by Rod on 4/16/2018.
 */

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameAssetManager {
    public final AssetManager manager = new AssetManager();
    public final String imagesPack = "packed_images/stones.atlas";
    public final String gameFont = "fonts/prstart/prstart.fnt";

    public void loadImages() { manager.load(imagesPack, TextureAtlas.class); }

    public void loadFonts() { manager.load(gameFont, BitmapFont.class); }

    public void dispose() { manager.dispose(); }
}
