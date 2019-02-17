package com.mystudio.dungeon_adventure.model.Inventory;
import com.mystudio.dungeon_adventure.helpers.Wearables;
import com.mystudio.dungeon_adventure.helpers.Rarity;

import java.io.Serializable;

public class ItemBase implements Serializable {

    protected Rarity rarityLevel;
    protected String title;
    protected String description;

    // cannot update after creation
    protected int itemID;
    protected int itemTypeID;

    public ItemBase () {
        // this class will never be instantiated directly, only extended
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
