package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.Actionables;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;
import com.mystudio.dungeon_adventure.helpers.Wearables;

import java.io.Serializable;
import java.util.UUID;

public abstract class ItemBase implements Serializable {
    protected Rarity rarityLevel;
    protected String title;
    protected String description;
    protected String spritePath;

    protected Wearables bodyPart;
    protected Actionables type;

    // cannot update after creation
    protected UUID itemID;
    protected ItemTypes itemType;


    public ItemBase() {
    }


    public ItemGeneric castIfGeneric() {

        if (this.itemType == ItemTypes.Generic) {
            return (ItemGeneric)this;
        }
        else {
            return null;
        }
    }

    public ItemWearable castIfWearable() {
        if (this.itemType == ItemTypes.Wearable) {
            return (ItemWearable)this;
        }
        else {
            return null;
        }
    }

    public ItemActionable castIfActionable() {
        if (this.itemType == ItemTypes.Actionable) {
            return (ItemActionable)this;
        }
        else {
            return null;
        }
    }



    /**
     * Retrieves sprite
     * @return item's sprite
     */
    public String getSpritePath() {
        return this.spritePath;
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
    public UUID getItemID() {
        return this.itemID;
    }

    /**
     * Retrieves Item type ID
     * @return type ID
     */
    public ItemTypes getItemType() {
        return this.itemType;
    }

}
