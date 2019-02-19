package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;
import com.mystudio.dungeon_adventure.model.Inventory.ItemWearable;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryBodyPartUI {
    private Wearables bodyPartType;

    private int x;
    private int y;
    private int width;
    private int height;

    private ItemWearable item;
    private boolean itemAssigned;

    // the sprite for each object
    public Sprite itemSprite;

    InventoryBodyPartUI(int x, int y, int width, int height, Wearables bodyPartType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bodyPartType = bodyPartType;
        this.itemAssigned = false;
    }

    /**
     * Returns the type of wearable this is
     * @return enum wearable
     */
    public Wearables bodyPart() {
        return this.bodyPartType;
    }

    /**
     * Removes item from slot
     * @return item removed
     */
    public ItemWearable removeItem() {
        this.itemAssigned = false;

        ItemWearable tmp = this.item;
        this.item = null;

        return tmp;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(ItemWearable item) {
        if (!this.itemAssigned) {
            this.itemAssigned = true;

            this.item = item;
            return true;
        }

        return false;
    }

    /**
     * Reports whether or not if an item is assigned to this slot
     * @return true if item assigned, false otherwise
     */
    public boolean isItemAssigned() {
        return itemAssigned;
    }


    /**
     * Used to determine if the user's mouse click is inside the box's area.
     *
     * @return whether or not given (x,y) lie inside box
     */
    public boolean isInClickedOn(int x, int y) {
        if (x >= this.x && x < (this.x + this.width)) {
            if (y >= this.y && y < (this.y + this.height)) {
                return true;
            }
        }
        return false;
    }

    public void resetCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resetDimension(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
