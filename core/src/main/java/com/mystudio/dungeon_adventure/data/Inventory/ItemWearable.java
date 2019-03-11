package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;
import com.mystudio.dungeon_adventure.helpers.Wearables;

import java.io.Serializable;
import java.util.UUID;

public class ItemWearable extends ItemBase implements Serializable {


    public ItemWearable(String title, String desc, Rarity rarity, Wearables bodyPart, String spritePath) {
        super.itemType = ItemTypes.Wearable;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
        super.spritePath = spritePath;
        super.bodyPart = bodyPart;

        // unique ID for each object
        super.itemID = UUID.randomUUID();
    }

    /**
     * Retrieves what type of wearable this is
     * @return type of wearable
     */
    public Wearables whichBodyPart() {
        return this.bodyPart;
    }
}
