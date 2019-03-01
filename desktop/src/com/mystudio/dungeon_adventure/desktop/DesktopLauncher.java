package com.mystudio.dungeon_adventure.desktop;

import com.mystudio.dungeon_adventure.helpers.GameConstants;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.mystudio.dungeon_adventure.DungeonAdventure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(DungeonAdventure.GAME_IDENTIFIER);
		config.title = "Dante's Dungeon";
		config.width = GameConstants.SCREEN_WIDTH;
		config.height = GameConstants.SCREEN_HEIGHT;
		config.vSyncEnabled = true;
		config.resizable = false;
		new DesktopMini2DxGame(new DungeonAdventure(), config);
	}
}
