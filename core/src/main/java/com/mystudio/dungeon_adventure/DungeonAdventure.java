package com.mystudio.dungeon_adventure;

import com.mystudio.dungeon_adventure.view.DungeonScreen;
import com.mystudio.dungeon_adventure.view.InventoryScreen;
import com.mystudio.dungeon_adventure.view.LoadingScreen;
import org.mini2Dx.core.game.ScreenBasedGame;

public class DungeonAdventure extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.dungeon_adventure";

	@Override
    public void initialise() {
	    // retrieve any player data if it exists

        // create player and other data

	    // initialize data here
    	// add screens
        this.addScreen(new LoadingScreen());
        this.addScreen(new DungeonScreen());
        this.addScreen(new InventoryScreen());

    }

    @Override
    public int getInitialScreenId() {
	    // initial screen of game
        return LoadingScreen.ID;
    }
}
