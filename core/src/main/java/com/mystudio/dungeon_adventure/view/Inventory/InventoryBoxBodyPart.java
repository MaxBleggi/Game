package com.mystudio.dungeon_adventure.view.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.Wearables;
import org.mini2Dx.core.graphics.Sprite;


public class InventoryBoxBodyPart extends InventoryBoxGeneric {

    private final Wearables bodyPartType;


    InventoryBoxBodyPart(int x, int y, int width, int height, Wearables bodyPartType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = null;
        this.itemID = -1;

        this.bodyPartType = bodyPartType;
    }

    @Override
    public boolean placeItemIfEmpty(int itemID, ItemTypes itemType, Wearables bodyPart, Sprite sprite) {

        System.out.println("item: " + bodyPart + " is a " + this.bodyPartType);
        if (!hasItem() && sprite != null) {

            // ensure it is the correct body part
            if (itemType == ItemTypes.Wearable && bodyPart == this.bodyPartType) {

                this.itemID = itemID;
                this.itemType = itemType;

                this.sprite = sprite;
                this.sprite.setSize(this.width, this.height);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean placeItemIfEmpty(int itemID, ItemTypes itemType, Wearables bodyPart, String spritePath) {
        System.out.println("item: " + bodyPart + " is a " + this.bodyPartType);

        if (!hasItem() && sprite != null) {

            // ensure it is the correct body part
            if (itemType == ItemTypes.Wearable && bodyPart == this.bodyPartType) {
                this.itemID = itemID;
                this.itemType = itemType;

                this.sprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
                this.sprite.setSize(this.width, this.height);

                return true;
            }
        }

        return false;
    }

    /**
     * Returns the type of wearable this is
     * @return enum wearable
     */
    public Wearables bodyPart() {
        return this.bodyPartType;
    }
}
