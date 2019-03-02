package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;


public class InventoryBoxBodyPart extends InventoryBoxGeneric {

    private Wearables bodyPartType;


    InventoryBoxBodyPart(int x, int y, int width, int height, Wearables bodyPartType) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.sprite = null;
        super.itemID = -1;

        this.bodyPartType = bodyPartType;
    }

    /**
     * Returns the type of wearable this is
     * @return enum wearable
     */
    public Wearables bodyPart() {
        return this.bodyPartType;
    }
}
