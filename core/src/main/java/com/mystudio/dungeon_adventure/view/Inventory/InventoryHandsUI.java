package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.model.Inventory.ItemActionable;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryHandsUI {

    private boolean isLeftHand;

    private int x;
    private int y;
    private int width;
    private int height;

    private boolean itemAssigned;
    private ItemActionable item;

    // the sprite for each object
    public Sprite itemSprite;

    public InventoryHandsUI(int x, int y, int width, int height, boolean isLeftHand) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.itemAssigned = false;
        this.isLeftHand = isLeftHand;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(ItemActionable item) {
        if (!this.itemAssigned) {
            this.itemAssigned = true;

            this.item = item;
            return true;
        }
        return false;
    }

    /**
     * Removes item from slot
     * @return item removed
     */
    public ItemActionable removeItem() {
        this.itemAssigned = false;

        ItemActionable tmp = this.item;
        this.item = null;

        return tmp;
    }

    public boolean isItemAssigned() {
        return this.itemAssigned;
    }

    public boolean isLeftHand() {
        return this.isLeftHand;
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
