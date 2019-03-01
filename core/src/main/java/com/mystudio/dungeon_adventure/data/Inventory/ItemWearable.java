package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Rarity;
import com.mystudio.dungeon_adventure.helpers.Wearables;

import java.io.Serializable;

public class ItemWearable extends ItemBase implements Serializable {

    private Wearables bodyPart;

    public ItemWearable(int itemID, String title, String desc, Rarity rarity, Wearables bodyPart, String spritePath) {
        super.itemID = itemID;
        super.itemType = ItemTypes.Wearable;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
        super.spritePath = spritePath;
        this.bodyPart = bodyPart;
    }

    /**
     * Retrieves what type of wearable this is
     * @return type of wearable
     */
    public Wearables whichBodyPart() {
        return this.bodyPart;
    }
}
