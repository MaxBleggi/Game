package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.data.Inventory.ItemActionable;
import com.mystudio.dungeon_adventure.data.Inventory.ItemWearable;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Wearables;
import com.mystudio.dungeon_adventure.data.Player.PlayerBasicClass;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class InventoryWindowUI {

    /* - - -  Constants  - - - */
    // window dimensions
    private final static int boxPadding = 10;
    private final static int middlePadding = 20;
    private final static int upperSectionPadding = 20;
    private final static int bodyPadding = 10;
    private final static int numberOfBoxRows = 4;
    private final static int numberOfBoxColumns = 3;

    public static final int NO_BOX = -1;
    public static final int[] INVENTORY_BOXES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final int HEAD = 12;
    public static final int TORSO = 13;
    public static final int LEGS = 14;
    public static final int FEET = 15;
    public static final int L_HAND = 16;
    public static final int R_HAND = 17;
    /* - - - - - - - - - - - - - - - - - - - - - - - - */

    // screen is the game screen (window of the application)
    private int screenWidth;
    private int screenHeight;
    // window is the inventory window (i.e. this class)
    private int windowWidth;
    private int windowHeight;
    private int boxSize;

    /* - - -  The top section of the window - - - */
    // player's portrait
    private int portraitX;
    private int portraitY;
    private int portraitSize;
    public Sprite portrait;

    // player's name label
    private int nameX;
    private int nameY;

    // player's health status
    private int healthX;
    private int healthY;
    /* - - - - - - - - - - - - - - - - - - - - - */

    /* - - -  The bottom section of the window - - - */
    private ArrayList<InventoryBoxGeneric> boxes;

    // body parts
    private InventoryBoxBodyPart head;
    private InventoryBoxBodyPart torso;
    private InventoryBoxBodyPart legs;
    private InventoryBoxBodyPart feet;

    // hands
    private InventoryBoxHands leftHand;
    private InventoryBoxHands rightHand;
    /* - - - - - - - - - - - - - - - - - - - - - */

    // used to calculate sprite dragging via the mouse
    private int spriteDraggedID = NO_BOX;
    private int spriteDraggedOffsetX;
    private int spriteDraggedOffsetY;
    private int spriteDraggedX;
    private int spriteDraggedY;

    /* - - -    Constructor    - - - */
    public InventoryWindowUI(int screenWidth, int screenHeight, int boxSize, int portraitSize) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.boxSize = boxSize;
        this.portraitSize = portraitSize;

        // width = box - padding - box - padding - box - middle - hand - body - hand
        this.windowWidth = this.boxSize * numberOfBoxColumns + boxPadding
                * (numberOfBoxColumns - 1) + middlePadding + this.boxSize*3 + bodyPadding*2;

        // height = portrait - upperPadding - box - padding - box - padding - box padding - box
        this.windowHeight = this.portraitSize +  this.boxSize * numberOfBoxRows
                + boxPadding * (numberOfBoxRows - 1) + upperSectionPadding;

        generatePositioning();
    }


    /* - - -    methods for sprite dragging    - - - */


    /**
     * Picks up a sprite to be dragged.
     * @param boxID ID of box
     * @param x coord of mouse click
     * @param y coord of mouse click
     */
    public void pickupSprite(int boxID, int x, int y) {
        this.spriteDraggedID = boxID;
        this.spriteDraggedX = x;
        this.spriteDraggedY = y;

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (boxID == i) {
                this.spriteDraggedOffsetX = x - this.boxes.get(i).getX();
                this.spriteDraggedOffsetY = y - this.boxes.get(i).getY();
            }
        }

        switch (boxID) {
            case HEAD:
                this.spriteDraggedOffsetX = x - this.head.getX();
                this.spriteDraggedOffsetY = y - this.head.getY();
                break;
            case TORSO:
                this.spriteDraggedOffsetX = x - this.torso.getX();
                this.spriteDraggedOffsetY = y - this.torso.getY();
                break;
            case LEGS:
                this.spriteDraggedOffsetX = x - this.legs.getX();
                this.spriteDraggedOffsetY = y - this.legs.getY();
                break;
            case FEET:
                this.spriteDraggedOffsetX = x - this.feet.getX();
                this.spriteDraggedOffsetY = y - this.feet.getY();
                break;
            case L_HAND:
                this.spriteDraggedOffsetX = x - this.leftHand.getX();
                this.spriteDraggedOffsetY = y - this.leftHand.getY();
                break;
            case R_HAND:
                this.spriteDraggedOffsetX = x - this.rightHand.getX();
                this.spriteDraggedOffsetY = y - this.rightHand.getY();
                break;
        }
    }

    /***
     * Changes the x & y for the dragged sprite
     * @param x new dragged x for sprite
     * @param y new dragged y for sprite
     * @return true if a sprite is being dragged, false otherwise
     */
    public boolean dragSprite(int x, int y) {
        if (this.spriteDraggedID != NO_BOX) {
            this.spriteDraggedX = x;
            this.spriteDraggedY = y;
            return true;
        }
        return false;
    }

    /**
     * Resets the drag coords if the sprite was released outside the bounds of a box
     */
    public void spriteReleasedOutsideBox() {
        // reset the sprite's coords to original box
        int boxID = this.spriteDraggedID;

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (boxID == i) {
                this.spriteDraggedX = this.boxes.get(i).getX();
                this.spriteDraggedY = this.boxes.get(i).getY();
                this.spriteDraggedOffsetX += this.boxes.get(i).getX();
                this.spriteDraggedOffsetY += this.boxes.get(i).getX();
            }
        }

        switch (boxID) {
            case HEAD:
                this.spriteDraggedX = this.head.getX();
                this.spriteDraggedY = this.head.getY();
                break;
            case TORSO:
                this.spriteDraggedX = this.torso.getX();
                this.spriteDraggedY = this.torso.getY();
                break;
            case LEGS:
                this.spriteDraggedX = this.legs.getX();
                this.spriteDraggedY = this.legs.getY();
                break;
            case FEET:
                this.spriteDraggedX = this.feet.getX();
                this.spriteDraggedY = this.feet.getY();
                break;
            case L_HAND:
                this.spriteDraggedX = this.leftHand.getX();
                this.spriteDraggedY = this.leftHand.getY();
                break;
            case R_HAND:
                this.spriteDraggedX = this.head.getX();
                this.spriteDraggedY = this.head.getY();
                break;
        }


        this.spriteDraggedID = NO_BOX;
    }

    // TODO
    public boolean spriteReleasedInsideBox(int boxID, int x, int y) {
        // reset the sprite's coords to the new box

        // = = = ensure new box doesn't already have an item assigned = = =
        boolean isBoxAlreadyTaken = false;

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (boxID == i && this.boxes.get(i).hasItem()) {
                isBoxAlreadyTaken = true;
            }
        }

        /* if old box hasn't been found yet */
        if (!isBoxAlreadyTaken) {
            switch (boxID) {
                case HEAD:
                    if (this.head.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
                case TORSO:
                    if (this.torso.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
                case LEGS:
                    if (this.legs.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
                case FEET:
                    if (this.feet.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
                case L_HAND:
                    if (this.leftHand.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
                case R_HAND:
                    if (this.rightHand.hasItem()) {
                        isBoxAlreadyTaken = true;
                    }
                    break;
            }
        }

        // if new box already has an item, it can't be assigned a new one
        if (isBoxAlreadyTaken) {
            System.out.println("box already taken");
            return false;
        }
        /* = = = = = = = = = = = = = = = = = = = */


        /* = = = get item from old box = = = */
        int oldItemID = NO_BOX;
        ItemTypes oldItemType = null;
        Wearables oldBodyPart = null;

        Sprite oldItemSprite = null;
        boolean isOldBoxFound = false;

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (this.spriteDraggedID == i) {
                // remove item from the old box
                oldItemType = this.boxes.get(i).getItemType();
                oldBodyPart = this.boxes.get(i).getBodyPart();
                oldItemSprite = this.boxes.get(i).getItemSprite();

                oldItemID = this.boxes.get(i).getItem();
                isOldBoxFound = true;

            }
        }

        // if old box hasn't been found yet
        if (!isOldBoxFound) {
            switch (this.spriteDraggedID) {
                case HEAD:
                    oldItemType = ItemTypes.Wearable;
                    oldBodyPart = Wearables.HEAD;

                    oldItemSprite = this.head.getItemSprite();

                    oldItemID = this.head.getItem();
                    isOldBoxFound = true;
                    break;
                case TORSO:
                    oldItemType = ItemTypes.Wearable;
                    oldBodyPart = Wearables.TORSO;

                    oldItemSprite = this.torso.getItemSprite();

                    oldItemID = this.torso.getItem();
                    isOldBoxFound = true;
                    break;
                case LEGS:
                    oldItemType = ItemTypes.Wearable;
                    oldBodyPart = Wearables.LEGS;

                    oldItemSprite = this.legs.getItemSprite();

                    oldItemID = this.legs.getItem();
                    isOldBoxFound = true;
                    break;
                case FEET:
                    oldItemType = ItemTypes.Wearable;
                    oldBodyPart = Wearables.FEET;

                    oldItemSprite = this.feet.getItemSprite();

                    oldItemID = this.feet.getItem();
                    isOldBoxFound = true;
                    break;
                case L_HAND:
                    oldItemType = ItemTypes.Actionable;
                    oldBodyPart = Wearables.NONE;

                    oldItemSprite = this.leftHand.getItemSprite();

                    oldItemID = this.leftHand.getItem();
                    isOldBoxFound = true;
                    break;
                case R_HAND:
                    oldItemType = ItemTypes.Actionable;
                    oldBodyPart = Wearables.NONE;

                    oldItemSprite = this.rightHand.getItemSprite();

                    oldItemID = this.rightHand.getItem();
                    isOldBoxFound = true;
                    break;
            }
        }

        // if old box wasn't box, can't place item in new box
        if (!isOldBoxFound) {
            System.out.println("old box not found");
            return false;
        }
        /* ====================================== */


        /* = = = assign item to new box = = = */
        boolean itemSuccessfullyPlaced = false;

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (boxID == i) {
                // add item to new box
                System.out.println("it's a box");
                itemSuccessfullyPlaced = placeItemInBox(boxID, oldItemID, oldItemType, oldBodyPart, oldItemSprite);
            }
        }

        // if item hasn't been placed, try a body part
        // only if the old item is wearable
        if (!itemSuccessfullyPlaced && oldItemType == ItemTypes.Wearable) {

            System.out.println("it's wearable: " + oldItemID);
            itemSuccessfullyPlaced = placeItemOnBody(boxID, oldItemID, oldBodyPart, oldItemSprite);
        }

        // if item still hasn't been placed, try hands
        // only if old item is Actionable
        if (!itemSuccessfullyPlaced && oldItemType == ItemTypes.Actionable) {

            System.out.println("it's actionable: " + oldItemType);
            itemSuccessfullyPlaced = placeItemInHand(boxID, oldItemID, oldItemSprite);
        }


        // if item was removed successfully
        if (itemSuccessfullyPlaced) {

            // remove item this item from former box
            for (int i = 0; i < INVENTORY_BOXES.length; i++) {
                if (this.spriteDraggedID == i) {
                    this.boxes.get(i).removeItemIfNotEmpty();
                }
            }
            switch (this.spriteDraggedID) {
                case HEAD:
                    this.head.removeItemIfNotEmpty();
                    break;
                case TORSO:
                    this.torso.removeItemIfNotEmpty();
                    break;
                case LEGS:
                    this.legs.removeItemIfNotEmpty();
                    break;
                case FEET:
                    this.feet.removeItemIfNotEmpty();
                    break;
                case L_HAND:
                    this.leftHand.removeItemIfNotEmpty();
                    break;
                case R_HAND:
                    this.rightHand.removeItemIfNotEmpty();
                    break;
            }
        }
        else {
            // if item wasn't placed for some reason, report false
            System.out.println("could not place item");
            return false;
        }
        /* ====================================== */

        return true;
    }

    /* - - - - - - - - - - - - - - - - - */


    /* - - -    methods for setting and drawing sprites    - - - */

    public void setSpritesFromModel(PlayerBasicClass player) {
        //player.inventory;
    }

    /**
     * Sets the sprite of specified box, does not include body parts
     * @param boxID ID of box for the sprite
     * @param itemID ID of item associated with sprite
     * @param spritePath file path of sprite resource file
     * @return true if successful, false otherwise
     */
    public boolean placeItemInBox(int boxID, int itemID, ItemTypes itemType, Wearables bodyPart, String spritePath) {
        boolean success = false;

        System.out.println(spritePath + " ID:" + boxID);
        if (boxID > NO_BOX && boxID < HEAD) {
            success = this.boxes.get(boxID).placeItemIfEmpty(itemID, itemType, bodyPart, spritePath);
        }

        return success;
    }

    public boolean placeItemInBox(int boxID, int itemID, ItemTypes itemType, Wearables bodyPart, Sprite sprite) {
        boolean success = false;

        if (boxID > NO_BOX && boxID < HEAD) {
            success = this.boxes.get(boxID).placeItemIfEmpty(itemID, itemType, bodyPart, sprite);
        }

        return success;
    }

    public boolean placeItemInHand(int boxID, int itemID, String spritePath) {
        boolean success = false;

        if (boxID == L_HAND) {
            success = this.leftHand.placeItemIfEmpty(itemID, ItemTypes.Actionable, Wearables.NONE, spritePath);
        }
        else if (boxID == R_HAND) {
            success = this.rightHand.placeItemIfEmpty(itemID, ItemTypes.Actionable, Wearables.NONE, spritePath);
        }

        return success;
    }

    public boolean placeItemInHand(int boxID, int itemID, Sprite sprite) {
        boolean success = false;

        if (boxID == L_HAND) {
            success = this.leftHand.placeItemIfEmpty(itemID, ItemTypes.Actionable, Wearables.NONE, sprite);
        }
        else if (boxID == R_HAND) {
            success = this.rightHand.placeItemIfEmpty(itemID, ItemTypes.Actionable, Wearables.NONE, sprite);
        }

        return success;
    }

    public boolean placeItemOnBody(int boxID, int itemID, Wearables bodyPart, String spritePath) {

        boolean success = false;

        switch (boxID) {
            case HEAD:
                success = this.head.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, spritePath);
                break;
            case TORSO:
                success = this.torso.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, spritePath);
                break;
            case LEGS:
                success = this.legs.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, spritePath);
                break;
            case FEET:
                success = this.feet.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, spritePath);
                break;
        }

        return success;
    }

    public boolean placeItemOnBody(int boxID, int itemID, Wearables bodyPart, Sprite sprite) {

        boolean success = false;

        switch (boxID) {
            case HEAD:
                success = this.head.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, sprite);
                break;
            case TORSO:
                success = this.torso.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, sprite);
                break;
            case LEGS:
                success = this.legs.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, sprite);
                break;
            case FEET:
                success = this.feet.placeItemIfEmpty(itemID, ItemTypes.Wearable, bodyPart, sprite);
                break;
        }

        return success;
    }

    /**
     * Draws the sprites of every box, if those boxes contain sprites
     * @param g Graphics of the render method
     */
    public void drawSprites(Graphics g) {

        // draw each box's sprite, if it has one
        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            InventoryBoxGeneric box = this.boxes.get(i);

            // for every box that has an item
            if (box.hasItem()) {
                // check if this box's sprite is being dragged
                if (this.spriteDraggedID == i) {
                    // use the dragged coords instead
                    g.drawSprite(box.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                            this.spriteDraggedY - this.spriteDraggedOffsetY);
                }
                else {
                    g.drawSprite(box.getItemSprite(), box.getX(), box.getY());
                }
            }
        }

        // draw head if it has an item
        if (this.head.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == HEAD) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.head.getX();
                int yDiff = this.spriteDraggedY - this.head.getY();
                g.drawSprite(this.head.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.head.getItemSprite(), this.head.getX(), this.head.getY());
            }
        }

        // draw torso if it has an item
        if (this.torso.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == TORSO) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.torso.getX();
                int yDiff = this.spriteDraggedY - this.torso.getY();
                g.drawSprite(this.torso.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.torso.getItemSprite(), this.torso.getX(), this.torso.getY());
            }
        }

        // draw legs if it has an item
        if (this.legs.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == LEGS) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.legs.getX();
                int yDiff = this.spriteDraggedY - this.legs.getY();
                g.drawSprite(this.legs.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.legs.getItemSprite(), this.legs.getX(), this.legs.getY());
            }
        }

        // draw feet if it has an item
        if (this.feet.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == FEET) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.feet.getX();
                int yDiff = this.spriteDraggedY - this.feet.getY();
                g.drawSprite(this.feet.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.feet.getItemSprite(), this.feet.getX(), this.feet.getY());
            }
        }

        // draw left hand if it has an item
        if (this.leftHand.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == L_HAND) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.leftHand.getX();
                int yDiff = this.spriteDraggedY - this.leftHand.getY();
                g.drawSprite(this.leftHand.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.leftHand.getItemSprite(), this.leftHand.getX(), this.leftHand.getY());
            }
        }

        // draw right hand if it has an item
        if (this.rightHand.hasItem()) {
            // check if this box's sprite is being dragged
            if (this.spriteDraggedID == R_HAND) {
                // use the dragged coords instead
                int xDiff = this.spriteDraggedX - this.rightHand.getX();
                int yDiff = this.spriteDraggedY - this.rightHand.getY();
                g.drawSprite(this.rightHand.getItemSprite(), this.spriteDraggedX  - this.spriteDraggedOffsetX,
                        this.spriteDraggedY - this.spriteDraggedOffsetY);
            }
            else {
                g.drawSprite(this.rightHand.getItemSprite(), this.rightHand.getX(), this.rightHand.getY());
            }
        }
    }

    /* - - - - - - - - - - - - - - - - - */


    /* - - -    methods related to coordinates of boxes    - - - */

    /**
     * Required after initialization of object. Calculates the coords for each element
     */
    public void generatePositioning() {
        // center window onto screen
        int x = (screenWidth / 2) - (this.windowWidth / 2);
        int y = (screenHeight / 2) - (this.windowHeight / 2);

        // --- create upper section ---
        this.portraitX = x;
        this.portraitY = y;
        this.nameX = this.boxSize;
        this.nameY = this.boxSize;
        this.healthX = this.boxSize;
        this.healthY = this.boxSize;

        // --- create lower section ---

        // create the boxes

        // create 4 row of 3 boxes
        int adjY = y + this.portraitSize + upperSectionPadding;
        int adjX = x;

        this.boxes = new ArrayList<InventoryBoxGeneric>(INVENTORY_BOXES.length);

        // create 4 row of 3 boxes
        for (int i = 0; i < numberOfBoxRows; i++) {
            for (int j = 0; j < numberOfBoxColumns; j++) {
                this.boxes.add(new InventoryBoxGeneric(adjX, adjY, this.boxSize, this.boxSize));
                adjX += this.boxSize + boxPadding;
            }
            adjX = x;
            adjY += this.boxSize + boxPadding;
        }

        // left hand
        int handsY = y + this.portraitSize + upperSectionPadding + this.boxSize + this.boxSize/2;
        adjX = x + this.boxSize*3 + boxPadding*2 + middlePadding;
        this.leftHand = new InventoryBoxHands(adjX, handsY, this.boxSize, this.boxSize,true);

        // create  the body parts
        adjX += this.leftHand.getWidth() + bodyPadding;
        adjY = y + this.portraitSize + upperSectionPadding;
        this.head = new InventoryBoxBodyPart(adjX, adjY, this.boxSize, this.boxSize, Wearables.HEAD);
        adjY += this.head.getHeight() + bodyPadding;
        this.torso = new InventoryBoxBodyPart(adjX, adjY, this.boxSize, this.boxSize, Wearables.TORSO);
        adjY += this.torso.getHeight() + bodyPadding;
        this.legs = new InventoryBoxBodyPart(adjX, adjY, this.boxSize, this.boxSize, Wearables.LEGS);
        adjY += this.legs.getHeight() + bodyPadding;
        this.feet = new InventoryBoxBodyPart(adjX, adjY, this.boxSize, this.boxSize, Wearables.FEET);

        // right hand
        adjX += this.torso.getWidth() + bodyPadding;
        this.rightHand = new InventoryBoxHands(adjX, handsY, this.boxSize, this.boxSize,false);
    }

    /**
     * Determines if the user pressed on any area inside an inventory box
     * @param x the x coord of user mouse press
     * @param y the y coord of user mouse press
     * @return ID of box clicked on
     */
    public int checkForPress(int x, int y) {

        int returnID = NO_BOX;

        // return false if it's not even inside the window
        if (x < this.portraitX || x > this.portraitX + this.windowWidth) {
            System.out.println("out of window width");
            return NO_BOX;
        }
        else if (y < this.portraitY || y > this.portraitY + this.windowHeight) {
            System.out.println("out of window height");
            return NO_BOX;
        }

        // the only clickable areas are on the bottom section
        if (y >= this.portraitY + this.portraitSize) {
            // check every inventory box
            for (int i = 0; i < INVENTORY_BOXES.length; i++) {
                if (this.boxes.get(i).isClickedOn(x,y)) {
                    returnID = INVENTORY_BOXES[i];
                }
            }

            // check every body part for click
            if (this.rightHand.isClickedOn(x, y)) {
                returnID = R_HAND;
            } else if (this.leftHand.isClickedOn(x, y)) {
                returnID = L_HAND;
            } else if (this.head.isClickedOn(x, y)) {
                returnID = HEAD;
            } else if (this.torso.isClickedOn(x, y)) {
                returnID = TORSO;
            } else if (this.legs.isClickedOn(x, y)) {
                returnID = LEGS;
            } else if (this.feet.isClickedOn(x, y)) {
                returnID = FEET;
            }
        }

        return returnID;
    }

    /* - - - - - - - - - - - - - - - - - */

    public int getItemFromBox(int boxID) {
        // find which box this is
        // return that box's item ID

        for (int i = 0; i < INVENTORY_BOXES.length; i++) {
            if (boxID == i) {
               return this.boxes.get(i).getItem();
            }
        }

        int returnValue;

        switch (boxID) {
            case HEAD:
                returnValue = this.head.getItem();
                break;
            case TORSO:
                returnValue = this.torso.getItem();
                break;
            case LEGS:
                returnValue = this.legs.getItem();
                break;
            case FEET:
                returnValue = this.feet.getItem();
                break;
            case L_HAND:
                returnValue = this.leftHand.getItem();
                break;
            case R_HAND:
                returnValue = this.rightHand.getItem();
                break;
                default:
                    returnValue = NO_BOX;
                    break;
        }

        // box ID was not found
        return returnValue;
    }


    /**
     * Retrieve the x coord of window
     * @return x coord of window
     */
    public int getX() {
        // portrait start on the top left of the window
        return this.portraitX;
    }

    /**
     * Retrieve the y coord of window
     * @return y coord of window
     */
    public int getY() {
        // portrait start on the top left of the window
        return this.portraitY;
    }

    /**
     * Retrieve the width of window
     * @return width of window
     */
    public int getWidth() {
        return this.windowWidth;
    }

    /**
     * Retrieve the height of window
     * @return height of window
     */
    public int getHeight() {
        return this.windowHeight;
    }

    /**
     * Draws the outlines of each box to allow for easier debugging
     * @param g Graphics from render
     */
    public void drawOutlineToDebug(Graphics g) {
        g.drawRect(this.portraitX, this.portraitY, this.portraitSize, this.portraitSize);

        for (InventoryBoxGeneric box : this.boxes) {
            g.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        }

        g.drawRect(this.leftHand.getX(), this.leftHand.getY(), this.leftHand.getWidth(), this.leftHand.getHeight());
        g.drawRect(this.head.getX(), this.head.getY(), this.head.getWidth(), this.head.getHeight());
        g.drawRect(this.torso.getX(), this.torso.getY(), this.torso.getWidth(), this.torso.getHeight());
        g.drawRect(this.legs.getX(), this.legs.getY(), this.legs.getWidth(), this.legs.getHeight());
        g.drawRect(this.feet.getX(), this.feet.getY(), this.feet.getWidth(), this.feet.getHeight());
        g.drawRect(this.rightHand.getX(), this.rightHand.getY(), this.rightHand.getWidth(), this.rightHand.getHeight());

    }
}
