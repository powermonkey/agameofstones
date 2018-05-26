package com.agameofstones.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayDeque;

import javax.sound.midi.SysexMessage;

/**
 * Created by Rod on 4/28/2018.
 */

public class Controls {
    private Label.LabelStyle tileRedStyle;
    private NinePatchDrawable stoneGreen, stoneRed, stoneSquare;
    private TextureRegionDrawable starStoneRed, starStoneGreen;
    private final Label[][] gridTiles;
    private Label timeMsg;
    private final boolean[][] gridField;
    private Constants constants;
    private int lastTouchedTileX, lastTouchedTileY;
    private ArrayDeque<UndoCoordinates> undoStack;
    private UndoCoordinates undoCoordinates;
    private long startTime;

    public Controls(boolean[][] gfield, Label[][] tiles, long sTime, Label tMsg){
        gridTiles = tiles;
        gridField = gfield;
        startTime = sTime;
        timeMsg = tMsg;
        constants = new Constants();
        undoStack = new ArrayDeque<UndoCoordinates>();
        undoCoordinates = new UndoCoordinates();
        getAssets();
    }

    private void getAssets() {
        tileRedStyle = GameAssetLoader.tileRedStyle;
        stoneGreen = GameAssetLoader.patchDrawableStoneGreen;
        stoneRed = GameAssetLoader.patchDrawableStoneRed;
        starStoneRed = GameAssetLoader.starRed;
        starStoneGreen = GameAssetLoader.starGreen;
        stoneSquare = GameAssetLoader.patchDrawableStoneSquare;
        lastTouchedTileX = 8;
        lastTouchedTileY = 8;
    }

    public void winMessageListener(TextButton okayButton, Table wTable) {
        final Table winTable = wTable;
        okayButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                winTable.setVisible(false);
                return true;
            }
        });
    }

    public void addFlipListener(int x, int y, Table wTable) {
        final Label tile = gridTiles[x][y];
        final Table winTable = wTable;
        final int xTile = x;
        final int yTile = y;
        tile.setAlignment(Align.center);
        tile.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!allTilesFlipped()) {
                    flipMainTile(xTile, yTile);
                    flipTilesNear(xTile, yTile);

                    //tile undo stack
                    undoCoordinates = new UndoCoordinates(); //TODO: change this to without creating new objects
                    undoCoordinates.setXtile(xTile);
                    undoCoordinates.setYtile(yTile);

                    undoStack.push(undoCoordinates);

                    if (allTilesFlipped()) {
                        winTable.setVisible(true);
                        setElapsedTime();
                    }
                }
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

        if(!gridField[x][y]){
            gridTiles[x][y].getStyle().background = stoneRed;
        } else {
            gridTiles[x][y].getStyle().background = stoneGreen;
        }
    }

    private void flipMainTile(int x, int y) {
        gridTiles[x][y].setStyle(new Label.LabelStyle(tileRedStyle));
        toggleGridField(x, y);

        if(lastTouchedTileX == 8 && lastTouchedTileY == 8) {
            //System.out.println("load grid: "+x+" "+y+" "+lastTouchedTileX+" "+lastTouchedTileY);
        } else {
                //set previous style back
                if(!gridField[lastTouchedTileX][lastTouchedTileY]){
                    gridTiles[lastTouchedTileX][lastTouchedTileY].getStyle().background = stoneRed;
                } else {
                    gridTiles[lastTouchedTileX][lastTouchedTileY].getStyle().background = stoneGreen;
                }
        }

        //save coordinates for last touched tile
        lastTouchedTileX = x;
        lastTouchedTileY = y;

        if(!gridField[x][y]){
            gridTiles[x][y].getStyle().background = starStoneRed;
        } else {
            gridTiles[x][y].getStyle().background = starStoneGreen;
        }
    }

    private void toggleGridField(int x, int y) {
        gridField[x][y] = !gridField[x][y];
    }

    private boolean allTilesFlipped() {
        boolean allFlipped = true;
        for(int y = (constants.SIZE_H - 1); y >= 0; y--) {
            for (int x = 0; x < constants.SIZE_W; x++) {
                if(!gridField[x][y]) {
                    allFlipped = false;
                    break;
                }
            }
        }

        return allFlipped;
    }

    private void setElapsedTime() {
        long elapsedTime = TimeUtils.timeSinceMillis(startTime);
        elapsedTime = elapsedTime / 1000;
        if(elapsedTime > 999000) {
            elapsedTime = 999;
        }
        timeMsg.setText("Time: "+Long.toString(elapsedTime)+"s");
    }

    public void newGameBtnListener(Button newGameBtn, AGameOfStones gam) {
        final AGameOfStones game = gam;
        newGameBtn.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
    }

    public void exitBtnListener(Button exitBtn) {
        exitBtn.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                GameAssetLoader.dispose();
                return true;
            }
        });
    }

    public void undoBtnListener(Button undoBtn) {
        undoBtn.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!allTilesFlipped()) {
                    if(undoStack.size() > 0) {
                        UndoCoordinates undoCoordinates = undoStack.pop();
                        int xtile = undoCoordinates.getXTile();
                        int ytile = undoCoordinates.getYTile();

                        flipTile(xtile, ytile);
                        flipTilesNear(xtile, ytile);

                        if(undoStack.size() != 0) {
                            UndoCoordinates lastTouched = undoStack.peek();
                            if(!gridField[lastTouched.getXTile()][lastTouched.getYTile()]) {
                                gridTiles[lastTouched.getXTile()][lastTouched.getYTile()].getStyle().background = starStoneRed;
                            } else {
                                gridTiles[lastTouched.getXTile()][lastTouched.getYTile()].getStyle().background = starStoneGreen;
                            }
                            lastTouchedTileX = lastTouched.getXTile();
                            lastTouchedTileY = lastTouched.getYTile();
                        }
                    }
                }
                return true;
            }
        });
    }

    class UndoCoordinates
    {
        public int xTile;
        public int yTile;

        public UndoCoordinates setXtile(int xTile) {
            this.xTile = xTile;
            return this;
        }

        public UndoCoordinates setYtile(int yTile) {
            this.yTile = yTile;

            return this;
        }

        public int getXTile() {
            return this.xTile;
        }

        public int getYTile() {
            return this.yTile;
        }
    }
}
