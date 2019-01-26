package com.agameofstones.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.agameofstones.game.AGameOfStones;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = AGameOfStones.WIDTH;
		config.height = AGameOfStones.HEIGHT;
		config.title = AGameOfStones.TITLE;
		new LwjglApplication(new AGameOfStones(null), config);
	}
}
