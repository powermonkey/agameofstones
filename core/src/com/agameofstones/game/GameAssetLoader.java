package com.agameofstones.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Rod on 4/16/2018.
 */

public class GameAssetLoader {
    public static TextureAtlas atlas;
    public static TextureAtlas.AtlasRegion stoneGray, stoneGreen, stonePink, stoneRed, stonesGraySquare, exitIcon, mainMenuIcon, newGameIcon, randomizeIcon, undoIcon;
    public static BitmapFont gameFont;
    public static Label.LabelStyle tileRedStyle, tileGreenStyle;
    public static NinePatchDrawable patchDrawableStoneGray, patchDrawableStoneSquare, patchDrawableStoneGreen, patchDrawableStoneRed, patchDrawableStonePink;
    static GameAssetManager gameAssetManager;


    public static void loadAssets() {
        gameAssetManager = new GameAssetManager();
        gameAssetManager.loadImages();
        gameAssetManager.loadFonts();
        gameAssetManager.manager.finishLoading();
    }

    public static void setAssets() {
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

        TextureAtlas.AtlasRegion stoneGray = GameAssetLoader.stoneGray;
        TextureAtlas.AtlasRegion stoneGraySquare = GameAssetLoader.stonesGraySquare;
        TextureAtlas.AtlasRegion stoneGreen = GameAssetLoader.stoneGreen;
        TextureAtlas.AtlasRegion stoneRed = GameAssetLoader.stoneRed;
        TextureAtlas.AtlasRegion stonePink = GameAssetLoader.stonePink;

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

    public static void dispose() {
        gameAssetManager.dispose();
        gameFont.dispose();
        atlas.dispose();
    }
}
