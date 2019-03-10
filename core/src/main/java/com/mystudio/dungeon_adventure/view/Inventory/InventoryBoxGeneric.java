package com.mystudio.dungeon_adventure.view.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.data.Inventory.ItemWearable;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Wearables;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryBoxGeneric {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected ItemTypes itemType;
    private Wearables bodyPart;

    // the sprite for each object
    protected Sprite sprite;

    // keep track of what item is assigned to box
    protected int itemID;


    /* - - -    Constructors    - - - */

    public InventoryBoxGeneric(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = null;
        this.itemID = InventoryWindowUI.NO_BOX;
    }

    public InventoryBoxGeneric() {

    }

    public Wearables getBodyPart() {
        return bodyPart;
    }

    public ItemTypes getItemType() {
        return this.itemType;
    }

    /**
     * Retrieves the ID of the item from this box
     * @return Unique item ID
     */
    public int getItem() {
        return this.itemID;
    }


    /* - - -    Sprite manipulation methods    - - - */

    /**
     * Removes item from slot. Returns that item if it existed
     * @return itemID if available, NO_BOX otherwise
     */
    public int removeItemIfNotEmpty() {
        if (hasItem()) {
            this.sprite = null;
            this.itemType = null;

            int tmp = this.itemID;
            this.itemID = InventoryWindowUI.NO_BOX;

            return tmp;
        }
        return InventoryWindowUI.NO_BOX;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @param itemID ID of item added
     * @param spritePath the file path of sprite resource
     * @return true if empty, false if not empty
     */
    public boolean placeItemIfEmpty(int itemID, ItemTypes itemType, Wearables bodyPart, String spritePath) {
        if (!hasItem() && itemType != null && bodyPart != null) {
            this.itemID = itemID;
            this.itemType = itemType;
            this.bodyPart = bodyPart;

            this.sprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
            this.sprite.setSize(this.width, this.height);

            return true;
        }

        return false;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @param itemID ID of item added
     * @param sprite sprite of the item
     * @return true if empty, false is not empty
     */
    public boolean placeItemIfEmpty(int itemID, ItemTypes itemType, Wearables bodyPart, Sprite sprite) {

        if (!hasItem() && sprite != null && itemType != null && bodyPart != null) {
            this.itemID = itemID;
            this.itemType = itemType;
            this.bodyPart = bodyPart;

            this.sprite = sprite;
            this.sprite.setSize(this.width, this.height);

            return true;
        }

        return false;
    }

    /**
     * Used to determine if the user's mouse click is inside the box's area.
     *
     * @return whether or not given (x,y) lie inside box
     */
    public boolean isClickedOn(int x, int y) {
        if (x >= this.x && x < (this.x + this.width)) {
            if (y >= this.y && y < (this.y + this.height)) {
                return true;
            }
        }
        return false;
    }


    /* - - -    Setters and Getters     - - - */

    /**
     * Reports the type of item contained
     * @return true if item assigned, false otherwise
     */
    public boolean hasItem() {
        return this.sprite != null;
    }

    /**
     * Retrieve's item's sprite
     * @return sprite, null is contains no sprite
     */
    public Sprite getItemSprite() {
        return this.sprite;
    }

    /**
     * Resets the coords of the box
     * @param x the x coord
     * @param y the y coord
     */
    public void resetCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Resets the box's dimensions and sprite, if assigned
     * @param w width of box
     * @param h height of box
     */
    public void resetDimension(int w, int h) {
        this.width = w;
        this.height = h;

        if (hasItem()) {
            this.sprite.setSize(w, h);
        }
    }

    /**
     * Retrieves x coord of box
     * @return x coord
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retrieves y coord of box
     * @return y coord
     */
    public int getY() {
        return this.y;
    }

    /**
     * Retrieves width of box
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Retrieves height of box
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
}
