package com.mystudio.dungeon_adventure.model;

import com.mystudio.dungeon_adventure.helpers.Actionables;
import com.mystudio.dungeon_adventure.helpers.Rarity;

public class ItemActionable extends ItemBase {
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
