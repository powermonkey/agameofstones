package com.agameofstones.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Rod on 4/16/2018.
 */

public class GameAssetLoader {
    public static TextureAtlas atlas;
    public static BitmapFont gameFont;
    public static Label.LabelStyle tileRedStyle, tileGreenStyle, tileYellowStyle;
    public static NinePatchDrawable patchDrawableStoneGray, patchDrawableStoneSquare, patchDrawableStoneGreen, patchDrawableStoneRed, patchDrawableStonePink, patchDrawableStoneYellow;
    public static TextButton.TextButtonStyle okayWinButtonStyle;
    public static TextureRegionDrawable mainStoneGreen, mainStoneRed, mainStoneYellow, starRed, starGreen, starYellow;
    public static TextureAtlas.AtlasRegion starStoneGreen, starStoneRed, starStoneYellow, stoneYellow, stoneGray, stoneGreen, stonePink, stoneRed, stoneGraySquare, exitIcon, mainMenuIcon, newGameIcon, randomizeIcon, undoIcon;
    public static TextureAtlas.AtlasRegion bg;
    static GameAssetManager gameAssetManager;


    public static void loadAssets() {
        gameAssetManager = new GameAssetManager();
        gameAssetManager.loadImages();
        gameAssetManager.loadFonts();
        gameAssetManager.manager.finishLoading();
    }

    public static void setAssets() {
        atlas = gameAssetManager.manager.get("packedimages/stones.atlas");
        starStoneGreen = atlas.findRegion("star_green_stone");
        starStoneRed = atlas.findRegion("star_red_stone");
        starStoneYellow = atlas.findRegion("star_yellow_stone");
        stoneGray = atlas.findRegion("gray_stone");
        stoneGreen = atlas.findRegion("green_stone");
        stonePink = atlas.findRegion("pink_stone");
        stoneRed = atlas.findRegion("red_stone");
        stoneYellow = atlas.findRegion("yellow_stone");
        stoneGraySquare = atlas.findRegion("gray_square_stone");
        exitIcon = atlas.findRegion("stone_exit");
        mainMenuIcon = atlas.findRegion("stone_back");
        newGameIcon = atlas.findRegion("stone_new_game");
        randomizeIcon = atlas.findRegion("stone_surprise");
        undoIcon = atlas.findRegion("stone_undo");
        bg = atlas.findRegion("bg");

        gameFont = gameAssetManager.manager.get("fonts/prstart/prstart.fnt");

//        TextureAtlas.AtlasRegion stoneGray = GameAssetLoader.stoneGray;
//        TextureAtlas.AtlasRegion stoneGraySquare = GameAssetLoader.stoneGraySquare;
//        TextureAtlas.AtlasRegion stoneGreen = GameAssetLoader.stoneGreen;
//        TextureAtlas.AtlasRegion stoneRed = GameAssetLoader.stoneRed;
//        TextureAtlas.AtlasRegion stonePink = GameAssetLoader.stonePink;
        mainStoneGreen = new TextureRegionDrawable(stoneGreen);
        mainStoneRed = new TextureRegionDrawable(stoneRed);
        mainStoneYellow = new TextureRegionDrawable(stoneYellow);
        starRed = new TextureRegionDrawable(starStoneRed);
        starGreen = new TextureRegionDrawable(starStoneGreen);
        starYellow = new TextureRegionDrawable(starStoneYellow);

        NinePatch patchStoneGray = new NinePatch(stoneGray, 4 ,4 ,4 ,4);
        NinePatch patchStoneGraySquare = new NinePatch(stoneGraySquare, 13 ,13 ,12 ,13);
        NinePatch patchStoneGreen = new NinePatch(stoneGreen, 8 ,8 ,8 ,8);
        NinePatch patchStoneRed = new NinePatch(stoneRed, 8 ,8 ,8 ,8);
        NinePatch patchStonePink = new NinePatch(stonePink, 4 ,4 ,4 ,4);
        NinePatch patchStoneYellow = new NinePatch(stoneYellow, 4 ,4 ,4 ,4);

        patchDrawableStoneGray = new NinePatchDrawable(patchStoneGray);
        patchDrawableStoneSquare = new NinePatchDrawable(patchStoneGraySquare);
        patchDrawableStoneGreen = new NinePatchDrawable(patchStoneGreen);
        patchDrawableStoneRed = new NinePatchDrawable(patchStoneRed);
        patchDrawableStonePink = new NinePatchDrawable(patchStonePink);
        patchDrawableStoneYellow = new NinePatchDrawable(patchStoneYellow);

        tileRedStyle = new Label.LabelStyle();
        tileRedStyle.background = patchDrawableStoneRed;
        tileRedStyle.font = gameFont;

        tileGreenStyle = new Label.LabelStyle();
        tileGreenStyle.background = patchDrawableStoneGreen;
        tileGreenStyle.font = gameFont;

        tileYellowStyle = new Label.LabelStyle();
        tileYellowStyle.background = patchDrawableStoneYellow;
        tileYellowStyle.font = gameFont;

        okayWinButtonStyle = new TextButton.TextButtonStyle();
        okayWinButtonStyle.up = patchDrawableStoneGray;
        okayWinButtonStyle.down = patchDrawableStoneGray;
        okayWinButtonStyle.font = gameFont;
    }

    public static void dispose() {
        gameAssetManager.dispose();
        gameFont.dispose();
        atlas.dispose();
    }
}
