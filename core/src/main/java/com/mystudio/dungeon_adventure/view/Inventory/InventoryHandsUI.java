package com.mystudio.dungeon_adventure.view.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryHandsUI {

    private boolean isLeftHand;

    private int x;
    private int y;
    private int width;
    private int height;

    private int itemID;

    // the sprite for each object
    public Sprite sprite;

    public InventoryHandsUI(int x, int y, int width, int height, boolean isLeftHand) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isLeftHand = isLeftHand;
        this.sprite = null;
        this.itemID = -1;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Attempts to place item into slot. Will return false if slot is already used.
     * @return success of placement
     */
    public boolean placeItemIfEmpty(int itemID, String spritePath) {
        if (this.sprite == null) {
            this.itemID = itemID;
            this.sprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
            this.sprite.setSize(this.width, this.height);
            return true;
        }
        return false;
    }

    /**
     * Removes item from slot
     * @return itemID removed
     */
    public int removeItem() {
        if (this.sprite != null) {
            this.sprite = null;

            int tmp = this.itemID;
            this.itemID = -1;

            return tmp;
        }

        return -1;
    }

    public boolean hasItem() {
        return this.sprite != null;
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
