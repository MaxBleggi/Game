package com.mystudio.dungeon_adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.view.DungeonMap;
import com.mystudio.dungeon_adventure.view.LoadingScreen;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.game.ScreenBasedGame;
import org.mini2Dx.core.graphics.Graphics;

public class DungeonAdventure extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.dungeon_adventure";
	
	@Override
    public void initialise() {
	    // initialize data here
    	// add screens
        this.addScreen(new LoadingScreen());
        this.addScreen(new DungeonMap());

    }

    @Override
    public int getInitialScreenId() {
	    // initial screen of game
        return LoadingScreen.ID;
    }
}
