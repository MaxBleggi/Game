package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.helpers.GameAttributes;
import com.mystudio.dungeon_adventure.helpers.SaveState;
import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;
import com.mystudio.dungeon_adventure.view.DungeonScreen;
import com.mystudio.dungeon_adventure.view.Inventory.InventoryWindowUI;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class InventoryScreen extends BasicGameScreen {

    // ID used to reference the screen
    public static final int ID = 3;
    private PlayerBasicClass player;

    private InventoryWindowUI window;


    /**
     * Initialises the game screen
     *
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {
        // get player info
        this.player = (PlayerBasicClass) SaveState.loadObject(SaveState.PLAYER_SAVE_STATE);


        // create 4 x 4 box of rectangles
        int x = GameAttributes.SCREEN_WIDTH / 2;
        int y = GameAttributes.SCREEN_HEIGHT / 2;

        int boxSize = 50;
        int portraitSize = 100;
        this.window = new InventoryWindowUI(GameAttributes.SCREEN_WIDTH,
                GameAttributes.SCREEN_HEIGHT, boxSize, portraitSize);

        // handle user interactions with UI
        handleInput();
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
       // g.drawTexture(new Texture(Gdx.files.internal("inventory.jpg")), 0,0);

        g.setBackgroundColor(Color.BLACK);

        this.window.drawOutlineToDebug(g);

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

    private void handleInput() {

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                System.out.println("touchdown at x: " + x + " y:" + y);
                return true;
            }

            @Override
            public boolean touchUp (int x, int y, int pointer, int button) {
                return true;
            }

            @Override
            public boolean touchDragged (int x, int y, int pointer) {
                System.out.println("dragged at x: " + x + " y:" + y);
                return true;
            }
        });
    }
}
