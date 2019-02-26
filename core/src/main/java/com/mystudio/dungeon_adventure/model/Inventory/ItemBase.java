package com.mystudio.dungeon_adventure.model.Inventory;
import com.mystudio.dungeon_adventure.helpers.Wearables;
import com.mystudio.dungeon_adventure.helpers.Rarity;
import org.mini2Dx.core.graphics.Sprite;

import java.io.Serializable;
import java.lang.ref.SoftReference;

public class ItemBase implements Serializable {

    protected Rarity rarityLevel;
    protected String title;
    protected String description;
    protected Sprite sprite;

    // cannot update after creation
    protected int itemID;
    protected int itemTypeID;

    public ItemBase (int itemID, int itemTypeID, String title, String desc, Rarity rarity, Sprite sprite) {
        this.itemID = itemID;
        this.itemTypeID = itemTypeID;
        this.title = title;
        this.description = desc;
        this.rarityLevel = rarity;
        this.sprite = sprite;
    }

    public ItemBase () {
    }

    /**
     * Retrieves sprite
     * @return item's sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Updates title of item
     * @param newTitle new title
     */
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Retrieves title
     * @return item's title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Updates description
     * @param newDesc new description
     */
    public void updateDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Retrieves item description
     * @return description of item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Update item's rarity
     * @param rarityDelta change in rarity
     */
    public void updateRarity(Rarity rarityDelta) {
        this.rarityLevel = rarityDelta;
    }

    /**
     * Retrieves rarity of item
     * @return item rarity
     */
    public Rarity getRarity() {
        return this.rarityLevel;
    }

    /**
     * Retrieves ID of item
     * @return item ID
     */
    public int getItemID() {
        return this.itemID;
    }

    /**
     * Retrieves Item type ID
     * @return type ID
     */
    public int getItemTypeID() {
        return this.itemTypeID;
    }

}
