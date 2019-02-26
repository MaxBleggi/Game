package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.model.Inventory.ItemActionable;
import com.mystudio.dungeon_adventure.model.Inventory.ItemBase;
import com.mystudio.dungeon_adventure.model.Inventory.ItemWearable;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryBoxUI {
    private int x;
    private int y;
    private int width;
    private int height;

    // the item's slot, itemAssigned = -1 when empty
    // 1 = ItemBase
    // 2 = ItemWearable
    // 3 = ItemActionable
    private int itemAssigned;
    private ItemBase possibleItem_base;
    private ItemWearable possibleItem_wearable;
    private ItemActionable possibleItem_actionable;


    // the sprite for each object
    public Sprite itemSprite;


    public InventoryBoxUI(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.itemAssigned = -1;
    }

    /**
     * Reports the type of item contained
     * @return 1 = ItemBase, 2 = ItemWearable, 3 = ItemActionable, -1 = empty
     */
    public int itemAssigned() {
        return itemAssigned;
    }

    /* - - -    remove item methods     - - - */

    /**
     * removes item from slot. Returns that item if it existed
     * @return item if avaiable, null otherwise
     */
    public ItemBase removeItemIfBase() {
        if (itemAssigned != -1) {
            ItemBase tmp = this.possibleItem_base;
            this.possibleItem_base = null;
            this.itemAssigned = -1;
            return tmp;
        }
        return null;
    }

    /**
     * removes item from slot. Returns that item if it existed
     * @return item if avaiable, null otherwise
     */
    public ItemActionable removeItemIfActionable() {
        if (itemAssigned != -1) {
            ItemActionable tmp = this.possibleItem_actionable;
            this.possibleItem_base = null;
            this.itemAssigned = -1;
            return tmp;
        }
        return null;
    }

    /**
     * removes item from slot. Returns that item if it existed
     * @return item if avaiable, null otherwise
     */
    public ItemWearable removeItemIfWearable() {
        if (itemAssigned != -1) {
            ItemWearable tmp = this.possibleItem_wearable;
            this.possibleItem_base = null;
            this.itemAssigned = -1;
            return tmp;
        }
        return null;
    }

    /* - - -    place item methods     - - - */

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(ItemBase item) {
        if (this.itemAssigned == -1) {
            this.possibleItem_base = item;
            this.itemAssigned = 1;

            return true;
        }

        return false;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(ItemWearable item) {
        if (this.itemAssigned == -1) {
            this.possibleItem_wearable = item;
            this.itemAssigned = 2;

            return true;
        }

        return false;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(ItemActionable item) {
        if (this.itemAssigned == -1) {
            this.possibleItem_actionable = item;
            this.itemAssigned = 3;

            return true;
        }

        return false;
    }

    /* - - -    UI related methods     - - - */

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
