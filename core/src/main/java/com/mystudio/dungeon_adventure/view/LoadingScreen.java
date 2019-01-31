package com.mystudio.dungeon_adventure.view;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

public class LoadingScreen extends BasicGameScreen {

    // ID used to reference the screen
    public static final int ID = 1;

    // the amount of time this screen will last until loading to next screen
    // 4 seconds
    private float loadingTime = 4f;

    @Override
    public void initialise(GameContainer gc) {
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
        System.out.println("gets here");
        if (loadingTime > 0f) {

            // countdown the time
            loadingTime -= delta;

            // when 4 seconds pass
            if (loadingTime < 0f) {
                // fade to the in game screen
                screenManager.enterGameScreen(DungeonMap.ID, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawString("Loading game...", 32, 32);
        g.drawCircle(3f,3f,4f);
    }

    @Override
    public int getId() {
        return ID;
    }
}
