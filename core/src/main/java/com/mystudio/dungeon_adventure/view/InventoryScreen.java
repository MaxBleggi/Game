package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.dungeon_adventure.helpers.*;
import com.mystudio.dungeon_adventure.data.Inventory.ItemActionable;
import com.mystudio.dungeon_adventure.data.Player.PlayerBasicClass;
import com.mystudio.dungeon_adventure.view.Inventory.InventoryWindowUI;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

public class InventoryScreen extends BasicGameScreen {

    private boolean isScreenVisible;

    // ID used to reference the screen
    public static final int ID = 3;
    private PlayerBasicClass player;

    private InventoryWindowUI window;
    private Sprite windowBackground;
    private int windowX;
    private int windowY;

    // the box that contains that the item is dragged from
    private boolean isDraggedFromBox = false;
    private int boxBeingDraggedFrom;

    private boolean mousePressFlag = false;


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
        int x = GameConstants.SCREEN_WIDTH / 2;
        int y = GameConstants.SCREEN_HEIGHT / 2;

        int boxSize = 50;
        int portraitSize = 100;
        this.window = new InventoryWindowUI(GameConstants.SCREEN_WIDTH,
                GameConstants.SCREEN_HEIGHT, boxSize, portraitSize);


     //   this.windowBackground = new Sprite(new Texture(Gdx.files.internal(GameConstants.INVENTORY_WINDOW_BACKGROUND)));
      //  this.windowBackground.setSize(this.window.getWidth()*1.5f, this.window.getHeight()*1.5f);

      //  this.windowX = this.window.getX() - portraitSize/4;
      //  this.windowY = this.window.getY() - portraitSize/2;

        // handle user interactions with UI
     //   handleInput();

        this.isScreenVisible = false;
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
        this.isScreenVisible = true;

        ItemActionable test = new ItemActionable(1, "sword", "desc", Rarity.COMMON,
                Actionables.SWORD, "inventory/items/sword.png");

        boolean success = this.window.setBoxSprite(0, test.getItemID(), test.getSpritePath());

        System.out.println("success of adding sprite: " + success);

        super.preTransitionIn(transitionIn);
    }

    @Override
    public void preTransitionOut(Transition transitionOut) {
        this.isScreenVisible = false;
        super.preTransitionOut(transitionOut);
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
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            // navigate to inventory screen
            screenManager.enterGameScreen(DungeonScreen.ID, new FadeOutTransition(), new FadeInTransition());
        }

        // check for user clicks
        if (InputHandler.isLeftMousePressedDown) {

            // make sure clicks are only counted once per button press
            if (!this.mousePressFlag) {
                this.mousePressFlag = true;
                int x = InputHandler.mousePressedAtX;
                int y = InputHandler.mousePressedAtY;

                int tmp = this.window.checkForPress(x, y);

                if (tmp != -1) {
                    this.boxBeingDraggedFrom = tmp;
                    this.isDraggedFromBox = true;
                    this.window.pickupSprite(tmp, x, y);
                }
            }
        }
        // user releases left mouse
        else {
            // find out where sprite is released

            // release sprite
            this.mousePressFlag = false;
            this.isDraggedFromBox = false;
            this.boxBeingDraggedFrom = -1;
            this.window.releaseSpriteOutsideBox();
        }

        // check for user dragging sprite
        if (this.isDraggedFromBox && InputHandler.isLeftMouseDragged) {
            int x = InputHandler.mouseDraggedX;
            int y = InputHandler.mouseDraggedY;

            // update the sprite's position if it's dragged
            this.window.dragSprite(x, y);

            System.out.println("dragged at x: " + x + " y:" + y);
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

       // g.drawSprite(this.windowBackground, windowX, windowY);
       this.window.drawOutlineToDebug(g);

       this.window.drawSprites(g);
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
