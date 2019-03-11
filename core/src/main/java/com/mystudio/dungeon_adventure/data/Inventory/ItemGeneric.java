package com.mystudio.dungeon_adventure.data.Inventory;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;

import java.io.Serializable;
import java.util.UUID;

public class ItemGeneric extends ItemBase implements Serializable {


    public ItemGeneric(String title, String desc, Rarity rarity, String spritePath) {
        super.itemType = ItemTypes.Generic;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
        super.spritePath = spritePath;

        // unique ID for each object
        super.itemID = UUID.randomUUID();
    }

}
