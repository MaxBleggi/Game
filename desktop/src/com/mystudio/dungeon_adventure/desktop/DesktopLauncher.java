package com.mystudio.dungeon_adventure.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.mystudio.dungeon_adventure.DungeonAdventure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(DungeonAdventure.GAME_IDENTIFIER);
		config.vSyncEnabled = true;
		new DesktopMini2DxGame(new DungeonAdventure(), config);
	}
}
