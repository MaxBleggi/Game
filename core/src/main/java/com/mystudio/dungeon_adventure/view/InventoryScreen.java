package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.helpers.SaveState;
import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

public class InventoryScreen extends BasicGameScreen {

    // ID used to reference the screen
    public static final int ID = 3;
    private PlayerBasicClass player;

    /**
     * Initialises the game screen
     *
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {
        // get player info
        this.player = (PlayerBasicClass) SaveState.loadObject(SaveState.PLAYER_SAVE_STATE);


    }

    /**
     * Updates the game screen
     *
     * @param gc            The {@link GameContainer} of the game
     * @param screenManager The {@link ScreenManager} of the game
     * @param delta         The time in seconds since the last update
     */
    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
        // if player closed inventory
        if (!this.player.isInventoryOpen()) {
            // navigate to inventory screen
            screenManager.enterGameScreen(DungeonScreen.ID, new FadeOutTransition(), new FadeInTransition());
        }
    }

    /**
     * Interpolate between the previous and current state
     *
     * @param gc
     * @param alpha The interpolation alpha value
     */
    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    /**
     * Renders the game screen
     *
     * @param gc The {@link GameContainer} of the game
     * @param g  The {@link Graphics} context available for rendering
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawTexture(new Texture(Gdx.files.internal("inventory.jpg")), 0,0);
    }

    /**
     * Returns the identifier of the screen
     *
     * @return A unique identifier
     */
    @Override
    public int getId() {
        return ID;
    }
}
