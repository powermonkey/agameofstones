package com.agameofstones.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Rod on 4/16/2018.
 */

public class GameAssetLoader {
    public static TextureAtlas atlas;
    public static TextureAtlas.AtlasRegion stoneGray, stoneGreen, stonePink, stoneRed, stonesGraySquare, exitIcon, mainMenuIcon, newGameIcon, randomizeIcon, undoIcon;
    public static BitmapFont gameFont;
    static GameAssetManager gameAssetManager;

    public static void load() {
        gameAssetManager = new GameAssetManager();
        gameAssetManager.loadImages();
        gameAssetManager.loadFonts();
        gameAssetManager.manager.finishLoading();
    }

    public static void getLoadedAssets() {
        atlas = gameAssetManager.manager.get("packedimages/stones.atlas");
        stoneGray = atlas.findRegion("gray_stone");
        stoneGreen = atlas.findRegion("green_stone");
        stonePink = atlas.findRegion("pink_stone");
        stoneRed = atlas.findRegion("red_stone");
        stonesGraySquare = atlas.findRegion("gray_square_stone");
        exitIcon = atlas.findRegion("door");
        mainMenuIcon = atlas.findRegion("exitLeft");
        newGameIcon = atlas.findRegion("backward");
        randomizeIcon = atlas.findRegion("question");
        undoIcon = atlas.findRegion("refresh");

        gameFont = gameAssetManager.manager.get("fonts/prstart/prstart.fnt");
    }

    public static void dispose() {
        gameAssetManager.dispose();
        gameFont.dispose();
        atlas.dispose();
    }
}
