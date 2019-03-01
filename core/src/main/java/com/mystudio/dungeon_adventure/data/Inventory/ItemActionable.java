package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.Actionables;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;

import java.io.Serializable;

public class ItemActionable extends ItemBase implements Serializable {
    private Actionables type;

    public ItemActionable(int itemID, String title, String desc, Rarity rarity, Actionables type, String spritePath) {
        super.itemID = itemID;
        super.itemType = ItemTypes.Actionable;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
        super.spritePath = spritePath;
        this.type = type;
    }

    /**
     * Retrieves what type of actionable this is
     * @return type of actionable
     */
    public Actionables whichActionableType() {
        return this.type;
    }
}
