package com.mystudio.dungeon_adventure.model.Inventory;

import com.mystudio.dungeon_adventure.helpers.Actionables;
import com.mystudio.dungeon_adventure.helpers.Rarity;

import java.io.Serializable;

public class ItemActionable extends ItemBase implements Serializable {
    private Actionables type;

    public ItemActionable(int itemID, int itemTypeID, String title, String desc, Rarity rarity, Actionables type) {
        super.itemID = itemID;
        super.itemTypeID = itemTypeID;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
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
