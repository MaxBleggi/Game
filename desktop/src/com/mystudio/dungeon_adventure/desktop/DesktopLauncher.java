package com.mystudio.dungeon_adventure.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.mystudio.dungeon_adventure.DungeonAdventure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(DungeonAdventure.GAME_IDENTIFIER);
		config.title = "Basic Game Example";
		config.width = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		new DesktopMini2DxGame(new DungeonAdventure(), config);
	}
}
