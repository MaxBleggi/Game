package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.Actionables;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;

import java.io.Serializable;
import java.util.UUID;

public class ItemActionable extends ItemBase implements Serializable {

    public ItemActionable(String title, String desc, Rarity rarity, Actionables type, String spritePath) {
        super.itemType = ItemTypes.Actionable;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
        super.spritePath = spritePath;
        super.type = type;

        super.itemID = UUID.randomUUID();
    }

    /**
     * Retrieves what type of actionable this is
     * @return type of actionable
     */
    public Actionables whichActionableType() {
        return this.type;
    }
}
