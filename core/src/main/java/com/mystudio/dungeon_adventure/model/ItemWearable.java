package com.mystudio.dungeon_adventure.model;

import com.mystudio.dungeon_adventure.helpers.Rarity;
import com.mystudio.dungeon_adventure.helpers.Wearables;

public class ItemWearable extends ItemBase {

    private Wearables bodyPart;

    public ItemWearable(int itemID, int itemTypeID, String title, String desc, Rarity rarity, Wearables bodyPart) {
        super.itemID = itemID;
        super.itemTypeID = itemTypeID;
        super.title = title;
        super.description = desc;
        super.rarityLevel = rarity;
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
