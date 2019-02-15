package com.mystudio.dungeon_adventure;

import com.mystudio.dungeon_adventure.view.DungeonScreen;
import com.mystudio.dungeon_adventure.view.LoadingScreen;
import org.mini2Dx.core.game.ScreenBasedGame;

public class DungeonAdventure extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.dungeon_adventure";

	@Override
    public void initialise() {
	    // initialize data here
    	// add screens
        this.addScreen(new LoadingScreen());
        this.addScreen(new DungeonScreen());

    }

    @Override
    public int getInitialScreenId() {
	    // initial screen of game
        return LoadingScreen.ID;
    }
}
