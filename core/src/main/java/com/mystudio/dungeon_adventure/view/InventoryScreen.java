package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.dungeon_adventure.data.Inventory.ItemActionable;
import com.mystudio.dungeon_adventure.data.Inventory.ItemBase;
import com.mystudio.dungeon_adventure.data.Inventory.ItemGeneric;
import com.mystudio.dungeon_adventure.data.Inventory.ItemWearable;
import com.mystudio.dungeon_adventure.helpers.*;
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

import java.util.UUID;

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

    private boolean mousePressedFlag = false;
    private boolean mouseReleasedFlag = false;


    /**
     * Initialises the game screen
     *
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {


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

        // get player info
        this.player = (PlayerBasicClass) SaveState.loadObject(SaveState.PLAYER_SAVE_STATE);

        UUID[] boxes = this.player.inventory.getInventoryBoxes();

        for (int i = 0; i < boxes.length; i++) {

            // if slot has an item
            if (boxes[i] != null) {

                // get item
                ItemBase item = this.player.inventory.getItem(boxes[i]);
                Wearables type = Wearables.NONE;

                // determine if it's a wearable
                if (item.getItemType() == ItemTypes.Wearable) {
                    ItemWearable tmp = item.castIfWearable();
                    type = tmp.whichBodyPart();
                }

                this.window.placeItemInBox(i, boxes[i], item.getItemType(), type, item.getSpritePath());
                System.out.println("placed item in box " + i);
            }
        }

       /* ItemActionable test = new ItemActionable(1, "sword", "desc", Rarity.COMMON,
                Actionables.SWORD, "inventory/items/sword.png");

        boolean success = this.window.placeItemInBox(0, test.getItemID(),ItemTypes.Actionable,
                Wearables.NONE, test.getSpritePath());



        ItemGeneric potion = new ItemGeneric(2, "potion", "desc", Rarity.COMMON,
                "inventory/items/potion.png");

        this.window.placeItemInBox(3, potion.getItemID(),ItemTypes.Generic,
                Wearables.NONE, potion.getSpritePath());

        ItemGeneric helmet = new ItemWearable(3, "potion", "desc", Rarity.COMMON,
                Wearables.HEAD, "inventory/items/helmet.jpg");

        this.window.placeItemInBox(6, helmet.getItemID(),ItemTypes.Wearable,
                Wearables.HEAD, helmet.getSpritePath());*/




        super.preTransitionIn(transitionIn);
    }

    @Override
    public void preTransitionOut(Transition transitionOut) {
        this.isScreenVisible = false;

        super.preTransitionOut(transitionOut);
    }

    @Override
    public void postTransitionOut(Transition transitionOut) {

        // remove sprites from window
        this.window.removeAllSprites();

        super.postTransitionOut(transitionOut);
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
            if (!this.mousePressedFlag) {
                this.mousePressedFlag = true;
                this.mouseReleasedFlag = false;

                int x = InputHandler.mousePressedAtX;
                int y = InputHandler.mousePressedAtY;

                int boxID = this.window.checkForPress(x, y);

                if (boxID != InventoryWindowUI.NO_BOX) {
                    this.boxBeingDraggedFrom = boxID;
                    this.isDraggedFromBox = true;
                    this.window.pickupSprite(boxID, x, y);
                }
            }
        }
        // if user releases left mouse
        // only check for the first instance after release, not continuously
        else if (!this.mouseReleasedFlag) {

            // find out where sprite is released
            int x = InputHandler.mouseReleasedAtX;
            int y = InputHandler.mouseReleasedAtY;

            int boxID = this.window.checkForPress(x, y);

            // if this ID is from a box
            if (boxID != InventoryWindowUI.NO_BOX) {

                // attempt to place item inside box
                boolean placementSuccess = this.window.spriteReleasedInsideBox(boxID, x, y);

                // if placement succeeded
                if (placementSuccess) {
                    // TODO add item to player inventory
                    // get the item ID
                    UUID itemID = this.window.getItemFromBox(boxID);

                    // save the item to the player's inventory, based on what type it is
                    if (boxID < InventoryWindowUI.HEAD) {
                        // any item < HEAD is in regular inventory
                    }
                    else {
                        // check which body part
                    }
                }
                else {
                    // if it failed, place item back in original box/
                    // TODO make new box outside of window to allow players to drop items
                    this.window.spriteReleasedOutsideBox();
                }

                System.out.println("placement inside was: " + placementSuccess);
            }
            // this ID is not from a box
            else {
                System.out.println("item released outside box");
                this.window.spriteReleasedOutsideBox();
            }

            // release sprite
            this.mousePressedFlag = false;
            this.mouseReleasedFlag = true;
            this.isDraggedFromBox = false;
            this.boxBeingDraggedFrom = InventoryWindowUI.NO_BOX;
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
